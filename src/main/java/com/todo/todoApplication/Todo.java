package com.todo.todoApplication;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public class Todo {

    private int id;
    private String username;
    @Size(min =10, message = "Enter at least 10 letters")
    private String description;
    private LocalDate localDate;
    private boolean done;

    public Todo(int id, String username, String description, LocalDate localDate, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.localDate = localDate;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", localDate=" + localDate +
                ", done=" + done +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
