package br.com.nexushub.usecases.todo;

import br.com.nexushub.domain.Todo;
import br.com.nexushub.web.model.todos.request.TodoRequest;

import java.util.List;
import java.util.UUID;

public interface TodoCRUD {

    Todo createNewTodo(TodoRequest todoRequest);
    Todo findTodoById(UUID id);
    Todo updateTodoById(UUID id, TodoRequest todoRequest);

    Todo updateTodoStatus(UUID id);

    List<Todo> findAllTodosBySubjectId(UUID id);

    Todo deleteTodoById(UUID id);
}
