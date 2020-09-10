package com.alexander.task2.entity;

import com.alexander.task2.exception.ProgramException;
import com.alexander.task2.validator.TextValidator;

public class Word extends SentenceNode{
    private String word;

    public Word(String word) throws ProgramException {
        TextValidator validator = TextValidator.getInstance();
        if (!validator.wordValidation(word)) {
            throw new ProgramException("Invalid word");
        }
        this.word = word;
    }

    @Override
    public String getNode() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word1 = (Word) o;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Word{");
        sb.append("word='").append(word).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
