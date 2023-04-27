package br.com.nexushub.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Flashcard {

    private UUID id;
    private String front;
    private String back;
    private LocalDate nextRevisitionDate;
    private LocalDate lastRevisedDate;
    private FlashcardStatus status;
    private double maturity;
    private Deck deck;

    private ArrayList<Tag> tags;

    public Flashcard(UUID id, String front, String back, LocalDate nextRevisitionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, Deck deck, ArrayList<Tag> tags) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.nextRevisitionDate = nextRevisitionDate;
        this.lastRevisedDate = lastRevisedDate;
        this.status = status;
        this.maturity = maturity;
        this.deck = deck;
        this.tags = tags;
    }

    public Flashcard(String front, String back, LocalDate nextRevisitionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, Deck deck, ArrayList<Tag> tags) {
        this.front = front;
        this.back = back;
        this.nextRevisitionDate = nextRevisitionDate;
        this.lastRevisedDate = lastRevisedDate;
        this.status = status;
        this.maturity = maturity;
        this.deck = deck;
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public LocalDate getNextRevisitionDate() {
        return nextRevisitionDate;
    }

    public void setNextRevisitionDate(LocalDate nextRevisitionDate) {
        this.nextRevisitionDate = nextRevisitionDate;
    }

    public LocalDate getLastRevisedDate() {
        return lastRevisedDate;
    }

    public void setLastRevisedDate(LocalDate lastRevisedDate) {
        this.lastRevisedDate = lastRevisedDate;
    }

    public FlashcardStatus getStatus() {
        return status;
    }

    public void setStatus(FlashcardStatus status) {
        this.status = status;
    }

    public double getMaturity() {
        return maturity;
    }

    public void setMaturity(double maturity) {
        this.maturity = maturity;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
