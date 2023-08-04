package br.com.nexushub.web.controller;

import br.com.nexushub.domain.Tag;
import br.com.nexushub.usecases.tag.TagCRUD;
import br.com.nexushub.web.model.tag.TagResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/tags")
@RestController
public class TagController {

    private final TagCRUD tagCRUD;

    public TagController(TagCRUD tagCRUD) {
        this.tagCRUD = tagCRUD;
    }

    @PostMapping("/save")
    public ResponseEntity<TagResponse> createNewTag(
            @RequestBody String name){
        Tag tag = tagCRUD.createNewTag(name);
        return ResponseEntity.ok(TagResponse.createFromTag(tag));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> findTagById(
            @PathVariable("id") UUID id){
        Tag tag = tagCRUD.findTagById(id);
        return ResponseEntity.ok(TagResponse.createFromTag(tag));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TagResponse>> findAllTagsByUserId() {
        List<Tag> tags = tagCRUD.findAllTagsByUserId();
        return ResponseEntity.ok(tags.stream()
                .map(TagResponse::createFromTag)
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagResponse> deleteTagById(@PathVariable("id") UUID id) {
        Tag tag = tagCRUD.deleteTagById(id);
        return ResponseEntity.ok(TagResponse.createFromTag(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTagById(@PathVariable("id") UUID id,
                                                     @RequestBody String name) {
        Tag tag = tagCRUD.updateTagById(id, name);
        return ResponseEntity.ok(TagResponse.createFromTag(tag));
    }
}
