package br.com.nexushub.usecases.todo.gateway;

import br.com.nexushub.domain.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoDAO {
    Todo createNewTodo (Todo todo);
    Optional<Todo> findTodoById (UUID id);
    List<Todo> findAllTodosBySubjectId(UUID id);
    Todo updateTodoById (Todo todo);
    Todo updateTodoStatus (Todo todo);
    Todo deleteTodoById (UUID id);

}
