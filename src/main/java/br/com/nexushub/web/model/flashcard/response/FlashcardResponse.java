package br.com.nexushub.web.model.flashcard.response;

import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.domain.FlashcardStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FlashcardResponse {

    private UUID id;
    private String question;
    private String answer;
    private LocalDate nextRevisionDate;
    private LocalDate lastRevisedDate;
    private FlashcardStatus status;
    private double maturity;
    private UUID deckId;

    private List<UUID> tagsId;

    private FlashcardResponse(UUID id, String question, String answer, LocalDate nextRevisionDate, LocalDate lastRevisedDate, FlashcardStatus status, double maturity, UUID deckId, List<UUID> tagsId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.nextRevisionDate = nextRevisionDate;
        this.lastRevisedDate = lastRevisedDate;
        this.status = status;
        this.maturity = maturity;
        this.deckId = deckId;
        this.tagsId = tagsId;
    }

    public static FlashcardResponse createFromFlashcard(Flashcard flashcard) {
        return new FlashcardResponse(
                flashcard.getId(),
                flashcard.getQuestion(),
                flashcard.getAnswer(),
                flashcard.getNextRevisionDate(),
                flashcard.getLastRevisionDate(),
                flashcard.getStatus(),
                flashcard.getMaturity(),
                flashcard.getDeckId(),
                flashcard.getTagsId()
        );
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

    public UUID getDeckId() {
        return deckId;
    }

    public void setDeckId(UUID deckId) {
        this.deckId = deckId;
    }

    public List<UUID> getTagsId() {
        return tagsId;
    }

    public void setTagsId(List<UUID> tagsId) {
        this.tagsId = tagsId;
    }
}
