package br.com.nexushub.usecases.flashcard;

import br.com.nexushub.configuration.auth.jwt.IAuthenticationFacade;
import br.com.nexushub.domain.Deck;
import br.com.nexushub.domain.Flashcard;
import br.com.nexushub.domain.Tag;
import br.com.nexushub.usecases.deck.DeckCRUD;
import br.com.nexushub.usecases.deck.DeckCRUDimpl;
import br.com.nexushub.usecases.deck.gateway.DeckDAO;
import br.com.nexushub.usecases.flashcard.gateway.FlashcardDAO;
import br.com.nexushub.usecases.subject.SubjectCRUD;
import br.com.nexushub.usecases.tag.TagCRUD;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import br.com.nexushub.web.model.flashcard.request.FlashcardRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlashcardCRUDimpl implements FlashcardCRUD {

    private final FlashcardDAO flashcardDAO;

    private final TagCRUD tagCRUD;

    private final DeckCRUD deckCRUD;

    public FlashcardCRUDimpl(FlashcardDAO flashcardDAO, TagCRUD tagCRUD, DeckCRUD deckCRUD) {
        this.flashcardDAO = flashcardDAO;
        this.tagCRUD = tagCRUD;
        this.deckCRUD = deckCRUD;
    }

    @Override
    public Flashcard createNewFlashcard(FlashcardRequest flashcardRequest) {
        Flashcard flashcard = flashcardRequest.toFlashcard();
        return flashcardDAO.saveNewFlashcard(flashcard);
    }

    @Override
    public Flashcard findFlashcardById(UUID id) {
        return flashcardDAO.findFlashcardById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found flashcard with id: " + id));
    }

    @Override
    public Flashcard updateFlashcardById(UUID id, FlashcardRequest flashcardRequest) {
        Flashcard flashcardUpdate = flashcardRequest.toFlashcard();

        Flashcard flashcard = flashcardDAO.findFlashcardById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found flashcard with id: " + id));

        if (flashcardUpdate.getDeckId() != null)
            deckCRUD.findDeckById(flashcardUpdate.getDeckId());

        flashcardUpdate.setId(id);
        return flashcardDAO.updateFlashcard(flashcardUpdate);
    }

    @Override
    public Flashcard deleteFlashcardById(UUID id) {
        flashcardDAO.findFlashcardById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found flashcard with id: " + id));

        return flashcardDAO.deleteFlashcardById(id);
    }

    @Override
    public List<Flashcard> findAllFlashcardByDeckId(UUID deckId) {
        return flashcardDAO.findAllFlashcardByDeckId(deckId);
    }

    @Override
    public Flashcard addTagToFlashcard(UUID flashcardId, UUID tagId) {
        Flashcard flashcard = flashcardDAO.findFlashcardById(flashcardId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found flashcard with id: " + flashcardId));
        tagCRUD.findTagById(tagId);
        return flashcardDAO.addTagToFlashcard(flashcard, tagId);
    }

    @Override
    public Map<Deck, List<Flashcard>> AllFlashcardForDeckAndChildren(UUID parentId) {
        List<Deck> childDecks = new ArrayList<>();
        Set<UUID> visited = new HashSet<>();
        Map<Deck, List<Flashcard>> allFlashcards = new HashMap<>();
        findChildDecksRecursive(parentId, deckCRUD.findAllDeckByUserId(), childDecks, visited);

        childDecks.add(0, deckCRUD.findDeckById(parentId));

        for (Deck deck : childDecks){
            List<Flashcard> flashcards = flashcardDAO.findAllFlashcardByDeckId(deck.getId());
            allFlashcards.putIfAbsent(deck, flashcards);
        }
        return allFlashcards;
    }

    public void findChildDecksRecursive(UUID parentId, List<Deck> allDecks, List<Deck> childDecks, Set<UUID> visited){
        visited.add(parentId);

        for (Deck deck : allDecks) {
            if (deck.getParentDeckId() != null && deck.getParentDeckId().equals(parentId)) {
                childDecks.add(deck);

                if (!visited.contains(deck.getId())) {
                    findChildDecksRecursive(deck.getId(), allDecks, childDecks, visited);
                }
            }
        }
    }
}
