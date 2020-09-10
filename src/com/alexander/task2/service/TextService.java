package com.alexander.task2.service;

import com.alexander.task2.dao.TextDao;
import com.alexander.task2.entity.*;
import com.alexander.task2.exception.DaoException;
import com.alexander.task2.exception.ProgramException;
import com.alexander.task2.exception.ServiceException;
import com.alexander.task2.dao.impl.TextDaoImpl;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextService {
    private static TextService textService;

    private static final String CONSONANTS = "^[БВГДЖЗЙКЛМНПРСТФХШЩЧЦЬЪбвгджзйклмнпрстфхшщчцъь" +
            "BCDFGHJKLMNPQRSTVWXZbcdfghjklmnpqrstvwxz]";

    private TextService() {}

    public static TextService getInstance() {
        if (textService == null) {
            textService = new TextService();
        }
        return textService;
    }

    public Text sortSentencesByWordsAmount() throws ServiceException {
        TextDao reader = TextDaoImpl.getInstance();
        Text text;
        try {
            text = reader.read();
            Comparator<Sentence> comparator = (s1, s2) -> s1.getNodes().size() - s2.getNodes().size();
            text.getText().sort(comparator);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return text;
    }

    public Text deleteWords(int length) throws ServiceException {
        TextDao reader = TextDaoImpl.getInstance();
        Text text;
        try {
            text = reader.read();
            Pattern pattern = Pattern.compile(CONSONANTS);
            for (Sentence sentence: text.getText()) {
                List<SentenceNode> nodes = sentence.getNodes();
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i) instanceof Word) {
                        String word = nodes.get(i).getNode();
                        Matcher matcher = pattern.matcher(word);
                        if (matcher.find() && word.length() == length) {

                            nodes.remove(nodes.get(i));

                        }
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return text;
    }

    public Set<String> findUniqueWordInQuestions(int wordLength) throws ServiceException {
        TextDao reader = TextDaoImpl.getInstance();
        Text text;
        Set<String> uniqueWords = new HashSet<>();
        try {
            text = reader.read();
            for (Sentence sentence: text.getText()) {
                List<SentenceNode> nodes = sentence.getNodes();
                if (nodes.get(nodes.size() - 1).getNode().equals("?")) {
                    for (SentenceNode node: nodes) {
                        if (node instanceof Word && node.getNode().length() == wordLength) {
                            uniqueWords.add(node.getNode());
                        }
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return uniqueWords;
    }

    public Text swapWordsInSentences() throws ServiceException  {
        TextDao reader = TextDaoImpl.getInstance();
        Text text;
        try {
            text = reader.read();
            List<Sentence> sentences = text.getText();
            for (Sentence sentence: sentences) {
                List<SentenceNode> nodes = sentence.getNodes();
                SentenceNode firstWord = nodes.get(0);
                SentenceNode lastWord = nodes.get(nodes.size() - 2);
                nodes.set(0, lastWord);
                nodes.set(nodes.size() - 2, firstWord);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return text;
    }

    public Text replaceWords(int sentenceNumber, int wordLength, String replacement)
            throws ServiceException {
        TextDao reader = TextDaoImpl.getInstance();
        Text text;
        try {
            text = reader.read();
            List<Sentence> sentences = text.getText();
            if (sentenceNumber < sentences.size()) {
                Sentence sentence = sentences.get(sentenceNumber);
                List<SentenceNode> nodes = sentence.getNodes();
                for (int i = 0; i < nodes.size(); i++) {
                    SentenceNode node = nodes.get(i);
                    if (node instanceof Word && node.getNode().length() == wordLength) {
                        nodes.set(i, new Word(replacement));
                    }
                }
            }
        } catch (DaoException | ProgramException e) {
            throw new ServiceException(e);
        }
        return text;
    }
}
