package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * データベースから全てのToDoを取得するメソッド
     **/
    public List<Map<String, Object>> findAll() {
        String query = "SELECT * FROM todos";
        try {
            return jdbcTemplate.queryForList(query);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 新しいTodoをデータベースに追加するメソッド
     **/
    public void addTodo(String title, String description, Boolean status) {
        String query = "INSERT INTO todos (title, description, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, title, description, status);
    }

    /**
     * IDから1レコードを取得するメソッド
     **/
    public Map<String, Object> getTodoItemById(Long id) {
        String query = "SELECT * FROM todos WHERE id = ?";
        try {
            return jdbcTemplate.queryForMap(query, id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 修正するメソッド
     **/
    public void editTodo(Long id, String title, String description, Boolean status) {
        String query = "UPDATE todos SET title = ?, description = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(query, title, description, status, id);
    }

    /**
     * 削除するメソッド
     **/
    public void deleteTodoItem(Long id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
