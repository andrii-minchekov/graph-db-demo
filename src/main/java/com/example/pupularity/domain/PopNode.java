package com.example.pupularity.domain;

import java.util.UUID;

public interface PopNode {

    default int degreeCentrality() {
        return 1;
    }

    default int range() {
        return 1;
    }

    default int influence() {
        return 1;
    }

    default String id() {
        return uniqueId();
    }

    static String uniqueId() {
        return UUID.randomUUID().toString();
    }
}
