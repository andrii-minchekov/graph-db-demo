package com.example.pupularity.producer;

import com.example.pupularity.domain.PopScoreService;
import org.springframework.stereotype.Component;

@Component
public class PopScoreProducer {

    private final PopScoreService popScoreService;

    public PopScoreProducer(PopScoreService popScoreService) {
        this.popScoreService = popScoreService;
    }

    public void produce(String entertainmentId, double popScore) {
    }
}
