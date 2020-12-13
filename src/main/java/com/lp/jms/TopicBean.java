package com.lp.jms;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * 自动创建主题
 */
@Configuration
public class TopicBean {

    @Bean
    public NewTopic testTopic1() {
        return TopicBuilder.name("testTopic1")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic testTopic6() {
        return TopicBuilder.name("testTopic6")
                .partitions(5)
                .build();
    }

    @Bean
    public NewTopic testTopic5() {
        return TopicBuilder.name("testTopic5")
                .partitions(5)
                .build();
    }

    @Bean
    public NewTopic testTopic7() {
        return TopicBuilder.name("testTopic7")
                .partitions(1)
                .build();
    }
}
