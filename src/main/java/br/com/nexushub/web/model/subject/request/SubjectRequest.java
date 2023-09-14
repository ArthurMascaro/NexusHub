package br.com.nexushub.web.model.subject.request;

import br.com.nexushub.domain.Subject;

import java.util.Objects;

public record SubjectRequest(String name, int difficulty, String color) {

    public SubjectRequest(String name, int difficulty, String color) {
        this.name = Objects.requireNonNull(name, "Name must not be null!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty!");

        this.difficulty = Objects.requireNonNull(difficulty, "Difficulty must not be null!");

        this.color = color;
        if (color.isEmpty()) throw new IllegalArgumentException("Color must not be empty!");
    }

    public Subject toSubject() {
        return Subject.createWithoutIdAndOwner(name, difficulty, color);
    }
}
