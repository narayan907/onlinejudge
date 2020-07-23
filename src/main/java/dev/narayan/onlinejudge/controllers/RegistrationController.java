package dev.narayan.onlinejudge.controllers;

import dev.narayan.onlinejudge.dto.UserDto;
import dev.narayan.onlinejudge.dto.UserReponseDto;
import dev.narayan.onlinejudge.models.User;
import dev.narayan.onlinejudge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/confirm")
    public ResponseEntity validateUser(@RequestParam String token)
    {
        boolean isValidationSuccessful =userService.validateUser(token);

        if(isValidationSuccessful) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Validated Successfully!!");
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Token expired. A new Token is sent, please try again");
        }
    }

    @GetMapping("/user/reset-password")
    public ResponseEntity requestResetPassword(@RequestParam String email)
    {
        userService.requestResetPassword(email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Reset token will be sent over mail");

    }

    @PostMapping("/user/new-password")
    public ResponseEntity setNewPassword(@RequestParam String token, @RequestBody String newPassword)
    {

        boolean passwordResetSuccessful=userService.setNewPassword(token,newPassword);

        if(passwordResetSuccessful)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Password Changed Successfully !!");
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Password reset token expired, please initiate new request");
        }
    }
}
