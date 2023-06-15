package br.com.nexushub.web.model.deck.response;

import java.util.UUID;

public class DeckResponse {

    private UUID id;
    private String name;
    private UUID subjectId;
    private UUID parentDeckId;

    public DeckResponse() {
    }

    public DeckResponse(UUID id, String name, UUID subjectId, UUID parentDeckId) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.parentDeckId = parentDeckId;
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

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public UUID getParentDeckId() {
        return parentDeckId;
    }

    public void setParentDeckId(UUID parentDeckId) {
        this.parentDeckId = parentDeckId;
    }
}
