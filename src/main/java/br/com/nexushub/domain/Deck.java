package br.com.nexushub.domain;

import java.util.UUID;

public class Deck {

    private UUID id;
    private String name;
    private UUID ownerId;
    private UUID parentDeckId;
    private UUID subjectId;

    private Deck(UUID id, String name, UUID ownerId, UUID parentDeck, UUID subject) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.parentDeckId = parentDeck;
        this.subjectId = subject;
    }

    private Deck(String name, UUID ownerId, UUID parentDeck, UUID subject) {
        this.name = name;
        this.ownerId = ownerId;
        this.parentDeckId = parentDeck;
        this.subjectId = subject;
    }

    private Deck(String name, UUID subjectId, UUID parentDeckId){
        this.name = name;
        this.subjectId = subjectId;
        this.parentDeckId = parentDeckId;
    }

    private Deck(UUID id) {
        this.id = id;
    }

    public static Deck createWithoutIdAndOwner(String name, UUID subjectId, UUID parentDeckId){
        return new Deck(name, subjectId, parentDeckId);
    }

    public static Deck createWithoutId(String name, UUID ownerId, UUID parentDeck, UUID subject){
        return new Deck(name, ownerId, parentDeck, subject);
    }

    public static Deck createWithAllArgs(UUID id, String name, UUID ownerId, UUID parentDeck, UUID subject){
        return new Deck(id, name, ownerId, parentDeck, subject);
    }

    public static Deck createWithId(UUID id){
        return new Deck(id);
    }

    public Deck getNewInstanceWithId(UUID id){
        return new Deck(id, this.name, this.ownerId, this.parentDeckId, this.subjectId);
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

    public UUID getParentDeckId() {
        return parentDeckId;
    }

    public void setParentDeckId(UUID parentDeckId) {
        this.parentDeckId = parentDeckId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }
}
