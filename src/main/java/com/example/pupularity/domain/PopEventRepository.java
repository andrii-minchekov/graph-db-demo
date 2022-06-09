package com.example.pupularity.domain;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopEventRepository extends Neo4jRepository<PopEventNode, String> {
}