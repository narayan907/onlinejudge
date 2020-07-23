package dev.narayan.onlinejudge.services;

import dev.narayan.onlinejudge.dto.UserDto;
import dev.narayan.onlinejudge.models.User;

public interface UserService {

    public User registerUser(UserDto userDto) ;

    boolean validateUser(String token);

    void requestResetPassword(String email);

    boolean setNewPassword(String token, String newPassword);
}
