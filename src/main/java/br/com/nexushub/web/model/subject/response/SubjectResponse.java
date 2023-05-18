package br.com.nexushub.web.model.subject.response;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;

import java.util.UUID;

public class SubjectResponse {
    private String name;
    private int difficulty;
    private SubjectColor color;

    private SubjectResponse(String name, int difficulty, SubjectColor color) {
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
    }

    public static SubjectResponse createFromSubject(Subject subject) {
        return new SubjectResponse(
                subject.getName(),
                subject.getDifficulty(),
                subject.getColor()
        );
    }

    public String getName() {
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

    public SubjectColor getColor() {
        return color;
    }

    public void setColor(SubjectColor color) {
        this.color = color;
    }

}
