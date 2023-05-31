package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;
import br.com.nexushub.usecases.subject.gateway.SubjectDAO;
import br.com.nexushub.web.exception.GenericResourceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class SubjectDAOimpl implements SubjectDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.subject-dao.insert.subject}")
    private String insertSubjectQuery;

    @Value("${queries.sql.subject-dao.select.subject-by-id}")
    private String findSubjectByIdQuery;

    @Value("${queries.sql.subject-dao.update.subject}")
    private String updateSubjectQuery;

    @Value("${queries.sql.subject-dao.exists.subject-by-id}")
    private String existsSubjectByIdQuery;

    @Value("${queries.sql.subject-dao.delete.subject-by-id}")
    private String deleteSubjectByIdQuery;

    @Value("${queries.sql.subject-dao.select.subject-all}")
    private String findAllSubjectsByUserIdQuery;

    public SubjectDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Subject saveNewSubject(Subject subject) {
        UUID subjectId = UUID.randomUUID();
        jdbcTemplate.update(insertSubjectQuery, subjectId, subject.getName(), subject.getDifficulty(), subject.getColor().name(), subject.getOwnerId());
        return subject.getNewInstanceWithId(subjectId);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        jdbcTemplate.update(updateSubjectQuery, subject.getName(),
                subject.getDifficulty(), subject.getColor().name(), subject.getId());
        return subject;
    }

    @Override
    public Optional<Subject> findSubjectById(UUID id) {
        try {
            Subject subject = jdbcTemplate.queryForObject(findSubjectByIdQuery, this::mapperSubjectFromRs, id);

            return Optional.of(subject);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Subject> findAllSubjectsByUserId(UUID userId) {
        List<Subject> subjectsArray = jdbcTemplate.query(findAllSubjectsByUserIdQuery, this::mapperSubjectFromRs, userId);

        return subjectsArray;
    }

    @Override
    public boolean subjectExistsById(UUID id) {
        Boolean exists = jdbcTemplate.queryForObject(existsSubjectByIdQuery, Boolean.class, id);
        return Objects.nonNull(exists) && exists;
    }


    @Override
    public Subject deleteSubjectById(UUID id) {
        if (jdbcTemplate.update(deleteSubjectByIdQuery, id) != 1)
            throw new GenericResourceException("Unexpected error when try delete subject with id=" + id, "Exclusion Error");

        return Subject.createOnlyWithId(id);
    }

    private Subject mapperSubjectFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID subjectId = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        int difficulty = rs.getInt("difficulty");
        SubjectColor color = SubjectColor.valueOf(rs.getString("color"));
        UUID ownerId = (UUID) rs.getObject("owner_id");
        return Subject.createWithAllFields(subjectId, name, difficulty, color, ownerId);
    }
}
