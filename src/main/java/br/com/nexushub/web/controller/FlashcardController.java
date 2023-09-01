package br.com.nexushub.web.controller;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.usecases.flashcard.FlashcardCRUD;
import br.com.nexushub.web.model.deck.response.DeckResponse;
import br.com.nexushub.web.model.deck.response.DeckResponseWithFlashcards;
import br.com.nexushub.web.model.flashcard.request.FlashcardAnswer;
import br.com.nexushub.web.model.flashcard.request.FlashcardRequest;
import br.com.nexushub.web.model.flashcard.response.FlashcardResponse;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping("/{id}/add-tag/{tagId}")
    public ResponseEntity<FlashcardResponse> addTagToFlashcard(
            @PathVariable("id") UUID flashcardId,
            @PathVariable("tagId") UUID tagId) {
        Flashcard flashcard = flashcardCRUD.addTagToFlashcard(flashcardId, tagId);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardResponse> findFlashcardById(
            @PathVariable("id") UUID id){
        Flashcard flashcard = flashcardCRUD.findFlashcardById(id);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }

    @GetMapping("/deck/{id}")
    public ResponseEntity<List<DeckResponseWithFlashcards>> findAllFlashcardByParentDeckId(
            @PathVariable("id") UUID parentId){
        Map<Deck, List<Flashcard>> map = flashcardCRUD.AllFlashcardForDeckAndChildren(parentId);
        return ResponseEntity.ok(map.entrySet().stream()
                .map(entry -> DeckResponseWithFlashcards.createFromDeck(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
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

        @PatchMapping("/{id}")
    public ResponseEntity<FlashcardResponse> processResponseFlashcard(@PathVariable("id") UUID id,
                                                                      @RequestBody String response){
        FlashcardAnswer answer = FlashcardAnswer.valueOf(response);
        Flashcard flashcard = flashcardCRUD.updateRevisionDate(id, answer);
        return ResponseEntity.ok(FlashcardResponse.createFromFlashcard(flashcard));
    }
}
