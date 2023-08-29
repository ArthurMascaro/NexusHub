package br.com.nexushub.web.model.deck.response;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.web.model.flashcard.response.FlashcardResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DeckResponseWithFlashcards {

    private UUID id;
    private String name;
    private UUID subjectId;
    private UUID parentDeckId;
    private List<FlashcardResponse> flashcardResponses;

    private DeckResponseWithFlashcards(UUID id, String name, UUID subjectId, UUID parentDeckId, List<FlashcardResponse> flashcardResponses) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.parentDeckId = parentDeckId;
        this.flashcardResponses = flashcardResponses;
    }

    public static DeckResponseWithFlashcards createFromDeck(Deck deck, List<Flashcard> flashcards) {
        return new DeckResponseWithFlashcards(
                deck.getId(),
                deck.getName(),
                deck.getSubjectId(),
                deck.getParentDeckId(),
                flashcards.stream().map(FlashcardResponse::createFromFlashcard)
                        .collect(Collectors.toList())
        );
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

    public List<FlashcardResponse> getFlashcardResponses() {
        return flashcardResponses;
    }

    public void setFlashcardResponses(List<FlashcardResponse> flashcardResponses) {
        this.flashcardResponses = flashcardResponses;
    }
}
