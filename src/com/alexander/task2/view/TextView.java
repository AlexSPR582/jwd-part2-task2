package com.alexander.task2.view;

import com.alexander.task2.entity.*;

import java.util.List;

public class TextView {
    private static TextView view;

    private TextView() {}

    public static TextView getInstance() {
        if (view == null) {
            view = new TextView();
        }
        return view;
    }

    public String textView(Text text) {
        StringBuilder builder = new StringBuilder();
        List<Sentence> sentences = text.getText();
        for (Sentence sentence: sentences) {
            List<SentenceNode> nodes = sentence.getNodes();
            for (int i = 0; i < nodes.size(); i++) {
                SentenceNode node = nodes.get(i);
                if (i == nodes.size() - 1) {
                    builder.append(node.getNode());
                    break;
                }
                if (node instanceof PunctuationMark) {
                    String punctuationMark = node.getNode();
                    if (!punctuationMark.equals("-")) {
                        builder.append(node.getNode()).append(" ");
                    } else {
                        builder.append(" ").append(punctuationMark).append(" ");
                    }
                } else if (node instanceof Word) {
                    SentenceNode nextNode = nodes.get(i + 1);
                    if (nextNode instanceof Word) {
                        builder.append(node.getNode()).append(" ");
                    } else if (nextNode instanceof PunctuationMark) {
                        builder.append(node.getNode());
                    }
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
