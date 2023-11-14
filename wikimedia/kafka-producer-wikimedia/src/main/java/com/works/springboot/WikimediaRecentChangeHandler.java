package com.works.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaRecentChangeHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(WikimediaRecentChangeHandler.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic_name;

    public WikimediaRecentChangeHandler(KafkaTemplate<String, String> kafkaTemplate, String topic_name) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic_name = topic_name;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {

        logger.info("Data from event = {}", messageEvent.getData());
        kafkaTemplate.send(topic_name, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
