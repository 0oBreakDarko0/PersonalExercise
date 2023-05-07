package com.kafka.producer.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author mapengfei
 */
public class MyPartitioner implements Partitioner {
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        int partition;

        String value = o1.toString();

        if(value.contains("Hello")){
            partition = 0;
        } else {
            partition = 1;
        }

        return partition;
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
