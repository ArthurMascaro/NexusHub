package br.com.nexushub.web.model.todos.request;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.Todo;

import java.util.Objects;
import java.util.UUID;

public record TodoRequest(String text, UUID subjectId) {

    public TodoRequest(String text, UUID subjectId) {
        this.text = Objects.requireNonNull(text, "Text must not be null!");
        if (text.isEmpty()) throw new IllegalArgumentException("Text must not be empty!");

        this.subjectId = Objects.requireNonNull(subjectId, "Subject id must not be null!");
        if (subjectId.toString().isEmpty()) throw new IllegalArgumentException("Subject id must not be empty!");
    }

    public Todo toTodo(){
        return Todo.createWithoutId(text, Subject.createOnlyWithId(subjectId));
    }
}
