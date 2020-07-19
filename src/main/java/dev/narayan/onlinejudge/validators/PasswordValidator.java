package dev.narayan.onlinejudge.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        s=s.trim();
        int lowercaseAlphabetCount = 0;
        int upperCaseAlphabetCount = 0;
        int numberCount = 0;
        int specialCharacterCount = 0;

        for(int i=0;i<s.length();i++)
        {
            int ch = (int)s.charAt(i);
            if(ch>=(int)'0' && ch<=(int)'9')
            {
                numberCount++;
            }
            else if(ch>=(int)'a' && ch<=(int)'z')
            {
                lowercaseAlphabetCount++;
            }
            else if(ch>=(int)'A' && ch<=(int)'Z')
            {
                upperCaseAlphabetCount++;
            }
            else
            {
                specialCharacterCount++;
            }
        }
        return (lowercaseAlphabetCount!=0 && upperCaseAlphabetCount!=0 && numberCount!=0 && specialCharacterCount!=0);
    }
}
