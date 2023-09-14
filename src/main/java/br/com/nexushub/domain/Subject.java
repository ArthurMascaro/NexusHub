package br.com.nexushub.domain;

import java.util.UUID;

public class Subject {

    private UUID id;
    private String name;
    private int difficulty;
    private String color;

    private UUID ownerId;

    private Subject(UUID id, String name, int difficulty, String color, UUID ownerId) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
        this.ownerId = ownerId;
    }

    private Subject(String name, int difficulty, String color, UUID ownerId) {
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
        this.ownerId = ownerId;
    }

    private Subject(String name, int difficulty, String color) {
        this.name = name;
        this.difficulty = difficulty;
        this.color = color;
    }

    private Subject(UUID id) {
        this.id = id;
    }

    public static Subject createWithAllFields(UUID id, String name, int difficulty, String color, UUID ownerId) {
        return new Subject(id, name, difficulty, color, ownerId);
    }

    public static Subject createWithoutId(String name, int difficulty, String color, UUID ownerId) {
        return new Subject(name, difficulty, color, ownerId);
    }

    public static Subject createWithoutIdAndOwner(String name, int difficulty, String color) {
        return new Subject(name, difficulty, color);
    }

    public static Subject createOnlyWithId(UUID id){
        return new Subject(id);
    }
    public Subject getNewInstanceWithId(UUID id){
        return new Subject(id, name, difficulty, color, ownerId);
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}
