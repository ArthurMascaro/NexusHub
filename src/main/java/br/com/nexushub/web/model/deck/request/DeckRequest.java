package br.com.nexushub.web.model.deck.request;

import java.util.UUID;

public record DeckRequest(String name, UUID subjectId, UUID parentDeckId){

    public DeckRequest(String name, UUID subjectId, UUID parentDeckId) {
        this.name = name;
        this.subjectId = subjectId;
        this.parentDeckId = parentDeckId;
    }
}
