package com.badgu.apiworkshop.service;

import com.badgu.apiworkshop.model.Todo;
import com.badgu.apiworkshop.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodo() {
        List<Todo> todos = new ArrayList<>();
        this.todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    public Optional<Todo> getTodoById(Long id) {
        return this.todoRepository.findById(id);
    }

    public List<Todo> getTodoByDoneFlag(boolean done) {
        List<Todo> todos = new ArrayList<>();
        todos.addAll(this.todoRepository.findByDone(done));
        return todos;
    }

    public Optional<Todo> deleteTodoById(Long id) {
        Optional<Todo> deletedTodo = getTodoById(id);
        if (deletedTodo.isPresent())
            this.todoRepository.deleteById(id);
        return deletedTodo;
    }

    public Todo addTodo(Todo todo) {
        return this.todoRepository.save(todo);
    }
}
