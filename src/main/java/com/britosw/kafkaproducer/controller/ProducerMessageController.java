package com.britosw.kafkaproducer.controller;

import com.britosw.kafkaproducer.model.Message;
import com.britosw.kafkaproducer.service.broker.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("producer")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProducerMessageController {

    private final KafkaProducerService service;

    @PostMapping
    public ResponseEntity.BodyBuilder producerMessages(@RequestBody Message message) {
        this.service.sendMessage(message);
        return ResponseEntity.ok();
    }
}
