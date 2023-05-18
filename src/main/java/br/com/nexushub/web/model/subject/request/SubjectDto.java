package br.com.nexushub.web.model.subject.request;

import br.com.nexushub.domain.SubjectColor;

import java.util.Objects;

public record SubjectDto(String name, int difficulty, SubjectColor color) {

    public SubjectDto(String name, int difficulty, SubjectColor color) {
        this.name = Objects.requireNonNull(name, "Name must not be null!");
        if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty!");

        this.difficulty = Objects.requireNonNull(difficulty, "Difficulty must not be null!");

        this.color = color;
        if (color.name().isEmpty()) throw new IllegalArgumentException("Color must not be empty!");
    }
}
