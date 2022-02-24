package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextComposite;
import by.makei.composite.entity.TextLeaf;
import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordAndPunctuationChainParser extends AbstractTextChainParser {
    private static final String WORD_PUNCTUATION_REGEXP = "[\\wа-яА-ЯёЁ']+|[\\p{Punct}\\u2026]";
    private static final String WORD_REGEXP = "[\\wа-яА-ЯёЁ']+"; //include ' like don't
    private static final String PUNCTUATION_REGEXP = "[\\p{Punct}|\\u2026]"; // !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~...

    public WordAndPunctuationChainParser() {
        nextParser = new LetterApostropheChainParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(WORD_PUNCTUATION_REGEXP);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                if (matcher.group(i).matches(WORD_REGEXP)) {
                    TextComponent wordConcreteComponent = new TextComposite(TextComponentType.WORD);
                    try {
                        component.add(wordConcreteComponent);
                    } catch (CustomException e) {
                        logger.log(Level.ERROR, "Can't be reached", e);
                    }
                    nextParser.parse(wordConcreteComponent, matcher.group(i));
                    logger.log(Level.DEBUG, "word - {}", matcher.group(i));

                } else if (matcher.group(i).matches(PUNCTUATION_REGEXP)) {
                    TextLeaf punctuationLeaf = new TextLeaf(TextComponentType.PUNCTUATION_MARK, matcher.group(i).charAt(i));
                    try {
                        component.add(punctuationLeaf);
                    } catch (CustomException e) {
                        logger.log(Level.ERROR, "Can't be reached", e);
                    }

                    logger.log(Level.DEBUG, "punctuation - {}", matcher.group(i).charAt(i));
                }
            }
        }


    }
}
