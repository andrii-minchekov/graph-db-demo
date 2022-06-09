package com.example.pupularity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PopEventRelation {

    @RelationshipId
    @GeneratedValue
    private Long id;

    @TargetNode
    private PopEventNode targetNode;

    private double weight; //calculated

    public PopEventRelation(PopEventNode relationTo, double weight) {
        this.targetNode = relationTo;
        this.weight = weight;
    }
}
