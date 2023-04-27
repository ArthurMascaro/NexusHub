package br.com.nexushub.external.persistence;

import br.com.nexushub.domain.Todo;
import br.com.nexushub.usecases.todo.gateway.TodoDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoDAOimpl implements TodoDAO {
    @Override
    public Todo create(Todo type) {
        return null;
    }

    @Override
    public Optional<Todo> findOne(UUID key) {
        return Optional.empty();
    }

    @Override
    public List<Todo> findALL() {
        return null;
    }

    @Override
    public Todo update(Todo type) {
        return null;
    }

    @Override
    public Todo deleteByKey(UUID key) {
        return null;
    }

    @Override
    public Todo delete(Todo type) {
        return null;
    }
}
