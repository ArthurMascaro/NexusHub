package br.com.nexushub.web.model.tag;

import br.com.nexushub.domain.Tag;

import java.util.UUID;

public class TagResponse {

    private UUID id;
    private String name;

    private TagResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TagResponse createFromTag(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
