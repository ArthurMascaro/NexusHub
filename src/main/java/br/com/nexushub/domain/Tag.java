package br.com.nexushub.domain;

import java.util.UUID;

public class Tag {

    private UUID id;
    private String name;
    private UUID ownerId;

    private Tag(UUID id, String name, UUID ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    private Tag(String name, UUID ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    private Tag(UUID id){
        this.id = id;
    }

    private Tag(String name){
        this.name = name;
    }

    public static Tag createNewTagWithAllArgs(UUID id, String name, UUID ownerId) {
        return new Tag(id, name, ownerId);
    }

    public static Tag createNewTag(String name, UUID ownerId) {
        return new Tag(name, ownerId);
    }

    public static Tag createWithId(UUID id){
        return new Tag(id);
    }

    public static Tag createWithName(String name){
        return new Tag(name);
    }

    public Tag newInstanceWithId(UUID id){
        return new Tag(id, this.name, this.ownerId);
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

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}
