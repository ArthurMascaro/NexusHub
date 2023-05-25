package br.com.nexushub.domain;

import java.util.UUID;

public class Subject {

    private UUID id;
    private String name;
    private int difficulty;
    private SubjectColor color;

    private Subject(UUID id, String name, int difficulty, SubjectColor color) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
    }

    private Subject(String name, int difficulty, SubjectColor color) {
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
    }

    private Subject(UUID id) {
        this.id = id;
    }

    public static Subject createWithAllFields(UUID id, String name, int difficulty, SubjectColor color) {
        return new Subject(id, name, difficulty, color);
    }

    public static Subject createWithoutId(String name, int difficulty, SubjectColor color) {
        return new Subject(name, difficulty, color);
    }

    public static Subject createWithOnlyId(UUID id) {
        return new Subject(id);
    }

    public Subject getNewInstanceWithId(UUID id){
        return new Subject(id, name, difficulty, color);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", color=" + color +
                '}';
    }
}
