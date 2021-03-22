package com.britosw.kafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import message.Messages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @JsonProperty("value")
    @NotBlank(message = Messages.MESSAGE_VALUE_REQUIRED)
    @Size(max = 255, message = Messages.MESSAGE_VALUE_EXCEEDED)
    private String value;
}
