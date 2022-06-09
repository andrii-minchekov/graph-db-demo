package com.example.pupularity.consumer;

import com.example.pupularity.domain.EntertainmentRepository;
import com.example.pupularity.domain.PopEventRepository;
import com.example.pupularity.domain.PopScoreService;
import com.example.pupularity.producer.PopScoreProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static com.example.pupularity.domain.PopNode.uniqueId;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PopEventConsumerTest {

    private static final int EVENT_COUNT = 10;
    @Autowired
    private PopEventConsumer consumer;
    @Autowired
    private PopEventRepository popEventRepository;
    @Autowired
    private EntertainmentRepository entertainmentRepository;
    @Autowired
    private PopScoreProducer popScoreProducer;
    @Autowired
    private PopScoreService popScoreService;

    @BeforeEach
    void setup() {
        popEventRepository.deleteAll();
        entertainmentRepository.deleteAll();
    }

    @Test
    void shouldConsumeAndUpdateGraph() {
        String entertainmentId = "Sweet November";
        var firstEvent = new PopEvent(uniqueId(), PopEvent.Type.TWEET, null, entertainmentId);
        consumer.consume(firstEvent);
        IntStream.range(1, EVENT_COUNT).forEach(i -> {
            var eventI = new PopEvent(uniqueId(), PopEvent.Type.REPLY, null, firstEvent.getEntertainmentId());
            consumer.consume(eventI);
            IntStream.range(1, EVENT_COUNT).forEach(ii -> {
                var eventII = new PopEvent(uniqueId(), PopEvent.Type.REPLY, eventI.getId(), null);
                consumer.consume(eventII);
            });
        });
        popScoreProducer.produce(entertainmentId, popScoreService.popularityScore(entertainmentRepository.findById(entertainmentId).orElseThrow()));
    }
}