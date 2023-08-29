package br.com.nexushub.usecases.deck;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.web.model.deck.request.DeckRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DeckCRUD {

    Deck createNewDeck(DeckRequest deckRequest);

    Deck findDeckById(UUID id);

    Deck updateDeckById(UUID id, DeckRequest deckRequest);

    List<Deck> findAllDeckByUserId();

    List<Deck> getAllDeckChildrenById(UUID deckId);

    Deck deleteDeckById(UUID id);

}
