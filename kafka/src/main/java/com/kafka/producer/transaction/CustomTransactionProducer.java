package com.kafka.producer.transaction;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author mapengfei
 */
public class CustomTransactionProducer {
    public static void main(String[] args) {

        //配置
        Properties properties = new Properties();

        //连接服务器
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //指定key和value的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //指定事务ID
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "Transaction-Demo1");

        //创建Kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        //初始化事务
        producer.initTransactions();
        //启动事务
        producer.beginTransaction();

        try {
            //发送数据
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<String, String>("first", "Hello World :" + i));
            }
            //提交事务
            producer.commitTransaction();
        }catch (Exception e) {
            //终止事务
            producer.abortTransaction();
        }finally {
            //关闭资源
            producer.close();
        }
    }
}
