package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.UserAuthDTO;
import com.example.finalfinalback3.DTO.UserRegisterDTO;
import com.example.finalfinalback3.Exceptions.PasswordsNotSameException;
import com.example.finalfinalback3.Exceptions.UserAlreadyExistsException;
import com.example.finalfinalback3.Service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity registration(@ModelAttribute("user") UserRegisterDTO user,
                                       Model model) {
        try {
            model.addAttribute("user", authService.registration(user));

            String redirectUrl = "/main"; // URL новой страницы
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", redirectUrl);
            return new ResponseEntity(headers, HttpStatus.FOUND);
            //return ResponseEntity.ok("main");
        } catch (UserAlreadyExistsException | PasswordsNotSameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }

    @GetMapping
    public ResponseEntity authUser(@ModelAttribute("user") UserAuthDTO user){
        try{
            Integer user_id = authService.authUser(user);
            String redirectUrl = "/main"; // URL новой страницы
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", redirectUrl);
            return new ResponseEntity(headers, HttpStatus.FOUND);
        }
        catch (EntityNotFoundException | PasswordsNotSameException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
