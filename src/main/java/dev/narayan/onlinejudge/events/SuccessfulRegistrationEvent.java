package dev.narayan.onlinejudge.events;

import dev.narayan.onlinejudge.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SuccessfulRegistrationEvent extends ApplicationEvent {

    private final User registeredUser;

    public SuccessfulRegistrationEvent(User user)
    {
        super(user);
        this.registeredUser=user;
    }
}
