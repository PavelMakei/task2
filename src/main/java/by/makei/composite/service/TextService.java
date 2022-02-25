package by.makei.composite.service;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface TextService {
    //отсортировать абзацы по количеству предложений
    //найти предложения с самым длинным словом
    //удалить из текста все предложения с числом слов меньше заданного
    //Найти в тексте все одинаковые слова без учета регистра и посчитать их количество.
    //Подсчитать в предложении число гласных и согласных букв.

    List<TextComponent> sortParagraphsBySentenceNumber(String text);
    List<TextComponent> findSentencesWithLongestWord(String text) throws CustomException;
    List<TextComponent> removeAllSentencesWithNumberWordsLessThen(String text, int minNumberWords) throws CustomException;
    Map<String,Long> findDuplicateNumber(String text) throws CustomException;
    int countVowel (String text) throws CustomException;
    int countСonsonant(String text) throws CustomException;
}
