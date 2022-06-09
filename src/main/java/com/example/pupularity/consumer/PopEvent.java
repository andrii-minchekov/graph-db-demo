package com.example.pupularity.consumer;

import com.example.pupularity.domain.SentimentValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class PopEvent {

    private String id;
    private Type type;
    @Nullable
    private String impactedPopEventId;
    @Nullable
    private String entertainmentId;
    private SentimentValue sentimentValue;

    public PopEvent(String id, Type type, SentimentValue sentimentValue, String impactedPopEventId, String entertainmentId) {
        this.id = id;
        this.type = type;
        this.entertainmentId = entertainmentId;
        this.sentimentValue = sentimentValue;
        this.impactedPopEventId = impactedPopEventId;
    }

    public PopEvent(String id, Type type, String impactedPopEventId, String entertainmentId) {
        this(id, type, SentimentValue.NEUTRAL, impactedPopEventId, entertainmentId);
    }

    public enum Type {
        TWEET, RE_TWEET, REPLY, COMMENT, LIKE, DISLIKE
    }
}
