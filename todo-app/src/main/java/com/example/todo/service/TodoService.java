package com.example.todo.service;

import com.example.todo.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bu sınıf bir servis katmanıdır (Spring yönetir)
public class TodoService {

    @Autowired
    private TodoRepository todoRepository; // Veritabanı işlemleri için kullanılır

    // ✅ Tüm Todo'ları getirir
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // ✅ Yeni Todo oluşturur
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // ✅ Belirli bir ID'ye sahip Todo'yu getirir
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    // ✅ Todo'yu günceller
    public Todo updateTodo(Long id, Todo todo) {
        return todoRepository.findById(id).map(existingTodo -> {
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setCompleted(todo.isCompleted());
            return todoRepository.save(existingTodo);
        }).orElse(null); // Eğer bulunamazsa null döner
    }

    // ✅ Todo'yu siler
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
