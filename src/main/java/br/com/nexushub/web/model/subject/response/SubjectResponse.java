package br.com.nexushub.web.model.subject.response;

import br.com.nexushub.domain.Subject;

import java.util.UUID;

public class SubjectResponse {

    private UUID id;
    private String name;
    private int difficulty;
    private String color;

    private SubjectResponse(UUID id, String name, int difficulty, String color) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
    }

    public static SubjectResponse createFromSubject(Subject subject) {
        return new SubjectResponse(
                subject.getId(),
                subject.getName(),
                subject.getDifficulty(),
                subject.getColor()
        );
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
