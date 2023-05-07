package com.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author mapengfei
 */
public class CustomConsumer {
    public static void main(String[] args) {

        Properties properties = new Properties();
        // Broker
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //key 和 value 反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 配置消费者组ID
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 创建一个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 定义主题 first
        List<String> topics = new ArrayList<>();
        topics.add("first");
        consumer.subscribe(topics);

        //消费数据
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }
    }
}
