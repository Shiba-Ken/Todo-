package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    /**
     * 全てのToDoを取得
     */
    public List<Todo> findAllTodos() {
        List<Map<String, Object>> list = todoRepository.findAll();
        if (list == null) {
            return List.of(); // 空リスト返す
        }
        return list.stream()
            .map(map -> {
                Todo todo = new Todo();
                todo.setId(((Number) map.get("id")).longValue());
                todo.setTitle((String) map.get("title"));
                todo.setDescription((String) map.get("description"));
                todo.setStatus((Boolean) map.get("status"));
                return todo;
            })
            .toList();
    }

    /**
     * 新規ToDoを追加
     */
    public void addTodo(String title, String description, Boolean status) {
        todoRepository.addTodo(title, description, status);
    }

    /**
     * 指定IDのToDoを取得
     */
    public Todo getTodoItemById(Long id) {
        Map<String, Object> map = todoRepository.getTodoItemById(id);
        if (map == null) {
            return null;
        }
        Todo todo = new Todo();
        todo.setId(((Number) map.get("id")).longValue());
        todo.setTitle((String) map.get("title"));
        todo.setDescription((String) map.get("description"));
        todo.setStatus((Boolean) map.get("status"));
        return todo;
    }

    /**
     * ToDoを編集
     */
    public void editTodo(Long id, String title, String description, Boolean status) {
        todoRepository.editTodo(id, title, description, status);
    }

    /**
     * ToDoを削除
     */
    public void deleteTodoItem(Long id) {
        todoRepository.deleteTodoItem(id);
    }
}
