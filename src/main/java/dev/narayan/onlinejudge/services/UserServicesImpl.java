package dev.narayan.onlinejudge.services;

import dev.narayan.onlinejudge.dto.UserDto;
import dev.narayan.onlinejudge.exceptions.UserAlreadyRegistered;
import dev.narayan.onlinejudge.models.User;
import dev.narayan.onlinejudge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServicesImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDto userDto)  {

        if(userRepository.findByEmail(userDto.getEmail())!=null)
        {
            throw new UserAlreadyRegistered("user already registered with this email Id "+userDto.getEmail());
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPassword(passwordEncoder.encode(userDto.getEmail()));
        user.setActive(false);

        userRepository.save(user);

        return user;
    }
}
