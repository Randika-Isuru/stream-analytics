package org.monitoring.stream.analytics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
import org.apache.flink.table.api.WindowedTable;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.api.java.Tumble;
import org.apache.flink.types.Row;

public class KafkaTableExample {

	public static void main(String[] args) throws Exception {
        // Read parameters from command line
		final ParameterTool params = ParameterTool.fromPropertiesFile("src/main/resources/application.properties");

        if(params.getNumberOfParameters() < 4) {
            System.out.println("\nUsage: FlinkReadKafka " +
                               "--read-topic <topic> " +
                               "--write-topic <topic> " +
                               "--bootstrap.servers <kafka brokers> " +
                               "--group.id <groupid>");
            return;
        }

        // define a schema
        String[] fieldNames = { "food", "price","processingTime"};
        TypeInformation<?>[] dataTypes = {Types.STRING, Types.INT, Types.SQL_TIMESTAMP};

        TypeInformation<Row> dataRow = Types.ROW_NAMED(fieldNames, dataTypes);

        // setup streaming environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(300000); // 300 seconds
        env.getConfig().setGlobalJobParameters(params);

        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

        KafkaTableSource kafkaTableSource = Kafka010JsonTableSource.builder()
                .forTopic(params.getRequired("read-topic"))
                .fromLatest()
                .withProctimeAttribute("processingTime")
                .withKafkaProperties(params.getProperties())
                .withSchema(TableSchema.fromTypeInfo(dataRow))
                .forJsonSchema(TableSchema.fromTypeInfo(dataRow))
                .build();

        String sql ="SELECT food,price,processingTime FROM foodTable WHERE processingTime BETWEEN processingTime - INTERVAL '4' HOUR AND processingTime";
        tableEnv.registerTableSource("foodTable", kafkaTableSource);
        WindowedTable windowedTable = tableEnv.scan("foodTable").window(Tumble.over("50.minutes").on("processingTime").as("userActionWindow")); 
        Table result = tableEnv.sqlQuery(sql);
        System.out.println(result.toString());


        // create a partition for the data going into kafka
        FlinkFixedPartitioner partition =  new FlinkFixedPartitioner();

        // create new tablesink of JSON to kafka
        KafkaJsonTableSink kafkaTableSink = new Kafka010JsonTableSink(
                params.getRequired("write-topic"),
                params.getProperties(),
                partition);

        result.writeToSink(kafkaTableSink);

        env.execute("FlinkReadWriteKafkaJSON");
    }

}
