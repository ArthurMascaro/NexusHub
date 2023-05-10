package br.com.nexushub.web.model.subject.request;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;

public class SubjectUpdateRequest {

    private String name;
    private int difficulty;
    private SubjectColor color;

    private SubjectUpdateRequest(String name, int difficulty, SubjectColor color) {
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
    }

    public static SubjectUpdateRequest createWithAllFields(String name, int difficulty, SubjectColor color) {
        return new SubjectUpdateRequest(name, difficulty, color);
    }

    public static Subject toSubject(SubjectUpdateRequest request) {
        return Subject.createWithoutId(request.getName(), request.getDifficulty(), request.getColor());
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public SubjectColor getColor() {
        return color;
    }
}
