package br.com.nexushub.usecases.flashcard.gateway;

import br.com.nexushub.domain.Flashcard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlashcardDAO {

    Flashcard saveNewFlashcard(Flashcard flashcard);

    Flashcard updateFlashcard(Flashcard flashcard);

    Optional<Flashcard> findFlashcardById(UUID id);

    Flashcard deleteFlashcardById(UUID id);

    List<Flashcard> findAllFlashcardByDeckId(UUID deckId);

}
