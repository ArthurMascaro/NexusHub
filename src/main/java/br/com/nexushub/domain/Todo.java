package br.com.nexushub.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Todo {

    private UUID id;
    private String text;
    private LocalDate date;
    private TodoStatus status;
    private Subject subject;

    public Todo(UUID id, String text, LocalDate date, TodoStatus status, Subject subject) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.status = status;
        this.subject = subject;
    }

    public Todo(String text, LocalDate date, TodoStatus status, Subject subject) {
        this.text = text;
        this.date = date;
        this.status = status;
        this.subject = subject;
    }

    public Todo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
