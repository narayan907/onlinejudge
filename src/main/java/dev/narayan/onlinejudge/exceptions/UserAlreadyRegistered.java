package dev.narayan.onlinejudge.exceptions;

public class UserAlreadyRegistered extends RuntimeException{

    public UserAlreadyRegistered(String s)
    {
        super(s);
    }
}
