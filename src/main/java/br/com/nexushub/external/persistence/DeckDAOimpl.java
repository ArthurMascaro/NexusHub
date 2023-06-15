package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.usecases.deck.gateway.DeckDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeckDAOimpl implements DeckDAO {

    private final JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.deck-dao.insert.deck}")
    private String insertDeckQuery;

    @Value("${queries.sql.deck-dao.select.deck-by-id}")
    private String findDeckByIdQuery;

    @Value("${queries.sql.deck-dao.update.deck}")
    private String updateDeckQuery;

    @Value("${queries.sql.deck-dao.select.deck-all}")
    private String findAllDecksByUserIdQuery;

    @Value("${queries.sql.deck-dao.delete.deck-by-id}")
    private String deleteDeckByIdQuery;

    public DeckDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Deck saveNewDeck(Deck deck) {
        UUID deckId = UUID.randomUUID();
        if (deck.getSubjectId() == null && deck.getParentDeckId() == null)
            jdbcTemplate.update(insertDeckQuery, deckId, deck.getName(), deck.getOwnerId(), null, null);
        else if (deck.getSubjectId() != null && deck.getParentDeckId() == null)
            jdbcTemplate.update(insertDeckQuery, deckId, deck.getName(), deck.getOwnerId(), deck.getSubjectId(), null);
        else if (deck.getSubjectId() == null && deck.getParentDeckId() != null)
            jdbcTemplate.update(insertDeckQuery, deckId, deck.getName(), deck.getOwnerId(), null, deck.getParentDeckId());
        else
            jdbcTemplate.update(insertDeckQuery, deckId, deck.getName(), deck.getOwnerId(), deck.getSubjectId(), deck.getParentDeckId());

        return deck.getNewInstanceWithId(deckId);
    }

    @Override
    public Deck updateDeck(Deck deck) {
        return null;
    }

    @Override
    public Optional<Deck> findDeckById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Deck> findAllDecksByUserId(UUID userId) {
        return null;
    }

    @Override
    public Deck deleteDeckById(UUID id) {
        return null;
    }
}
