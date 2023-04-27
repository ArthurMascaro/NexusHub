package br.com.nexushub.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Session {

    private UUID id;
    private LocalDate dateInitial;
    private LocalDate dateFinal;
    private Subject subject;
    private UUID userId;
    private SequenceItem sequenceItem;
    private Deck deck;

    public Session(UUID id, LocalDate dateInitial, LocalDate dateFinal, Subject subject, UUID userId, SequenceItem sequenceItem, Deck deck) {
        this.id = id;
        this.dateInitial = dateInitial;
        this.dateFinal = dateFinal;
        this.subject = subject;
        this.userId = userId;
        this.sequenceItem = sequenceItem;
        this.deck = deck;
    }

    public Session(LocalDate dateInitial, LocalDate dateFinal, Subject subject, UUID userId, SequenceItem sequenceItem, Deck deck) {
        this.dateInitial = dateInitial;
        this.dateFinal = dateFinal;
        this.subject = subject;
        this.userId = userId;
        this.sequenceItem = sequenceItem;
        this.deck = deck;
    }

    public Session(UUID id, LocalDate dateInitial, LocalDate dateFinal) {
        this.id = id;
        this.dateInitial = dateInitial;
        this.dateFinal = dateFinal;
    }

    public Session(LocalDate dateInitial, LocalDate dateFinal) {
        this.dateInitial = dateInitial;
        this.dateFinal = dateFinal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(LocalDate dateInitial) {
        this.dateInitial = dateInitial;
    }

    public LocalDate getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(LocalDate dateFinal) {
        this.dateFinal = dateFinal;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public SequenceItem getSequenceItem() {
        return sequenceItem;
    }

    public void setSequenceItem(SequenceItem sequenceItem) {
        this.sequenceItem = sequenceItem;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
