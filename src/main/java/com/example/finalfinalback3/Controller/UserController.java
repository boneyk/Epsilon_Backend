package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.UserAuthDTO;
import com.example.finalfinalback3.Exceptions.PasswordsNotSameException;
import com.example.finalfinalback3.Exceptions.UserAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.UserNotFoundException;
import com.example.finalfinalback3.Repository.UserRepository;
import com.example.finalfinalback3.Service.UserService;
import com.example.finalfinalback3.Entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registration(@ModelAttribute("user") UserEntity user,
                                       Model model){
        try{
            userService.registration(user);

            String redirectUrl = "/main"; // URL новой страницы
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", redirectUrl);

            return new ResponseEntity(headers, HttpStatus.FOUND);

            //model.addAttribute("user", userService.registration(user));
            //return ResponseEntity.ok("main");
        }
        catch (UserAlreadyExistsException | PasswordsNotSameException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }


    @GetMapping
    public ResponseEntity authUser(@ModelAttribute("user") UserAuthDTO user){
        try{
            return ResponseEntity.ok(userService.authUser(user));
        }
        catch (EntityNotFoundException | PasswordsNotSameException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Технические шоколаки");
        }
    }

}
