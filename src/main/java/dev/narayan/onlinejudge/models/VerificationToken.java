package dev.narayan.onlinejudge.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class VerificationToken {

    private static final int VALIDITY_TIME=2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expiryTime;

    public VerificationToken()
    {

    }

    public VerificationToken(User user) {
        this.token = generateRandomUniqueToken();
        this.user = user;
        this.expiryTime = calculateExpiryTime();
    }

    public void updateToken()
    {
        this.token = generateRandomUniqueToken();
        this.expiryTime = calculateExpiryTime();
    }

    private String generateRandomUniqueToken()
    {
        return UUID.randomUUID().toString();
    }

    private Date calculateExpiryTime()
    {
        Calendar calendar = Calendar.getInstance();

        Date currentTime = new Date();

        calendar.setTimeInMillis(currentTime.getTime());
        calendar.add(Calendar.MINUTE,VALIDITY_TIME);

        return new Date(calendar.getTime().getTime());

    }
}
