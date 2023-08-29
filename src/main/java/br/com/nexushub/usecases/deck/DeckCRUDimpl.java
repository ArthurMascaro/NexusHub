package br.com.nexushub.usecases.deck;

import br.com.nexushub.configuration.auth.jwt.IAuthenticationFacade;
import br.com.nexushub.domain.Deck;
import br.com.nexushub.usecases.deck.gateway.DeckDAO;
import br.com.nexushub.usecases.subject.SubjectCRUD;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import br.com.nexushub.web.model.deck.request.DeckRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeckCRUDimpl implements DeckCRUD {

    private final DeckDAO deckDAO;

    private final SubjectCRUD subjectCRUD;

    private final IAuthenticationFacade authenticationFacade;

    public DeckCRUDimpl(DeckDAO deckDAO, SubjectCRUD subjectCRUD, IAuthenticationFacade authenticationFacade) {
        this.deckDAO = deckDAO;
        this.subjectCRUD = subjectCRUD;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Deck createNewDeck(DeckRequest deckRequest) {
        Deck deck = deckRequest.toDeck();
        deck.setOwnerId(authenticationFacade.getUserAuthenticatedId());
        return deckDAO.saveNewDeck(deck);
    }

    @Override
    public Deck findDeckById(UUID id) {
        return deckDAO.findDeckById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found deck with id: " + id));
    }

    @Override
    public Deck updateDeckById(UUID id, DeckRequest deckRequest) {
        Deck deckUpdate = deckRequest.toDeck();

        Deck deck = findDeckById(id);

        if (deckUpdate.getParentDeckId() != null)
            findDeckById(deckUpdate.getParentDeckId());

        if (deckUpdate.getSubjectId() != null)
            subjectCRUD.findSubjectById(deckUpdate.getSubjectId());

        if (!deck.getOwnerId().equals(authenticationFacade.getUserAuthenticatedId()))
            throw new RuntimeException("You dont have permission to update this deck");

        deckUpdate.setId(id);
        return deckDAO.updateDeck(deckUpdate);
    }

    @Override
    public List<Deck> findAllDeckByUserId() {
        return deckDAO.findAllDecksByUserId(authenticationFacade.getUserAuthenticatedId());
    }

    @Override
    public List<Deck> getAllDeckChildrenById(UUID deckId) {
        return deckDAO.getAllDeckChildren(deckId);
    }

    @Override
    public Deck deleteDeckById(UUID id) {
        Deck deck = findDeckById(id);

        if (!deck.getOwnerId().equals(authenticationFacade.getUserAuthenticatedId()))
            throw new RuntimeException("You dont have permission to delete this deck");

        return deckDAO.deleteDeckById(id);
    }
}
