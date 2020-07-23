package dev.narayan.onlinejudge.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String s)
    {
        super(s);
    }
}
