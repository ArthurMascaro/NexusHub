package br.com.nexushub.domain;

import java.util.UUID;

public class Deck {

    private UUID id;
    private String name;
    private UUID ownerId;
    private Deck parentDeck;
    private Subject subject;

    public Deck(UUID id, String name, UUID ownerId, Deck parentDeck, Subject subject) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.parentDeck = parentDeck;
        this.subject = subject;
    }

    public Deck(String name, UUID ownerId, Deck parentDeck, Subject subject) {
        this.name = name;
        this.ownerId = ownerId;
        this.parentDeck = parentDeck;
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public Deck getParentDeck() {
        return parentDeck;
    }

    public void setParentDeck(Deck parentDeck) {
        this.parentDeck = parentDeck;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
