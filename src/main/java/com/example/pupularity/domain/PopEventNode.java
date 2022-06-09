package com.example.pupularity.domain;

import com.example.pupularity.consumer.PopEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("PopEvent")
@Getter
@Setter
@NoArgsConstructor
public class PopEventNode implements PopNode {

    @Id
    private String id;
    private PopEvent.Type type;
    private double range; //calculated
    private double degreeCentrality; //calculated
    private SentimentValue sentimentValue;

    private String influence; //calculated

    public PopEventNode(String id, PopEvent.Type type) {
        this.type = type;
        this.id = id;
    }

    public PopEventNode(double range, SentimentValue sentimentValue, Set<PopEventRelation> popEvents) {
        this.range = range;
        this.sentimentValue = sentimentValue;
        this.popEvents = popEvents;
        this.id = id();
    }

    @Relationship(type = "REACT_ON", direction = INCOMING)
    private Set<PopEventRelation> popEvents = new HashSet<>();

}
