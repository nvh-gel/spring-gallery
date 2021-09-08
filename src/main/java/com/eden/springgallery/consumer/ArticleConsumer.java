package com.eden.springgallery.consumer;

import com.eden.springgallery.service.ArticleService;
import com.eden.springgallery.viewmodel.ArticleVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Consumer for article message
 */
@Service
@Slf4j
public class ArticleConsumer {

    private static final String TOPIC = "gj6u8n8t-article";

    private ArticleService articleService;

    /**
     * Consume a message on queue.
     *
     * @param message   message to consume
     * @param partition partition info
     */
    @KafkaListener(topics = TOPIC)
    public void consume(@Payload ArticleVM message,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.info("Message received on queue {}, partition {}: {}", TOPIC, partition, message);
        if (ObjectUtils.isEmpty(message.getId())) {
            ArticleVM result = articleService.createArticle(message);
            log.info("created article: {}", result);
        } else {
            ArticleVM result = articleService.updateArticle(message);
            log.info("updated article: {}", result);
        }
    }

    /**
     * Setter for articleService.
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
