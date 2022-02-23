package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComposite;
import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParagraphChainParser extends AbstractTextChainParser {
    private static final String PARAGRAPH_SPLITTER_REGEX = "(^|\\n)(\\t|\\s{4})";
    private List<String> paragraphList = new ArrayList();

    public ParagraphChainParser() {
        this.nextParser = new SentenceChainParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        paragraphList = Stream.of(data.split(PARAGRAPH_SPLITTER_REGEX))
                .filter(p -> !p.isEmpty())
                .toList();

        for (var paragraph : paragraphList) {
            TextComponent paragraphConcreteComponent = new TextComposite("PARAGRAPH");
            try {
                component.add(paragraphConcreteComponent);
            } catch (CustomException e) {
                logger.log(Level.ERROR, "Can't be reached", e);
            }
            nextParser.parse(paragraphConcreteComponent, paragraph);

        }

    }
}
