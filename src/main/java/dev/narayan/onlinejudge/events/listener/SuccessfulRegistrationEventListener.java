package dev.narayan.onlinejudge.events.listener;

import dev.narayan.onlinejudge.events.SuccessfulRegistrationEvent;
import dev.narayan.onlinejudge.models.User;
import dev.narayan.onlinejudge.models.VerificationToken;
import dev.narayan.onlinejudge.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulRegistrationEventListener implements ApplicationListener<SuccessfulRegistrationEvent> {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {

        User registeredUser = successfulRegistrationEvent.getRegisteredUser();

        VerificationToken verificationToken = new VerificationToken(registeredUser);

        verificationTokenRepository.save(verificationToken);

        //TODO:send email to verification
    }
}
