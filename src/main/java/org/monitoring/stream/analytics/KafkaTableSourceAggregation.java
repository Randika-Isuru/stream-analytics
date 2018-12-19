package org.monitoring.stream.analytics;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.Kafka010JsonTableSink;
import org.apache.flink.streaming.connectors.kafka.Kafka010JsonTableSource;
import org.apache.flink.streaming.connectors.kafka.KafkaJsonTableSink;
import org.apache.flink.streaming.connectors.kafka.KafkaTableSource;
import org.apache.flink.streaming.connectors.kafka.partitioner.FlinkFixedPartitioner;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

public class KafkaTableSourceAggregation {

	public static void main(String[] args) throws Exception {
		final ParameterTool globalParam = ParameterTool.fromPropertiesFile("src/main/resources/application.properties");
		
		String[] fieldNamesA = { "productid", "syntheticvalue", "syntheticEventTime" };
        TypeInformation<?>[] dataTypesA = {Types.STRING, Types.INT, Types.SQL_TIMESTAMP};
        TypeInformation<Row> dataRowSourceA = Types.ROW_NAMED(fieldNamesA, dataTypesA);
        
        String[] fieldNamesB = { "productid", "gavalue", "gaEventTime" };
        TypeInformation<?>[] dataTypesB = {Types.STRING, Types.INT, Types.SQL_TIMESTAMP};
        TypeInformation<Row> dataRowSourceB = Types.ROW_NAMED(fieldNamesB, dataTypesB);
        
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(300000); // 300 seconds
        env.getConfig().setGlobalJobParameters(globalParam);
        
        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
        
        KafkaTableSource kafkaTableSourceA = Kafka010JsonTableSource.builder()
                .forTopic(globalParam.getRequired("synthetic-topic"))
                .fromLatest()
                .withProctimeAttribute("syntheticEventTime")
                .withKafkaProperties(globalParam.getProperties())
                .withSchema(TableSchema.fromTypeInfo(dataRowSourceA))
                .forJsonSchema(TableSchema.fromTypeInfo(dataRowSourceA))
                .build();
        KafkaTableSource kafkaTableSourceB = Kafka010JsonTableSource.builder()
                .forTopic(globalParam.getRequired("ga-topic"))
                .fromLatest()
                .withProctimeAttribute("gaEventTime")
                .withKafkaProperties(globalParam.getProperties())
                .withSchema(TableSchema.fromTypeInfo(dataRowSourceB))
                .forJsonSchema(TableSchema.fromTypeInfo(dataRowSourceB))
                .build();
        
        tableEnv.registerTableSource("synthetic", kafkaTableSourceA);
        tableEnv.registerTableSource("ga", kafkaTableSourceB);
        
        String sql ="SELECT s.productid,s.syntheticvalue,g.gavalue,g.gaEventTime "
        		+ "FROM synthetic s,ga g "
        		+ "WHERE s.productid = g.productid "
        		+ "AND s.syntheticEventTime BETWEEN g.gaEventTime - INTERVAL '4' HOUR AND g.gaEventTime";
        Table result = tableEnv.sqlQuery(sql);
        
        // create a partition for the data going into kafka
        FlinkFixedPartitioner partition =  new FlinkFixedPartitioner();
        
     // create new tablesink of JSON to kafka
        KafkaJsonTableSink kafkaTableSink = new Kafka010JsonTableSink(
                globalParam.getRequired("write-topic"),
                globalParam.getProperties(),
                partition);
        
        result.writeToSink(kafkaTableSink);
        System.out.println(env.getExecutionPlan());
        env.execute("FlinkReadWriteKafkaJSON");
	}

}
