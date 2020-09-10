package com.alexander.task2.dao.impl;

import com.alexander.task2.dao.TextDao;
import com.alexander.task2.entity.Text;
import com.alexander.task2.exception.DaoException;
import com.alexander.task2.exception.ProgramException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextDaoImpl implements TextDao {
    private static TextDaoImpl reader;
    private static final String PATH = "resourses/file.txt";

    private TextDaoImpl() {}

    public static TextDaoImpl getInstance() {
        if (reader == null) {
            reader = new TextDaoImpl();
        }
        return reader;
    }

    public Text read() throws DaoException {
        StringBuilder textBuilder = new StringBuilder();
        Text text;
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH));
            for (String line: lines) {
                textBuilder.append(line).append(" ");
            }
            text = new Text(textBuilder.toString());
        } catch (IOException | ProgramException e) {
            throw new DaoException("Read exception", e);
        }
        return text;
    }
}
