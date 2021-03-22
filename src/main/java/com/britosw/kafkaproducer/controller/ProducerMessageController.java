package com.britosw.kafkaproducer.controller;

import com.britosw.kafkaproducer.exception.BusinessException;
import com.britosw.kafkaproducer.model.Message;
import com.britosw.kafkaproducer.service.broker.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("producer")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProducerMessageController {

    private final KafkaProducerService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> producerMessages(@RequestBody @Valid Message message) {
        try {
            this.service.sendMessage(message);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
