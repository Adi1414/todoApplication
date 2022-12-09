package com.todo.todoApplication;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "todos")
    public String getTodos(ModelMap model) {
        List<Todo> todos = todoService.retrieveAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model) {
        String username = getUserName(model);
        Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "add-todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "add-todo";
        }
        String username = getUserName(model);
        todoService.createTodo(username, todo.getDescription(),
                todo.getLocalDate(), false);
        return "redirect:todos";
    }

    @RequestMapping(value ="delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:todos";
    }

    @RequestMapping(value ="update-todo")
    public String showUpdateTodo(@RequestParam int id, ModelMap modelMap){
        Todo todo = todoService.findById(id);
        modelMap.addAttribute("todo", todo);
        return "add-todo";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "add-todo";
        }
        String username = getUserName(model);
        todo.setUsername(username);
        todo.setLocalDate(todo.getLocalDate());
        todoService.updateTodo(todo);
        return "redirect:todos";
    }

    private String getUserName(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
