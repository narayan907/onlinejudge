package dev.narayan.onlinejudge.services;

import dev.narayan.onlinejudge.dto.UserDto;
import dev.narayan.onlinejudge.events.SuccessfulRegistrationEvent;
import dev.narayan.onlinejudge.exceptions.UserAlreadyRegistered;
import dev.narayan.onlinejudge.exceptions.UserNotFoundException;
import dev.narayan.onlinejudge.exceptions.TokenNotPresent;
import dev.narayan.onlinejudge.models.PasswordResetToken;
import dev.narayan.onlinejudge.models.User;
import dev.narayan.onlinejudge.models.VerificationToken;
import dev.narayan.onlinejudge.repository.PasswordResetTokenRepository;
import dev.narayan.onlinejudge.repository.UserRepository;
import dev.narayan.onlinejudge.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServicesImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

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

        applicationEventPublisher.publishEvent(new SuccessfulRegistrationEvent(user));

        return user;
    }

    @Override
    public boolean validateUser(String token) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken==null)
        {
            throw new TokenNotPresent("verification token not present "+token);
        }


        if(verificationToken.getExpiryTime().getTime()>new Date().getTime())
        {
            User verifiedUser = verificationToken.getUser();
            verifiedUser.setActive(true);
            userRepository.save(verifiedUser);
            verificationTokenRepository.delete(verificationToken);
            return true;
        }
        else
        {
            verificationToken.updateToken();
            return false;
        }

    }

    @Override
    public void requestResetPassword(String email) {

        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserNotFoundException("user not found with this "+email);
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUser(user);

        if(passwordResetToken==null)
        {
            passwordResetToken = new PasswordResetToken(user);
        }
        else
        {
            passwordResetToken.updateToken();
        }
        passwordResetTokenRepository.save(passwordResetToken);

    }

    @Override
    public boolean setNewPassword(String token, String newPassword) {

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if(passwordResetToken==null)
        {
            throw new TokenNotPresent("password reset token not present "+token);
        }

        if(passwordResetToken.getExpiryTime().getTime()>new Date().getTime())
        {
            User user = passwordResetToken.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            passwordResetTokenRepository.delete(passwordResetToken);
            return true;
        }
        else
        {
           return false;
        }

    }


}
