package com.example.pupularity;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestGraphRelationsSpringScheme {

    @Autowired
    private SomeEntityRepository someEntityRepository;

    @Test
    void testRelationshipPropertiesFeature() {
        SomeEntityRepository.SomeEntity entity = someEntityRepository.save(new SomeEntityRepository.SomeEntity("someEntityName"));
        entity.setSomeRelationsOut(relations(entity));
        someEntityRepository.save(entity);
    }

    private Set<SomeEntityRepository.SomeRelation> relations(SomeEntityRepository.SomeEntity entity) {
        return Set.of(new SomeEntityRepository.SomeRelation("someData", entity));
    }
}

@Repository
interface SomeEntityRepository extends Neo4jRepository<SomeEntityRepository.SomeEntity, Long> {

    @Node
    static class SomeEntity {

        @Id
        @GeneratedValue
        private Long number;

        private String name;

        public SomeEntity(String name) {
            this.name = name;
        }

        @Relationship(type = "SOME_RELATION_TO", direction = Relationship.Direction.OUTGOING)
        private Set<SomeRelation> someRelationsOut = new HashSet<>();

        public Long getNumber() {
            return number;
        }

        public void setNumber(Long number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<SomeRelation> getSomeRelationsOut() {
            return someRelationsOut;
        }

        public void setSomeRelationsOut(Set<SomeRelation> someRelationsOut) {
            this.someRelationsOut = someRelationsOut;
        }
    }

    @RelationshipProperties
    static class SomeRelation {

        @RelationshipId
        private Long id;

        private String someData;

        public SomeRelation(String someData, SomeEntity targetPerson) {
            this.someData = someData;
            this.targetPerson = targetPerson;
        }

        @TargetNode
        private SomeEntity targetPerson;
    }
}


