package br.com.nexushub.web.controller;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.Todo;
import br.com.nexushub.usecases.todo.TodoCRUD;
import br.com.nexushub.web.model.subject.request.SubjectRequest;
import br.com.nexushub.web.model.subject.response.SubjectResponse;
import br.com.nexushub.web.model.todos.request.TodoRequest;
import br.com.nexushub.web.model.todos.response.TodoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/todos")
@RestController
public class TodoController {

    private final TodoCRUD todoCRUD;

    public TodoController(TodoCRUD todoCRUD) {
        this.todoCRUD = todoCRUD;
    }

    @PostMapping("/save")
    public ResponseEntity<TodoResponse> createNewTodo(
            @RequestBody TodoRequest todoRequest) {
        Todo todo = todoCRUD.createNewTodo(todoRequest);
        return ResponseEntity.ok(TodoResponse.fromTodo(todo));
    }

    @GetMapping("/allBySubjectId/{id}")
    public ResponseEntity<List<TodoResponse>> findAllSubjects(
            @PathVariable("id") UUID id) {
        List<Todo> todos = todoCRUD.findAllTodosBySubjectId(id);
        return ResponseEntity.ok(todos.stream()
                .map(TodoResponse::fromTodo)
                .collect(Collectors.toList()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodoStatus(
            @PathVariable("id") UUID id) {
        Todo todo = todoCRUD.updateTodoStatus(id);
        return ResponseEntity.ok(TodoResponse.fromTodo(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodoById(
            @PathVariable("id") UUID id,
            @RequestBody TodoRequest todoRequest) {
        Todo todo = todoCRUD.updateTodoById(id, todoRequest);
        return ResponseEntity.ok(TodoResponse.fromTodo(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteTodoById(
            @PathVariable("id") UUID id) {
        Todo todo = todoCRUD.deleteTodoById(id);
        return ResponseEntity.ok(todo.getId());
    }
}
