package com.alexander.task2.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {
    private static TextValidator validator;

    private static final String WORD_REGEX = "^[\\p{Alpha}а-яА-Я\\p{Digit}]+-?[\\p{Alpha}а-яА-Я\\p{Digit}]+$";
    private static final String PUNCTUATION_MARK_REGEX = "^[.!?,:;\\-]+$";

    private TextValidator() {}

    public static TextValidator getInstance() {
        if (validator == null) {
            validator = new TextValidator();
        }
        return validator;
    }

    public boolean wordValidation(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    public boolean punctuationMarkValidation(String punctuationMark) {
        if (punctuationMark == null || punctuationMark.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(PUNCTUATION_MARK_REGEX);
        Matcher matcher = pattern.matcher(punctuationMark);
        return matcher.find();
    }
}
