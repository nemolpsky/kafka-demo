package com.lp.jms;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

/**
 * Kafka配置，覆盖默认的生产者工厂和消费者工厂
 */
@Configuration
public class KafkaConfig {

    @Bean
    public DefaultKafkaProducerFactory producerFactory(KafkaProperties properties) {
        Map<String, Object> producerProperties = properties.buildProducerProperties();
        producerProperties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG,1000);
//        producerProperties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,1000);

        // 添加生产者拦截器
        producerProperties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, CustomProducerInterceptor.class.getName());
        // 新建工厂，加载参数，设置序列化器
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(producerProperties,
                new StringSerializer(),
                new JsonSerializer());
        String transactionIdPrefix = properties.getProducer()
                .getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    public DefaultKafkaConsumerFactory consumerFactory(KafkaProperties properties) {
        // 新建消费者工厂，配置参数和反序列化器
        Map<String, Object> consumerProperties = properties.buildConsumerProperties();
        // 反序列化器，要添加序列化信任名单
        JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>();
        valueDeserializer.addTrustedPackages("com.lp.jms");
        DefaultKafkaConsumerFactory<?, ?> factory = new DefaultKafkaConsumerFactory<String,Object>(consumerProperties,
                new StringDeserializer(),
                valueDeserializer);
        return factory;
    }
}
