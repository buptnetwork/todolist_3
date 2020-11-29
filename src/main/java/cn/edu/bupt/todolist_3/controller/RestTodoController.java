package cn.edu.bupt.todolist_3.controller;

import cn.edu.bupt.todolist_3.entity.Todo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class RestTodoController {

    @GetMapping
    List<Todo> list(HttpSession session, @SessionAttribute(required = false) List<Todo> todos){
        if (todos == null) {
            todos = new ArrayList<Todo>();
            session.setAttribute("todos",todos);
        }
        return todos;
    }

    @PostMapping
    String add(@SessionAttribute List<Todo> todos, @RequestBody Todo todo){
        todos.add(todo);
        return "success";
    }

    @PutMapping("/{todoId}")
    String edit(@SessionAttribute List<Todo> todos, @RequestBody Todo todo,
                @PathVariable int todoId){
        todos.remove(todoId);
        todos.add(todoId,todo);
        return "success";
    }

    @DeleteMapping("/{todoId}")
    String delete(@SessionAttribute List<Todo> todos, @PathVariable int todoId){
        todos.remove(todoId);
        return "success";
    }
}
