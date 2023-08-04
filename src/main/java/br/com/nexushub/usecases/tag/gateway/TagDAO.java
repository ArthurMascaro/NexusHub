package br.com.nexushub.usecases.tag.gateway;

import br.com.nexushub.domain.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagDAO {

    Tag saveNewTag(Tag tag);

    Tag updateTag(Tag tag);

    Optional<Tag> findTagById(UUID id);

    Tag deleteTagById(UUID id);

    List<Tag> findAllTagsByOwnerId(UUID ownerId);

    List<Tag> findALlTagsByFlashcardId(UUID flashcardId);
}
