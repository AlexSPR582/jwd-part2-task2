package com.alexander.task2.entity;

import com.alexander.task2.exception.ProgramException;
import com.alexander.task2.parser.TextParser;

import java.util.List;

public class Sentence {
    private List<SentenceNode> words;

    public Sentence(String sentence) throws ProgramException {
        if (sentence == null || sentence.isEmpty()) {
            throw new ProgramException("Invalid sentence");
        }
        TextParser parser = TextParser.getInstance();
        this.words = parser.parseSentence(sentence);
    }

    public List<SentenceNode> getNodes() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sentence sentence = (Sentence) o;
        return words.equals(sentence.words);
    }

    @Override
    public int hashCode() {
        return words.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sentence{");
        sb.append("words=").append(words);
        sb.append('}');
        return sb.toString();
    }
}
