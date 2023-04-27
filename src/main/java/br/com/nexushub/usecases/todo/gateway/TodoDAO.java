package br.com.nexushub.usecases.todo.gateway;

import br.com.nexushub.domain.Todo;
import br.com.nexushub.usecases.util.DAO;

import java.util.UUID;

public interface TodoDAO extends DAO<UUID, Todo> {

}
