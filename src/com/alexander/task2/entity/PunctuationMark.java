package com.alexander.task2.entity;

import com.alexander.task2.exception.ProgramException;
import com.alexander.task2.validator.TextValidator;

public class PunctuationMark extends SentenceNode {
    private String punctuationMark;

    public PunctuationMark(String punctuationMark) throws ProgramException {
        TextValidator validator = TextValidator.getInstance();
        if (!validator.punctuationMarkValidation(punctuationMark)) {
            throw new ProgramException("Invalid punctuation mark");
        }
        this.punctuationMark = punctuationMark;
    }

    @Override
    public String getNode() {
        return punctuationMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PunctuationMark mark = (PunctuationMark) o;
        return punctuationMark.equals(mark.punctuationMark);
    }

    @Override
    public int hashCode() {
        return punctuationMark.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PunctuationMark{");
        sb.append("punctuationMark='").append(punctuationMark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
