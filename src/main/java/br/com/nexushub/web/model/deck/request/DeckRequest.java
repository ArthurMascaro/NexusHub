package br.com.nexushub.web.model.deck.request;

import br.com.nexushub.domain.Deck;

import java.util.Objects;
import java.util.UUID;

public record DeckRequest(String name, UUID subjectId, UUID parentDeckId){

    public DeckRequest(String name, UUID subjectId, UUID parentDeckId) {
        this.name = Objects.requireNonNull(name, "Name must not be null!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty!");

        this.subjectId = subjectId;
        this.parentDeckId = parentDeckId;
    }

    public Deck toDeck(){
        return Deck.createWithoutIdAndOwner(name, subjectId, parentDeckId);
    }
}
