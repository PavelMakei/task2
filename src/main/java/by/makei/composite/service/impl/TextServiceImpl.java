package by.makei.composite.service.impl;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextComposite;
import by.makei.composite.exception.CustomException;
import by.makei.composite.parser.ParagraphChainParser;
import by.makei.composite.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;

public class TextServiceImpl implements TextService {
    public static final Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "(?ui)[aeiouyаеёуионыэюя]";
    private static final String CONSONANT_REGEX = "(?ui)[qwrtpsdfghjklzxcvbnmбвгджзйклмнпрстфхцчшщ]";
    private static final TextServiceImpl instance = new TextServiceImpl();

    private TextServiceImpl() {
    }

    public static TextServiceImpl getInstance() {
        return instance;
    }


    @Override
    public List<TextComponent> sortParagraphsBySentenceNumber(String text) {
        TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
        ParagraphChainParser parser = new ParagraphChainParser();
        parser.parse(textComposite, text);
        List<TextComponent> paragraphs = textComposite.getChildren();
        paragraphs.sort((paragraph1, paragraph2) -> {
            int result = 0;
            try {
                result = paragraph1.getChildren().size() - paragraph2.getChildren().size();
            } catch (CustomException e) {
                logger.log(Level.WARN, "Can't be reached");
            }
            return result;
        });
        return paragraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(String text) throws CustomException {
        TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
        ParagraphChainParser parser = new ParagraphChainParser();
        parser.parse(textComposite, text);
        List<TextComponent> paragraphs = textComposite.getChildren();
        List<TextComponent> sentences;
        List<TextComponent> lexemes;
        List<TextComponent> words;
        List<TextComponent> leafs;

        //get size of the longest word
        int sizeOfLongestWord = 0;
        for (var paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            for (var sentence : sentences) {
                lexemes = sentence.getChildren();
                for (var lexeme : lexemes) {
                    words = lexeme.getChildren();
                    for (var word : words) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            leafs = word.getChildren();
                            if (sizeOfLongestWord < leafs.size()) {
                                sizeOfLongestWord = leafs.size();
                            }
                        }
                    }
                }
            }
        }
//collect sentences which contain the longest word
        Set<TextComponent> collectedSentences = new HashSet();
        for (var paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            for (var sentence : sentences) {
                lexemes = sentence.getChildren();
                for (var lexeme : lexemes) {
                    words = lexeme.getChildren();
                    for (var word : words) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            leafs = word.getChildren();
                            if (leafs.size() == sizeOfLongestWord) {
                                collectedSentences.add(sentence);
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>(collectedSentences);
    }

    @Override
    public List<TextComponent> removeAllSentencesWithNumberWordsLessThen(String text, int minNumberWords) throws CustomException {
        TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
        ParagraphChainParser parser = new ParagraphChainParser();
        parser.parse(textComposite, text);
        List<TextComponent> paragraphs = textComposite.getChildren();
        List<TextComponent> sentences = new ArrayList<>();
        List<TextComponent> lexemes;
        List<TextComponent> words;
        TextComponent sentence;


        for (var paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            for (int i = 0; i < sentences.size(); i++) {
                sentence = sentences.get(i);
                int countOfWords = 0;
                lexemes = sentence.getChildren();
                for (var lexeme : lexemes) {
                    words = lexeme.getChildren();
                    for (var word : words) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            countOfWords++;
                        }
                    }
                }
                if (countOfWords < minNumberWords) {
                    paragraph.remove(sentence);
                }
            }
        }
        return paragraphs;
    }

    @Override
    public Map<String, Integer> findDuplicateNumber(String text) throws CustomException {
        ArrayList<String> wordList = getListOfWord(text);
        Set<String> uniqueWords = new HashSet<>(wordList);
        Map<String, Integer> result = new HashMap<>();
        uniqueWords.forEach(new Consumer<String>() {
            @Override
            public void accept(String str) {
                result.put(str, (int) wordList.stream().filter(word -> word.equals(str)).count());
            }
        });
        return result;
    }

    @Override
    public int countVowel(String text) throws CustomException {
        return getCountOfWords(text, VOWEL_REGEX);
    }

    @Override
    public int countСonsonant(String text) throws CustomException {
        return getCountOfWords(text, CONSONANT_REGEX);
    }


    private int getCountOfWords(String text, String regex) throws CustomException {
        int count = 0;
        TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
        ParagraphChainParser parser = new ParagraphChainParser();
        parser.parse(textComposite, text);
        List<TextComponent> paragraphs = textComposite.getChildren();
        List<TextComponent> sentences;
        List<TextComponent> lexemes;
        List<TextComponent> words;
        List<TextComponent> leafs;

        for (var paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            for (var sentence : sentences) {
                lexemes = sentence.getChildren();
                for (var lexeme : lexemes) {
                    words = lexeme.getChildren();
                    for (var word : words) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            leafs = word.getChildren();
                            for (var leaf : leafs) {
                                if (leaf.getType().equals(TextComponentType.LETTER)) {
                                    if (leaf.toString().matches(regex)) {
                                        count++;
                                        logger.log(Level.DEBUG, "- {}",leaf.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private ArrayList<String> getListOfWord(String text) throws CustomException {
        ArrayList<String> wordList = new ArrayList<>();
        TextComposite textComposite = new TextComposite(TextComponentType.PARAGRAPH);
        ParagraphChainParser parser = new ParagraphChainParser();
        parser.parse(textComposite, text);
        List<TextComponent> paragraphs = textComposite.getChildren();
        List<TextComponent> sentences;
        List<TextComponent> lexemes;
        List<TextComponent> words;

        for (var paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            for (var sentence : sentences) {
                lexemes = sentence.getChildren();
                for (var lexeme : lexemes) {
                    words = lexeme.getChildren();
                    for (var word : words) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            wordList.add(word.toString().toLowerCase(Locale.ROOT));
                        }
                    }
                }
            }
        }
        return wordList;
    }
}
