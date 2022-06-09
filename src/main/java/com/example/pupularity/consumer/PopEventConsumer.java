package com.example.pupularity.consumer;

import com.example.pupularity.domain.*;
import org.springframework.stereotype.Component;

@Component
public class PopEventConsumer {

    private final EntertainmentRepository entertainmentRepository;
    private final PopEventRepository popEventRepository;

    public PopEventConsumer(EntertainmentRepository entertainmentRepository, PopEventRepository popEventRepository) {
        this.entertainmentRepository = entertainmentRepository;
        this.popEventRepository = popEventRepository;
    }

    public void consume(PopEvent event) {
        PopEventNode newPopEventNode = new PopEventNode(event.getId(), event.getType());
        if (event.getImpactedPopEventId() != null) {
            PopEventNode impactedEvent = popEventRepository.findById(event.getImpactedPopEventId()).orElseThrow();
            impactedEvent.getPopEvents().add(new PopEventRelation(newPopEventNode, 101));
            popEventRepository.save(impactedEvent);
        } else {
            EntertainmentNode ent = entertainmentRepository.save(new EntertainmentNode(event.getEntertainmentId()));
            ent.getPopEvents().add(newPopEventNode);
            entertainmentRepository.save(ent);
        }
    }
}
