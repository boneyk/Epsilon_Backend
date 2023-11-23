package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.Entity.TodoEntity;
import com.example.finalfinalback3.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;
    @PostMapping()
    public ResponseEntity createTodo(@RequestBody TodoEntity todo,
                                     @RequestParam Integer userId){

        try{
            return ResponseEntity.ok(todoService.create(todo, userId));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Технические шоколадки");
        }
    }

    @PutMapping()
    public ResponseEntity completeTodo(@RequestParam Integer id){
        try{
            return ResponseEntity.ok(todoService.setComplete(id));
        }

        catch (Exception e){
            return ResponseEntity.badRequest().body("Технические шоколадки");
        }

    }
}
