package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.domain.FlashcardStatus;
import br.com.nexushub.usecases.flashcard.gateway.FlashcardDAO;
import br.com.nexushub.usecases.tag.gateway.TagDAO;
import br.com.nexushub.web.exception.GenericResourceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FlashcardDAOimpl implements FlashcardDAO {

    private final JdbcTemplate jdbcTemplate;

    private final TagDAO tagDAO;

    @Value("${queries.sql.flashcard-dao.insert.flashcard}")
    private String saveNewFlashcardQuery;

    @Value("${queries.sql.flashcard-dao.select.flashcard-by-id}")
    private String findFlashcardByIdQuery;

    @Value("${queries.sql.flashcard-dao.select.flashcard-all}")
    private String findAllFlashcardByDeckIdQuery;

    @Value("${queries.sql.flashcard-dao.update.flashcard}")
    private String updateFlashcardByIdQuery;

    @Value("${queries.sql.flashcard-dao.delete.flashcard-by-id}")
    private String deleteFlashcardByIdQuery;

    public FlashcardDAOimpl(JdbcTemplate jdbcTemplate, TagDAO tagDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagDAO = tagDAO;
    }

    @Override
    public Flashcard saveNewFlashcard(Flashcard flashcard) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(saveNewFlashcardQuery, id, flashcard.getQuestion(), flashcard.getAnswer(),
                flashcard.getNextRevisionDate(), flashcard.getLastRevisionDate(), flashcard.getStatus().name(),
                flashcard.getMaturity(), flashcard.getDeckId());
        return flashcard.getNewInstanceWithId(id);
    }

    @Override
    public Flashcard updateFlashcard(Flashcard flashcard) {
        jdbcTemplate.update(updateFlashcardByIdQuery, flashcard.getQuestion(), flashcard.getAnswer(),
                flashcard.getNextRevisionDate(), flashcard.getLastRevisionDate(), flashcard.getStatus().name(),
                flashcard.getMaturity(), flashcard.getDeckId(), flashcard.getId());
        return flashcard;
    }

    @Override
    public Optional<Flashcard> findFlashcardById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(findFlashcardByIdQuery,
                    this::mapperFlashcardFromRs, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Flashcard deleteFlashcardById(UUID id) {
        if (jdbcTemplate.update(deleteFlashcardByIdQuery, id) != 1)
            throw new GenericResourceException("Unexpected error when try delete flashcard with id=" + id, "Exclusion Error");
        return Flashcard.createWithId(id);
    }

    @Override
    public List<Flashcard> findAllFlashcardByDeckId(UUID deckId) {
        return jdbcTemplate.query(findAllFlashcardByDeckIdQuery,
                this::mapperFlashcardFromRs, deckId);
    }

    private Flashcard mapperFlashcardFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String question = rs.getString("question");
        String answer = rs.getString("answer");
        LocalDate nextRevisionDate = rs.getDate("next_revision_date").toLocalDate();
        LocalDate lastRevisionDate = rs.getDate("last_revision_date").toLocalDate();
        FlashcardStatus status = FlashcardStatus.valueOf(rs.getString("status"));
        Double maturity = rs.getDouble("maturity");
        UUID deckId = (UUID) rs.getObject("deck_id");
        ArrayList<UUID> tags = new ArrayList<>();
        Flashcard flashcard = Flashcard.createWithAllArgs(id, question, answer, nextRevisionDate, lastRevisionDate, status, maturity, deckId, tags);
        return flashcard;
    }
}
