package com.practice.student.validator;

import com.practice.student.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return pattern.matcher(phoneNumber).matches();
    }
}
