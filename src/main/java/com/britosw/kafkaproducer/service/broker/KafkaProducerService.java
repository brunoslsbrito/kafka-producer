package com.britosw.kafkaproducer.service.broker;

import com.britosw.kafkaproducer.model.Message;
import com.britosw.kafkaproducer.repository.MessageRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class KafkaProducerService {

    private final MessageRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(Message message) {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("poc-kafka", new Gson().toJson(message));
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
