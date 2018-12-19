# Stream processing with Kafka,Flink,Calcite

Java example to read from Kafka, mapping stream in to calcite table, query and write to a new topic with Flink.

## Usage

Do a `mvn clean install -Dmaven.test.skip=true` to build the jar. 

### Use the following arguments to run jar file:

--read-topic <topic> --write-topic <topic> --bootstrap.servers <kafka brokers> --group.id <groupid>
 
`bin\flink run stream-analytics-0.0.1-SNAPSHOT.jar --read-topic testin --write-topic testout --bootstrap.servers 10.34.4.136:9092 --group.id analytics`
 