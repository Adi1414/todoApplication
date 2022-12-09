package com.todo.todoApplication;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int count = 0;

    static {
        todos.add(new Todo(++count, "AA", "Learn Java", LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++count, "AB", "Learn DS", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++count, "AC", "Learn SpringBoot", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++count, "AD", "Learn Reactive Programing", LocalDate.now().plusMonths(4), false));
        todos.add(new Todo(++count, "AE", "Practive interview Question", LocalDate.now().plusMonths(5), false));
    }

    public List<Todo> retrieveTodo(String username) {
        return todos.stream().filter(todo -> todo.equals(username)).collect(Collectors.toList());
    }

    public List<Todo> retrieveAll() {
        return todos;
    }

    public void createTodo(String username, String description, LocalDate localDate, boolean done) {
        todos.add(new Todo(++count, username, description, localDate, done));
    }

    public void deleteTodo(int id) {
        Predicate<? super Todo> predicate = todo -> id == todo.getId();
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteTodo(todo.getId());
        todos.add(todo);
    }
}
