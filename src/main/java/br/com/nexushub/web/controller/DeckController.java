package br.com.nexushub.web.controller;

import br.com.nexushub.domain.Deck;
import br.com.nexushub.usecases.deck.DeckCRUD;
import br.com.nexushub.web.model.deck.request.DeckRequest;
import br.com.nexushub.web.model.deck.response.DeckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/decks")
@RestController
public class DeckController {

    private final DeckCRUD deckCRUD;

    public DeckController(DeckCRUD deckCRUD) {
        this.deckCRUD = deckCRUD;
    }

    @PostMapping("/save")
    public ResponseEntity<DeckResponse> createNewDeck(
            @RequestBody DeckRequest deckRequest) {
        Deck deck = deckCRUD.createNewDeck(deckRequest);
        return ResponseEntity.ok(DeckResponse.createFromDeck(deck));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckResponse> findDeckById(
            @PathVariable("id") UUID id){
        Deck deck = deckCRUD.findDeckById(id);
        return ResponseEntity.ok(DeckResponse.createFromDeck(deck));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeckResponse>> findAllDeck() {
        List<Deck> decks = deckCRUD.findAllDeckByUserId();
        return ResponseEntity.ok(decks.stream()
                .map(DeckResponse::createFromDeck)
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeckResponse> deleteDeckById(@PathVariable("id") UUID id) {
        Deck deck = deckCRUD.deleteDeckById(id);
        return ResponseEntity.ok(DeckResponse.createFromDeck(deck));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeckResponse> updateDeckById(@PathVariable("id") UUID id,
                                                       @RequestBody DeckRequest deckRequest) {
        Deck deck = deckCRUD.updateDeckById(id, deckRequest);
        return ResponseEntity.ok(DeckResponse.createFromDeck(deck));
    }

}
