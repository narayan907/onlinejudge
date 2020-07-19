package dev.narayan.onlinejudge.dto;

import dev.narayan.onlinejudge.validators.ValidEmail;
import dev.narayan.onlinejudge.validators.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {


    @NotNull
    @NotBlank
    @Size(min=2,message = "name should be min 2 characters")
    @Size(max = 15, message = "name should be max 15 characters")
    private String fullName;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    @Size(min=6,message = "password should be min 6 characters")
    @Size(max = 15, message = "password should be max 15 characters")
    @ValidPassword
    private String password;

}
