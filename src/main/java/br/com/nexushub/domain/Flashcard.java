package br.com.nexushub.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Flashcard {

    private UUID id;
    private String question;
    private String answer;
    private LocalDate nextRevisionDate;
    private LocalDate lastRevisionDate;
    private FlashcardStatus status;
    private double maturity;
    private UUID deckId;

    private ArrayList<UUID> tagsId;

    private Flashcard(UUID id, String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisionDate, FlashcardStatus status, double maturity, UUID deckId, ArrayList<UUID> tagsId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.nextRevisionDate = nextRevisionDate;
        this.lastRevisionDate = lastRevisionDate;
        this.status = status;
        this.maturity = maturity;
        this.deckId = deckId;
        this.tagsId = tagsId;
    }

    private Flashcard(String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisionDate, FlashcardStatus status, double maturity, UUID deckId, ArrayList<UUID> tagsId) {
        this.question = question;
        this.answer = answer;
        this.nextRevisionDate = nextRevisionDate;
        this.lastRevisionDate = lastRevisionDate;
        this.status = status;
        this.maturity = maturity;
        this.deckId = deckId;
        this.tagsId = tagsId;
    }

    private Flashcard(String question, String answer, UUID deckId, ArrayList<UUID> tagsId){
        this.question = question;
        this.answer = answer;
        this.deckId = deckId;
        this.tagsId = tagsId;
        this.nextRevisionDate = LocalDate.now();
        this.lastRevisionDate = LocalDate.now();
        this.status = FlashcardStatus.NEW;
        this.maturity= 0;
        this.tagsId = new ArrayList<>();
    }

    private Flashcard(UUID id){
        this.id = id;
    }

    public static Flashcard createWithAllArgs(UUID id, String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, UUID deckId, ArrayList<UUID> tags){
        return new Flashcard(id, question, answer, nextRevisionDate, lastRevisedDate, status, maturity, deckId, tags);
    }

    public static Flashcard createWithoutId(String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, UUID deckId, ArrayList<UUID> tags){
        return new Flashcard(question, answer, nextRevisionDate, lastRevisedDate, status, maturity, deckId, tags);
    }

    public static Flashcard createNewFlashcard(String question, String answer, UUID deckId, ArrayList<UUID> tags){
        return new Flashcard(question, answer, deckId, tags);
    }

    public static Flashcard createWithId(UUID id){
        return new Flashcard(id);
    }

    public Flashcard getNewInstanceWithId(UUID id){
        return new Flashcard(id, question, answer, nextRevisionDate, lastRevisionDate, status, maturity, deckId, tagsId);
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

    public LocalDate getLastRevisionDate() {
        return lastRevisionDate;
    }

    public void setLastRevisionDate(LocalDate lastRevisionDate) {
        this.lastRevisionDate = lastRevisionDate;
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

    public UUID getDeckId() {
        return deckId;
    }

    public void setDeckId(UUID deckId) {
        this.deckId = deckId;
    }

    public ArrayList<UUID> getTagsId() {
        return tagsId;
    }

    public void setTagsId(ArrayList<UUID> tagsId) {
        this.tagsId = tagsId;
    }

    public void addTag(UUID tagId){
        if (this.tagsId == null)
            this.tagsId = new ArrayList<>();
        this.tagsId.add(tagId);
    }
}
