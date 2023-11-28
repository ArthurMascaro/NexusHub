package br.com.nexushub.web.model.todos.response;


import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.Todo;
import br.com.nexushub.domain.TodoStatus;
import br.com.nexushub.web.model.subject.response.SubjectResponse;

import java.time.LocalDate;
import java.util.UUID;

public class TodoResponse {

    private UUID id;
    private String text;
    private TodoStatus status;
    private LocalDate localDate;
    private UUID subjectparentId;

    public TodoResponse(UUID id, String text, TodoStatus status, LocalDate localDate, Subject subject) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.localDate = localDate;
        this.subjectparentId = subject.getId();
    }

    public static TodoResponse fromTodo(Todo todo){
        return new TodoResponse(
                todo.getId(),
                todo.getText(),
                todo.getStatus(),
                todo.getDate(),
                todo.getSubject()
        );
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public UUID getSubjectparentId() {
        return subjectparentId;
    }
}
