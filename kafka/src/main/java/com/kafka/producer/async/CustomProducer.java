package com.kafka.producer.async;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author mapengfei
 */
public class CustomProducer {
    public static void main(String[] args) {

        //配置
        Properties properties = new Properties();

        //连接服务器
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //指定key和value的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //1, 创建Kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        //2, 发送数据
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<String, String>("first", "Hello World :" + i));
        }
        //3, 关闭资源
        producer.close();
    }
}
