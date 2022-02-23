package by.makei.composite.parser;

import by.makei.composite.entity.LetterLeaf;
import by.makei.composite.entity.PunctuationLeaf;
import by.makei.composite.entity.TextComponent;
import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;

public class LetterApostropheChainParser extends AbstractTextChainParser {

    @Override
    public void parse(TextComponent component, String data) {
        char[] letters = data.toCharArray();
        for (char letter : letters) {
            try {
                if (letter == '\'') {
                    component.add(new PunctuationLeaf(letter));
                } else {
                    component.add(new LetterLeaf(letter));
                }
            } catch (CustomException e) {
                logger.log(Level.ERROR, "Can't be reached", e);
            }
        }

    }
}
