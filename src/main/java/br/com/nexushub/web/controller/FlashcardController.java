package br.com.nexushub.web.controller;

import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.usecases.flashcard.FlashcardCRUD;
import br.com.nexushub.web.model.flashcard.request.FlashcardRequest;
import br.com.nexushub.web.model.flashcard.response.FlashcardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/flashcards")
@RestController
public class FlashcardController {

    private final FlashcardCRUD flashcardCRUD;

    public FlashcardController(FlashcardCRUD flashcardCRUD) {
        this.flashcardCRUD = flashcardCRUD;
    }

    @PostMapping("/save")
    public ResponseEntity<FlashcardResponse> createNewFlashcard(
            @RequestBody FlashcardRequest flashcardRequest) {
        Flashcard flashcard = flashcardCRUD.createNewFlashcard(flashcardRequest);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardResponse> findFlashcardById(
            @PathVariable("id") UUID id){
        Flashcard flashcard = flashcardCRUD.findFlashcardById(id);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }

    @GetMapping("/{deckId}/all")
    public ResponseEntity<List<FlashcardResponse>> findAllFlashcardByDeckId(
            @PathVariable("deckId") UUID deckId) {
        List<Flashcard> flashcards = flashcardCRUD.findAllFlashcardByDeckId(deckId);
        return ResponseEntity.ok(flashcards.stream()
                .map(FlashcardResponse::createFromFlashcard)
                .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlashcardResponse> deleteFlashcardById(@PathVariable("id") UUID id) {
        Flashcard flashcard = flashcardCRUD.deleteFlashcardById(id);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashcardResponse> updateFlashcardById(@PathVariable("id") UUID id,
                                                        @RequestBody FlashcardRequest flashcardRequest) {
        Flashcard flashcard = flashcardCRUD.updateFlashcardById(id, flashcardRequest);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }
}
