package br.com.nexushub.usecases.deck.gateway;

import br.com.nexushub.domain.Deck;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeckDAO{

    Deck saveNewDeck(Deck deck);

    Deck updateDeck(Deck deck);

    Optional<Deck> findDeckById(UUID id);

    List<Deck> findAllDecksByUserId(UUID userId);

    Deck deleteDeckById(UUID id);
}
