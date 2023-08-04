package br.com.nexushub.usecases.tag;

import br.com.nexushub.configuration.auth.jwt.IAuthenticationFacade;
import br.com.nexushub.domain.Tag;
import br.com.nexushub.usecases.tag.gateway.TagDAO;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagCRUDimpl implements TagCRUD{

    private final TagDAO tagDAO;

    private final IAuthenticationFacade authenticationFacade;

    public TagCRUDimpl(TagDAO tagDAO, IAuthenticationFacade authenticationFacade) {
        this.tagDAO = tagDAO;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Tag createNewTag(String name) {
        Tag tag = Tag.createNewTag(name, authenticationFacade.getUserAuthenticatedId());
        return tagDAO.saveNewTag(tag);
    }

    @Override
    public Tag findTagById(UUID id) {
        return tagDAO.findTagById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found tag with id: " + id));
    }

    @Override
    public Tag updateTagById(UUID id, String name) {
        Tag tag = tagDAO.findTagById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found tag with id: " + id));
        tag.setName(name);

        if (!tag.getOwnerId().equals(authenticationFacade.getUserAuthenticatedId()))
            throw new RuntimeException("You dont have permission to update this tag");

        return tagDAO.updateTag(tag);
    }

    @Override
    public Tag deleteTagById(UUID id) {
        tagDAO.findTagById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found tag with id: " + id));

        return tagDAO.deleteTagById(id);
    }

    @Override
    public List<Tag> findAllTagsByUserId() {
        return tagDAO.findAllTagsByOwnerId(authenticationFacade.getUserAuthenticatedId());
    }

    @Override
    public List<Tag> findAllTagsByFlashcardId(UUID flashcardId) {
        return tagDAO.findALlTagsByFlashcardId(flashcardId);
    }
}
