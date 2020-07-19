package dev.narayan.onlinejudge.controllers;

import dev.narayan.onlinejudge.dto.UserDto;
import dev.narayan.onlinejudge.dto.UserReponseDto;
import dev.narayan.onlinejudge.models.User;
import dev.narayan.onlinejudge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity registerUser(@Valid @RequestBody UserDto userDto)
    {
        User user = userService.registerUser(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserReponseDto(user.getId(),user.getFullName(),user.getEmail(),user.isActive()));
    }
}
