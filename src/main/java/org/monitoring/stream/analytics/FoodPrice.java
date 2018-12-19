package org.monitoring.stream.analytics;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.core.fs.FileSystem.WriteMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.Types;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.table.sources.CsvTableSource;

public class FoodPrice {

	// *************************************************************************
	//     PROGRAM
	// *************************************************************************

	public static void main(String[] args) throws Exception {

		// set up execution environment
		StreamExecutionEnvironment  env = StreamExecutionEnvironment.getExecutionEnvironment();
		StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
		
		// Defining the table source
		CsvTableSource csvTableSource = CsvTableSource
			    .builder()
			    .path("src/main/resources/source.csv")
			    .field("food", Types.STRING())
			    .field("price", Types.INT())
			    .fieldDelimiter("|")
			    .lineDelimiter(",")
			    .ignoreParseErrors()
			    .build();
		
		// registering the Table
		tableEnv.registerTableSource("foodTable", csvTableSource);
		
		// create a Table from a SQL query
		Table sqlResult = tableEnv.sqlQuery("SELECT food,price FROM foodTable WHERE price>20");
		
		//define the table sink
		TableSink sink = new CsvTableSink("src/resources/sink.csv", "|",1,WriteMode.OVERWRITE);
		
		// define the field names and types
		String[] fieldNames = {"food", "price"};
		TypeInformation[] fieldTypes = {Types.STRING(), Types.INT()};

		// register the TableSink as table "CsvSinkTable"
		tableEnv.registerTableSink("CsvSinkTable", fieldNames, fieldTypes, sink);
		
		// emit a Table API result Table to a TableSink, same for SQL result
		sqlResult.writeToSink(sink);
		
		// execute
		env.execute();
		
		System.out.println("END");
		
	}

} 
