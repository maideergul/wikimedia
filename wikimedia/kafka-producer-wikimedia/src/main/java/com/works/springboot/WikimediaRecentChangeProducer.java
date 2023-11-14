package com.works.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaRecentChangeProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikimediaRecentChangeProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaRecentChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() throws InterruptedException {

        String topic_name = "wikimedia_recentChange";

        EventHandler eventHandler = new WikimediaRecentChangeHandler(kafkaTemplate, topic_name);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);



    }
}
