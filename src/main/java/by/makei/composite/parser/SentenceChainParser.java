package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceChainParser extends AbstractTextChainParser {
    private static final String SENTENCE_REGEX = "(?s).*?(?:[?!.](\\n|\\s+|$))";
    // Начинается с заглавной и заканчивается .!?... + " " или \n

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
                component.add(sentenceConcreteComponent);
            nextParser.parse(sentenceConcreteComponent,sentence);
        }

    }
}
