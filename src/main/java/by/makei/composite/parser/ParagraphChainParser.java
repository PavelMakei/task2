package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParagraphChainParser extends AbstractTextChainParser {
    private static final String PARAGRAPH_SPLITTER_REGEX = "(^|\\n)(\\t|\\s{4})";


    public ParagraphChainParser() {
        this.nextParser = new SentenceChainParser();
    }

    private List<String> paragraphList = new ArrayList();

    @Override
    public void parse(TextComponent component, String data) {
        paragraphList = Stream.of(data.split(PARAGRAPH_SPLITTER_REGEX))
                .filter(p -> !p.isEmpty())
                .toList();

        for (var paragraph : paragraphList) {
            TextComponent paragraphConcreteComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphConcreteComponent);
            nextParser.parse(paragraphConcreteComponent, paragraph);

        }

    }
}
