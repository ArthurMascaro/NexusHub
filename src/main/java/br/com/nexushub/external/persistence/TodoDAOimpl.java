package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Todo;
import br.com.nexushub.domain.TodoStatus;
import br.com.nexushub.usecases.subject.gateway.SubjectDAO;
import br.com.nexushub.usecases.todo.gateway.TodoDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TodoDAOimpl implements TodoDAO {

    private final JdbcTemplate jdbcTemplate;

    private final SubjectDAO subjectDAO;

    @Value("${queries.sql.todo-dao.insert.todo}")
    private String createTodoQuery;

    @Value("${queries.sql.todo-dao.select.todo-by-id}")
    private String findTodoByIdQuery;

    @Value("${queries.sql.todo-dao.select.todo-all}")
    private String findAllTodosBySubjectIdQuery;

    @Value("${queries.sql.todo-dao.update.todo}")
    private String updateTodoQuery;

    @Value("${queries.sql.todo-dao.update.todo-status}")
    private String updateTodoStatusQuery;

    @Value("${queries.sql.todo-dao.delete.todo-by-id}")
    private String deleteTodoByIdQuery;

    public TodoDAOimpl(JdbcTemplate jdbcTemplate, SubjectDAO subjectDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.subjectDAO = subjectDAO;
    }

    @Override
    public Todo createNewTodo(Todo todo) {
        jdbcTemplate.update(createTodoQuery, todo.getId(), todo.getText(), todo.getDate(), todo.getStatus(), todo.getSubject().getId());
        return todo;
    }

    @Override
    public Optional<Todo> findTodoById(UUID id) {
        try {
            jdbcTemplate.query(findTodoByIdQuery, this::mapperTodoFromRs, id);
        } catch (Exception e){
            e.printStackTrace();

        }
        return Optional.empty();
    }

    @Override
    public List<Todo> findAllTodosBySubjectId(UUID id) {
        return jdbcTemplate.query(findAllTodosBySubjectIdQuery, this::mapperTodoFromRs, id);
    }

    @Override
    public Todo updateTodoById(Todo todo) {
        jdbcTemplate.update(updateTodoQuery, todo.getText(), todo.getDate(),
                todo.getSubject().getId(), todo.getId());
        return todo;
    }

    @Override
    public Todo updateTodoStatus(Todo todo) {
        jdbcTemplate.update(updateTodoStatusQuery, todo.getStatus(), todo.getId());
        return todo;
    }

    @Override
    public Todo deleteTodoById(UUID id) {
        jdbcTemplate.update(deleteTodoByIdQuery, id);
        return new Todo(id);
    }

    private Todo mapperTodoFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID subjectId = rs.getObject("subject_id", UUID.class);
        return new Todo(
                rs.getObject("id", UUID.class),
                rs.getString("text"),
                rs.getObject("date", LocalDate.class),
                TodoStatus.valueOf(rs.getString("status")),
                subjectDAO.findSubjectById(subjectId).orElseThrow(
                        () -> new RuntimeException("Not found subject with id: " + subjectId)
                )
        );
    }
}
