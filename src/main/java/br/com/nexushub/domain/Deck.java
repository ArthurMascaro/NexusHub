package br.com.nexushub.domain;

import java.util.UUID;

public class Deck {

    private UUID id;
    private String name;
    private UUID userId;
    private Deck parentDeck;
    private Subject subject;

    public Deck(UUID id, String name, UUID userId, Deck parentDeck, Subject subject) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.parentDeck = parentDeck;
        this.subject = subject;
    }

    public Deck(String name, UUID userId, Deck parentDeck, Subject subject) {
        this.name = name;
        this.userId = userId;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
