package br.com.nexushub;

import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.web.model.flashcard.request.FlashcardAnswer;

import java.time.LocalDate;
import java.util.UUID;

public class MainTestFlascard {

    public static void main(String[] args) {
        /*Flashcard flashcard = Flashcard.createNewFlashcard("question 1", "answer 2", null, null);
        Flashcard flashcard2 = Flashcard.createNewFlashcard("question 2", "answer 3", null, null);
        Flashcard flashcard3 = Flashcard.createNewFlashcard("question 4", "answer 5", null, null);

        flashcard.updateReviewDate(FlashcardAnswer.AGAIN);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());
        flashcard.updateReviewDate(FlashcardAnswer.GOOD);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());
        flashcard.updateReviewDate(FlashcardAnswer.EASY);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());
        flashcard.updateReviewDate(FlashcardAnswer.GOOD);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());
        flashcard.updateReviewDate(FlashcardAnswer.GOOD);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());
        flashcard.updateReviewDate(FlashcardAnswer.GOOD);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());
        flashcard.updateReviewDate(FlashcardAnswer.AGAIN);
        System.out.println(flashcard.getNextRevisionDate().toString());
        System.out.println(flashcard.getMaturity());*/

        UUID deckId = UUID.randomUUID();
        Flashcard flashcard4 = Flashcard.createWithAllArgs(UUID.randomUUID(), "What is the capital of France?", "Paris", null, null, null, 0, deckId, null);

        // Simulate responses for the flashcard

        /*flashcard4.processResponse(FlashcardAnswer.EASY, LocalDate.now());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.HARD, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.EASY, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.EASY, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.EASY, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.EASY, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.EASY, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        flashcard4.processResponse(FlashcardAnswer.EASY, flashcard4.getNextRevisionDate());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());*/

        // Print the updated information
        System.out.println("Question: " + flashcard4.getQuestion());
        System.out.println("Answer: " + flashcard4.getAnswer());
        System.out.println("Next Revision Date: " + flashcard4.getNextRevisionDate());
        System.out.println("Last Revision Date: " + flashcard4.getLastRevisionDate());
        System.out.println("Status: " + flashcard4.getStatus());
        System.out.println("Maturity: " + flashcard4.getMaturity());
    }
}
