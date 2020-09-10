package com.alexander.task2.entity;

import com.alexander.task2.exception.ProgramException;
import com.alexander.task2.parser.TextParser;

import java.util.List;

public class Text {
    private List<Sentence> text;

    public Text(String text) throws ProgramException {
        if (text == null || text.isEmpty()) {
            throw new ProgramException("Invalid text");
        }
        TextParser parser = TextParser.getInstance();
        this.text = parser.parseText(text);
    }

    public List<Sentence> getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Text text1 = (Text) o;
        return text.equals(text1.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Text{");
        sb.append("text=").append(text);
        sb.append('}');
        return sb.toString();
    }
}
