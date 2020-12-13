package com.lp.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class JmsApplicationTests {

    @Autowired
    private KafkaSender kafkaSender;

    @Test
    public void testSend() throws Exception {
        kafkaSender.send("testTopic","testMessage");
        TimeUnit.SECONDS.sleep(10);
    }

}
