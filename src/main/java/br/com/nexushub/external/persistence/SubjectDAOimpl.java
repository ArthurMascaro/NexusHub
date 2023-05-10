package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;
import br.com.nexushub.usecases.subject.gateway.SubjectDAO;
import br.com.nexushub.web.exception.GenericResourceException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class SubjectDAOimpl implements SubjectDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.subject-dao.insert.subject}")
    private String insertSubjectQuery;

    @Value("${queries.sql.subject-dao.select.subject-all}")
    private String findAllSubjectsQuery;

    @Value("${queries.sql.subject-dao.select.subject-by-id}")
    private String findSubjectByIdQuery;

    @Value("${queries.sql.subject-dao.update.subject}")
    private String updateSubjectQuery;

    @Value("${queries.sql.subject-dao.exists.subject-by-id}")
    private String existsSubjectByIdQuery;

    @Value("${queries.sql.subject-dao.delete.subject-by-id}")
    private String deleteSubjectByIdQuery;

    public SubjectDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Subject saveNewSubject(String name, int difficulty, SubjectColor color) {
        UUID subjectId = UUID.randomUUID();
        jdbcTemplate.update(insertSubjectQuery, subjectId, name, difficulty, color.name());
        return Subject.createWithAllFields(subjectId, name, difficulty, color);
    }

    @Override
    public void updateSubject(Subject subject) {
        jdbcTemplate.update(updateSubjectQuery, subject.getName(),
                subject.getDifficulty(), subject.getColor().name(), subject.getId());
    }

    @Override
    public Optional<Subject> findSubjectById(UUID id) {
        try {
            Subject subject = jdbcTemplate.queryForObject(findSubjectByIdQuery, (rs, rowNum) -> {
                UUID subjectId = (UUID) rs.getObject("id");
                String name = rs.getString("name");
                int difficulty = rs.getInt("difficulty");
                SubjectColor color = SubjectColor.valueOf(rs.getString("color"));
                return Subject.createWithAllFields(subjectId, name, difficulty, color);
            }, id);

            return Optional.of(subject);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public ArrayList<Subject> findAllSubjects() {
        ArrayList<Subject> subjectArray = new ArrayList<>();

        jdbcTemplate.query(findAllSubjectsQuery, (rs, rowNum) -> {
            try {
                UUID subjectId = (UUID) rs.getObject("id");
                String name = rs.getString("name");
                int difficulty = rs.getInt("difficulty");
                SubjectColor color = SubjectColor.valueOf(rs.getString("color"));
                Subject subject = Subject.createWithAllFields(subjectId, name, difficulty, color);
                subjectArray.add(subject);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLException();
            }
            return null;
        });

        return subjectArray;
    }

    @Override
    public boolean subjectExistsById(UUID id) {
        Boolean exists = jdbcTemplate.queryForObject(existsSubjectByIdQuery, Boolean.class, id);
        return Objects.nonNull(exists) && exists;
    }


    @Override
    public boolean deleteSubjectById(UUID id) {
        Boolean existsSubject = subjectExistsById(id);
        if (!existsSubject)
            return false;

        if (jdbcTemplate.update(deleteSubjectByIdQuery, id) != 1)
            throw new GenericResourceException("Unexpected error when try delete subject with id=" + id, "Exclusion Error");

        return true;
    }
}
