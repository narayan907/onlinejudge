package dev.narayan.onlinejudge.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "password should contain at least 1 lowercase alphabet , 1 uppercase alphabet , 1 number, 1 special char";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
