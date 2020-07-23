package com.badgu.apiworkshop.repository;

import com.badgu.apiworkshop.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findByDone(boolean done);

}