package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextLeaf;
import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;

public class LetterApostropheChainParser extends AbstractTextChainParser {

    @Override
    public void parse(TextComponent component, String data) {
        char[] letters = data.toCharArray();
        for (char character : letters) {
            try {
                if (character == '\'') {
                    component.add(new TextLeaf(TextComponentType.PUNCTUATION_MARK, character));
                } else {
                    component.add(new TextLeaf(TextComponentType.LETTER, character));
                }
            } catch (CustomException e) {
                logger.log(Level.ERROR, "Can't be reached", e);
            }
        }

    }
}
