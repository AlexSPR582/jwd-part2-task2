package com.alexander.task2.parser;

import com.alexander.task2.entity.*;
import com.alexander.task2.exception.ProgramException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static TextParser parser;

    private static final String SENTENCE_NODE_REGEX = "([\\p{Alpha}а-яА-Я0-9]+-?[\\p{Alpha}а-яА-Я0-9]+)([.!?,:;])?(\\s-)?";
    private static final String SENTENCE_REGEX = "[A-ZА-Я0-9][^!.?]*[!.?]";

    private TextParser() {}

    public static TextParser getInstance() {
        if (parser == null) {
            parser = new TextParser();
        }
        return parser;
    }

    public List<SentenceNode> parseSentence(String text) throws ProgramException {
        List<SentenceNode> nodes = new ArrayList<>();
        Pattern pattern = Pattern.compile(SENTENCE_NODE_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Word word = new Word(matcher.group(1));
            nodes.add(word);
            if (matcher.group(2) != null) {
                PunctuationMark mark = new PunctuationMark(matcher.group(2));
                nodes.add(mark);
            }
            if (matcher.group(3) != null) {
                PunctuationMark mark = new PunctuationMark(matcher.group(3).trim());
                nodes.add(mark);
            }
        }
        return nodes;
    }

    public List<Sentence> parseText(String text) throws ProgramException {
        List<Sentence> sentences = new ArrayList<>();
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            sentences.add(new Sentence(matcher.group()));
        }
        return sentences;
    }
}
