package br.com.nexushub.usecases.tag;

import br.com.nexushub.domain.Tag;

import java.util.List;
import java.util.UUID;

public interface TagCRUD {

    Tag createNewTag(String name);

    Tag findTagById(UUID id);

    Tag updateTagById(UUID id, String name);

    Tag deleteTagById(UUID id);

    List<Tag> findAllTagsByUserId();

    List<Tag> findAllTagsByFlashcardId(UUID flashcardId);
}
