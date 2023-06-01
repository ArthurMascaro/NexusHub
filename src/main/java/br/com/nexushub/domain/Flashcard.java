package br.com.nexushub.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Flashcard {

    private UUID id;
    private String question;
    private String answer;
    private LocalDate nextRevisionDate;
    private LocalDate lastRevisedDate;
    private FlashcardStatus status;
    private double maturity;
    private Deck deck;

    private ArrayList<Tag> tags;

    public Flashcard(UUID id, String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, Deck deck, ArrayList<Tag> tags) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.nextRevisionDate = nextRevisionDate;
        this.lastRevisedDate = lastRevisedDate;
        this.status = status;
        this.maturity = maturity;
        this.deck = deck;
        this.tags = tags;
    }

    public Flashcard(String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, Deck deck, ArrayList<Tag> tags) {
        this.question = question;
        this.answer = answer;
        this.nextRevisionDate = nextRevisionDate;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getNextRevisionDate() {
        return nextRevisionDate;
    }

    public void setNextRevisionDate(LocalDate nextRevisionDate) {
        this.nextRevisionDate = nextRevisionDate;
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
