package com.example.pupularity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Entertainment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntertainmentNode implements PopNode{

    @Id
    private String id;

    public EntertainmentNode(String id) {
        this.id = id;
    }

    @Relationship(type = "REACT_ON", direction = INCOMING)
    private Set<PopEventNode> popEvents = new HashSet<>();
}
