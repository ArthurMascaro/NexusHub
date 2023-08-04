package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Tag;
import br.com.nexushub.usecases.tag.gateway.TagDAO;
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
public class TagDAOimpl implements TagDAO {

    private final JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.tag-dao.insert.tag}")
    private String addTagQuery;

    @Value("${queries.sql.tag-dao.update.tag}")
    private String updateTagQuery;

    @Value("${queries.sql.tag-dao.select.tag-by-id}")
    private String findTagByIdQuery;

    @Value("${queries.sql.tag-dao.delete.tag-by-id}")
    private String deleteTagByIdQuery;

    @Value("${queries.sql.tag-dao.select.tag-all}")
    private String findAllTagsByOwnerIdQuery;

    @Value("${queries.sql.tag-dao.select.tag-by-flashcard-id}")
    private String findALlTagsByFlashcardIdQuery;

    public TagDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag saveNewTag(Tag tag) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(addTagQuery, id, tag.getName(), tag.getOwnerId());
        return tag.newInstanceWithId(id);
    }

    @Override
    public Tag updateTag(Tag tag) {
        jdbcTemplate.update(updateTagQuery, tag.getName(), tag.getId());
        return tag;
    }

    @Override
    public Optional<Tag> findTagById(UUID id) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    findTagByIdQuery, this::mapperTagFromRs, id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Tag deleteTagById(UUID id) {
        if (jdbcTemplate.update(deleteTagByIdQuery, id) != 1)
            throw new GenericResourceException("Unexpected error when try delete tag with id=" + id, "Exclusion Error");
        return Tag.createWithId(id);
    }

    @Override
    public List<Tag> findAllTagsByOwnerId(UUID ownerId) {
        return jdbcTemplate.query(findAllTagsByOwnerIdQuery,
                this::mapperTagFromRs, ownerId);
    }

    @Override
    public List<Tag> findALlTagsByFlashcardId(UUID flashcardId) {
        return jdbcTemplate.query(findALlTagsByFlashcardIdQuery,
                this::mapperTagFromRs, flashcardId);
    }

    private Tag mapperTagFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String question = rs.getString("name");
        UUID ownerId = (UUID) rs.getObject("owner_id");
        return Tag.createNewTagWithAllArgs(id, question, ownerId);
    }
}
