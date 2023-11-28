package br.com.nexushub.usecases.todo;

import br.com.nexushub.configuration.auth.jwt.IAuthenticationFacade;
import br.com.nexushub.domain.Todo;
import br.com.nexushub.domain.TodoStatus;
import br.com.nexushub.usecases.subject.SubjectCRUD;
import br.com.nexushub.usecases.todo.gateway.TodoDAO;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import br.com.nexushub.web.model.todos.request.TodoRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TodoCRUDimpl implements TodoCRUD {

    private final TodoDAO todoDAO;
    private final SubjectCRUD subjectCRUD;
    private final IAuthenticationFacade authenticationFacade;

    public TodoCRUDimpl(TodoDAO todoDAO, SubjectCRUD subjectCRUD, IAuthenticationFacade authenticationFacade) {
        this.todoDAO = todoDAO;
        this.subjectCRUD = subjectCRUD;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Todo createNewTodo(TodoRequest todoRequest) {
        Todo todo = todoDAO.createNewTodo(todoRequest.toTodo());
        UUID todoId = todo.getId();
        todo.setId(todoId);
        todo.setDate(LocalDate.now());
        todo.setStatus(TodoStatus.PENDING);
        return todoDAO.createNewTodo(todo);
    }

    @Override
    public Todo findTodoById(UUID id) {
        return todoDAO.findTodoById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found todo with id: " + id)
        );
    }

    @Override
    public Todo updateTodoById(UUID id, TodoRequest todoRequest) {
        todoDAO.findTodoById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found todo with id: " + id)
        );
        Todo todo = todoRequest.toTodo();
        todo.setId(id);
        return todoDAO.updateTodoById(todo);
    }

    @Override
    public Todo updateTodoStatus(UUID id) {
        Todo todo = todoDAO.findTodoById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found todo with id: " + id)
        );
        if (todo.getStatus().equals("DONE"))
            todo.setStatus(TodoStatus.PENDING);
        else
            todo.setStatus(TodoStatus.DONE);

        return todoDAO.updateTodoStatus(todo);
    }
    @Override
    public List<Todo> findAllTodosBySubjectId(UUID id) {
        return todoDAO.findAllTodosBySubjectId(id);
    }

    @Override
    public Todo deleteTodoById(UUID id) {
        todoDAO.findTodoById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found todo with id: " + id)
        );
        return todoDAO.deleteTodoById(id);
    }
}
