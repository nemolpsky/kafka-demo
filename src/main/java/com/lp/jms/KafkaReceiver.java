package com.lp.jms;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 接收消息，@KafkaListener可以指定消费者id，消费组，分区
 */
@Component
public class KafkaReceiver {

    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @KafkaListener(id = "testGroup1", topics = "testTopic1",groupId = "t1")
    public void listen1(ConsumerRecord<String, Object> record) {
        logger.info("listen1 received: {}", ((User) record.value()).toString());
    }


    @KafkaListener(id = "testGroup2", topics = {"testTopic2", "testTopic3"},
            topicPartitions = {
            @TopicPartition(topic = "testTopic2", partitions = {"0"}), @TopicPartition(topic = "testTopic3", partitions = "0")
            })
    public void listen2(ConsumerRecord<String, Object> record) {
        logger.info("listen2 received: {}", ((User) record.value()).toString());
    }

    @KafkaListener(id = "testGroup3", topics = "testTopic1",groupId = "t2")
    public void listen3(ConsumerRecord<String, Object> record) {
        logger.info("listen3 received: {}",((User) record.value()).toString());
    }

    @KafkaListener(id = "testGroup4", topics = "testTopic1",groupId = "t2")
    public void listen4(ConsumerRecord<String, Object> record) {
        logger.info("listen4 received: {}",((User) record.value()).toString());
    }

    @KafkaListener(id = "testGroup5", topics = "testTopic5",concurrency = "5")
    public void listen5(ConsumerRecord<String, Object> record) {
        int random = new Random().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Thread name is {} ,sleep {}s ,listen5 received: {}",Thread.currentThread().getName()
                ,random,((User) record.value()).toString());
    }

    @KafkaListener(id = "testGroup7", topics = "testTopic7",concurrency = "5")
    public void listen7(ConsumerRecord<String, Object> record) {
        int random = new Random().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Thread name is {} ,sleep {}s ,listen7 received: {}",Thread.currentThread().getName()
                ,random,((User) record.value()).toString());
    }
}
