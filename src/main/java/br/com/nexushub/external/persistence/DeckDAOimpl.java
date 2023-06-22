package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.usecases.deck.gateway.DeckDAO;
import br.com.nexushub.web.exception.GenericResourceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        jdbcTemplate.update(updateDeckQuery, deck.getName(),
                deck.getSubjectId(), deck.getParentDeckId(), deck.getId());
        return deck;
    }

    @Override
    public Optional<Deck> findDeckById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(findDeckByIdQuery, (rs, rowNum) ->
                    Deck.createWithAllArgs(rs.getObject("id", UUID.class),
                            rs.getString("name"),
                            rs.getObject("owner_id", UUID.class),
                            rs.getObject("subject_id", UUID.class),
                            rs.getObject("parent_deck_id", UUID.class)), id));
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Deck> findAllDecksByUserId(UUID userId) {
           return jdbcTemplate.query(findAllDecksByUserIdQuery,
                   this::mapperDeckFromRs, userId);
    }

    @Override
    public Deck deleteDeckById(UUID id) {
        if (jdbcTemplate.update(deleteDeckByIdQuery, id) != 1)
            throw new GenericResourceException("Unexpected error when try delete deck with id=" + id, "Exclusion Error");
        return Deck.createWithId(id);
    }

    private Deck mapperDeckFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        UUID owner_id = (UUID) rs.getObject("owner_id");
        UUID subject_id = (UUID) rs.getObject("subject_id");
        UUID parent_deck_id = (UUID) rs.getObject("parent_deck_id");
        return Deck.createWithAllArgs(id, name, owner_id, subject_id, parent_deck_id);
    }
}
