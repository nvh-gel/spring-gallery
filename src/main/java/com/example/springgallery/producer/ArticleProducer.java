package com.example.springgallery.producer;

import com.example.springgallery.viewmodel.ArticleVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Producer for article.
 */
@Slf4j
@Service
public class ArticleProducer {

    private KafkaTemplate<String, ArticleVM> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    /**
     * Send message to queue
     *
     * @param articleVM article data to send
     */
    public void send(ArticleVM articleVM) {
        this.kafkaTemplate.send(topic, articleVM);
        log.info("Message sent -> {} to topic {}", articleVM, topic);
    }

    /**
     * Setter for kafkaTemplate.
     */
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, ArticleVM> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
