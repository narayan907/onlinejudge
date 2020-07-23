package dev.narayan.onlinejudge.repository;

import dev.narayan.onlinejudge.models.PasswordResetToken;
import dev.narayan.onlinejudge.models.User;
import dev.narayan.onlinejudge.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

    PasswordResetToken findByUser(User user);

    PasswordResetToken findByToken(String token);


}
