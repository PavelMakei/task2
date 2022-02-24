package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextComposite;
import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceChainParser extends AbstractTextChainParser {
    private static final String SENTENCE_REGEX = "([A-Z]|[А-ЯЁ]).+?(\\.|\\!|\\?|\\u2026)";
    // Нчинается с заглавной и заканчивается .!?...
    //добавить возможность пробельных символов в конце строки? "(\s|$)"


    public SentenceChainParser() {
        this.nextParser = new LexemeChainParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()){
            String sentence = matcher.group();
            TextComponent sentenceConcreteComponent = new TextComposite(TextComponentType.SENTENCE);
            try {
                component.add(sentenceConcreteComponent);
            } catch (CustomException e) {
                logger.log(Level.ERROR, "Can't be reached", e);
            }
            nextParser.parse(sentenceConcreteComponent,sentence);
        }

    }
}
