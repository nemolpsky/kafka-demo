package com.lp.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 发送消息，三种方式
 */
@Component
public class KafkaSender {

    private final KafkaTemplate kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    public KafkaSender(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Object object) {

        // 发完即忘
        ListenableFuture<SendResult> listenableFuture = kafkaTemplate.send(topic,object);

        // 异步
//            listenableFuture.addCallback(new SuccessCallback<SendResult>() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    logger.info("send success");
//                }
//            }, new FailureCallback() {
//                @Override
//                public void onFailure(Throwable throwable) {
//                    logger.info("send failure");
//                }
//            });
//            listenableFuture.addCallback(new ListenableFutureCallback<SendResult>() {
//                @Override
//                public void onFailure(Throwable throwable) {
//                    logger.info("send failure");
//                }
//
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    logger.info("send success");
//                }
//            });

        // 同步方式
//        try {
//            SendResult sendResult = listenableFuture.get();
//            logger.info(sendResult.toString());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

    }

}
