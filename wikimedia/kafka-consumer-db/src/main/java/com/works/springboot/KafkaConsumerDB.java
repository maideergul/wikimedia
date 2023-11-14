package com.works.springboot;

import com.works.springboot.entity.WikimediaDB;
import com.works.springboot.repository.WikimediaDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDB {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerDB.class);
    private WikimediaDBRepository wikimediaDBRepository;

    public KafkaConsumerDB(WikimediaDBRepository wikimediaDBRepository) {
        this.wikimediaDBRepository = wikimediaDBRepository;
    }

    @KafkaListener(topics = "wikimedia_recentChange", groupId = "groupOne")
    public void consume(String eventMessage)
    {
        logger.info("Event message has received = {}", eventMessage);
        WikimediaDB wikimediaDB = new WikimediaDB();
        wikimediaDB.setWikimediaEventData(eventMessage);

        wikimediaDBRepository.save(wikimediaDB);
    }
}
