package com.food.ordering.system.kafka.producer.service.impl;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, CompletableFuture<SendResult<K, V>> callback) {
        log.info("Sending kafka message={} to topic={}", message, topicName);
        try {
            CompletableFuture<SendResult<K, V>> future = kafkaTemplate.send(topicName, key, message);
            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Failed to send message={} to topic={}, exception={}", message, topicName,
                            ex.getMessage());
                    callback.completeExceptionally(ex);
                } else {
                    RecordMetadata metadata = result.getRecordMetadata();
                    log.info("Message sent successfully to topic={}, partition={}, offset={}",
                            topicName, metadata.partition(), metadata.offset());
                    callback.complete(result);
                }
            });

        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}",
                    key, message, e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: "
                    + key +", message: "+ message );
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer");
            kafkaTemplate.destroy();
        }
    }
}
