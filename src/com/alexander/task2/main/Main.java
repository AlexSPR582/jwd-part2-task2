package com.alexander.task2.main;

import com.alexander.task2.entity.Text;
import com.alexander.task2.exception.ServiceException;
import com.alexander.task2.service.TextService;
import com.alexander.task2.view.TextView;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws ServiceException {
        TextService service = TextService.getInstance();
        TextView view = TextView.getInstance();

        System.out.println("Перестановка слов в начале и конце предложения");
        Text text1 = service.swapWordsInSentences();
        System.out.println(view.textView(text1));

        System.out.println("\nСортировка предложений по количеству слов");
        Text text2 = service.sortSentencesByWordsAmount();
        System.out.println(view.textView(text2));

        System.out.println("\nУдаление слов заданной длины, начинающихся на согласную букву");
        Text text3 = service.deleteWords(4);
        System.out.println(view.textView(text3));

        System.out.println("\nЗамена слов заданной длины в заданном предложении");
        Text text4 = service.replaceWords(0, 4, "Python");
        System.out.println(view.textView(text4));

        System.out.println("Найти в вопросительных предложениях слова заданной длины без повторений");
        Set<String> words = service.findUniqueWordInQuestions(4);
        System.out.println(words);
    }
}
