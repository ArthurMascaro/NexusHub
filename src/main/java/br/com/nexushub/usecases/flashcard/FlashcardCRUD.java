package br.com.nexushub.usecases.flashcard;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.web.model.flashcard.request.FlashcardRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FlashcardCRUD {

    Flashcard createNewFlashcard(FlashcardRequest flashcardRequest);

    Flashcard findFlashcardById(UUID id);

    Flashcard updateFlashcardById(UUID id, FlashcardRequest flashcardRequest);

    Flashcard deleteFlashcardById(UUID id);

    List<Flashcard> findAllFlashcardByDeckId(UUID deckId);

    Flashcard addTagToFlashcard(UUID flashcardId, UUID tagId);

    Map<Deck, List<Flashcard>> AllFlashcardForDeckAndChildren(UUID deckId);
}
