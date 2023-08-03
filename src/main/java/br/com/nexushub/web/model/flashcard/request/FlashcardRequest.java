package br.com.nexushub.web.model.flashcard.request;

import br.com.nexushub.domain.Flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record FlashcardRequest(String question, String answer, UUID deckId, ArrayList<UUID> tags) {

    public FlashcardRequest(String question, String answer, UUID deckId, ArrayList<UUID> tags) {
        this.question = Objects.requireNonNull(question, "Question must not be null!");
        if (question.isEmpty()) throw new IllegalArgumentException("Question must not be empty!");

        this.answer = Objects.requireNonNull(answer, "Answer must not be null!");
        if (answer.isEmpty()) throw new IllegalArgumentException("Answer must not be empty!");

        this.deckId = Objects.requireNonNull(deckId, "DeckId must not be null!");

        this.tags = tags == null ? tags : new ArrayList<UUID>() ;
    }

    public Flashcard toFlashcard(){
        return Flashcard.createNewFlashcard(question, answer, deckId, tags);
    }
}
