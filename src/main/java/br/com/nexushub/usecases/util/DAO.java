package br.com.nexushub.usecases.util;

import java.util.List;
import java.util.Optional;

public interface DAO<K, T> {
    T create(T type);

    Optional<T> findOne(K  key);

    List<T> findALL();

    T update(T type);

    T deleteByKey(K key);

    T delete(T type);

}
