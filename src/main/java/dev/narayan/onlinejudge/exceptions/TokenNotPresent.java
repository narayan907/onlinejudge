package dev.narayan.onlinejudge.exceptions;

import dev.narayan.onlinejudge.models.VerificationToken;

public class TokenNotPresent extends RuntimeException {

    public TokenNotPresent(String s)
    {
        super(s);
    }
}
