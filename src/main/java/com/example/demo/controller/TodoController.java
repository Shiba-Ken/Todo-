package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Todo;
import com.example.demo.form.TodoForm;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String getAllTodos(Model model) {
        List<Todo> todos = todoService.findAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/create")
    public String showCreateTodoForm(Model model) {
        model.addAttribute("todoForm", new TodoForm());
        return "create-todo";
    }

    @PostMapping("/addTodo")
    public String submitForm(@Valid @ModelAttribute TodoForm todoForm, 
                           BindingResult result, 
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "create-todo";
        }

        try {
            todoService.addTodo(todoForm.getTitle(), todoForm.getDescription(), todoForm.getStatus());
            redirectAttributes.addFlashAttribute("successMessage", "Todo created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create todo: " + e.getMessage());
        }
        
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{id}")
    public String showEditTodo(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Todo todo = todoService.getTodoItemById(id);
            if (todo == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Todo not found with ID: " + id);
                return "redirect:/";
            }

            TodoForm form = new TodoForm();
            form.setId(todo.getId());
            form.setTitle(todo.getTitle());
            form.setDescription(todo.getDescription());
            form.setStatus(todo.getStatus());

            model.addAttribute("todoForm", form);
            return "edit-todo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error loading todo: " + e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable Long id,
                          @Valid @ModelAttribute("todoForm") TodoForm todoForm,
                          BindingResult result, 
                          Model model, 
                          RedirectAttributes redirectAttributes) {

        // IDをフォームに設定（重要：これがないとフォームのIDが空になる可能性）
        todoForm.setId(id);

        // バリデーションエラーがある場合は編集画面に戻る
        if (result.hasErrors()) {
            model.addAttribute("todoForm", todoForm);
            return "edit-todo";
        }

        try {
            todoService.editTodo(id, todoForm.getTitle(), todoForm.getDescription(), todoForm.getStatus());
            redirectAttributes.addFlashAttribute("successMessage", "Todo updated successfully!");
            return "redirect:/";
        } catch (Exception e) {
            // エラーが発生した場合の処理
            model.addAttribute("errorMessage", "Failed to update todo: " + e.getMessage());
            model.addAttribute("todoForm", todoForm);
            return "edit-todo";
        }
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            todoService.deleteTodoItem(id);
            redirectAttributes.addFlashAttribute("successMessage", "Todo deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete todo: " + e.getMessage());
        }
        return "redirect:/";
    }
}