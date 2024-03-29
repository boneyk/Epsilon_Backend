package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.AccountInfoChangeDTO;
import com.example.finalfinalback3.Security.AuthService;
import com.example.finalfinalback3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
       return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping("/info")
    public ResponseEntity changeAccountInfo(@RequestBody AccountInfoChangeDTO info,
                                          @RequestParam String token) {
        return new ResponseEntity(authService.changeAccountInfo(info, token), HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity showAccountInfo(@RequestParam String token) {
        return new ResponseEntity(userService.showAccountInfo(token), HttpStatus.OK);
    }

}
