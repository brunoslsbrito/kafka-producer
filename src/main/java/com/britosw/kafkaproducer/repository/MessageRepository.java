package com.britosw.kafkaproducer.repository;

import com.britosw.kafkaproducer.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
