package br.com.nexushub.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class Session {

    private UUID id;
    private Timestamp startTime;
    private Timestamp endTime;
    private Subject subject;
    private UUID ownerId;
    private SequenceItem sequenceItem;
    private Deck deck;

    public Session(UUID id, Timestamp startTime, Timestamp endTime, Subject subject, UUID ownerId, SequenceItem sequenceItem, Deck deck) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.ownerId = ownerId;
        this.sequenceItem = sequenceItem;
        this.deck = deck;
    }

    public Session(UUID id, Timestamp startTime, Timestamp endTime, UUID ownerId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public SequenceItem getSequenceItem() {
        return sequenceItem;
    }

    public void setSequenceItem(SequenceItem sequenceItem) {
        this.sequenceItem = sequenceItem;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
