package com.example.todo.controller;

import com.example.todo.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Bu sınıf REST API'dir, JSON veri döner
@RequestMapping("/api/todos") // URL'ler bu kökten başlar: localhost:8080/api/todos
public class TodoController {

    @Autowired
    private TodoService todoService;

    //  GET: Tüm Todo'ları getir
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    //  GET: Belirli ID'li Todo'yu getir
    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    //  POST: Yeni Todo oluştur
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    //  PUT: Var olan Todo'yu güncelle
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    //  DELETE: Todo sil
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
