package com.example.restohelper;

import static org.junit.Assert.*;

import com.example.restohelper.testing.EmailValidator;

import org.junit.Test;

public class EmailValidatorTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"));
    }
}
