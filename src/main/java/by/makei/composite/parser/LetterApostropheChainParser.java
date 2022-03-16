package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextLeaf;


public class LetterApostropheChainParser extends AbstractTextChainParser {
    private static final char APOSTROPHE = '\'';

    @Override
    public void parse(TextComponent component, String data) {
        char[] letters = data.toCharArray();
        for (char character : letters) {
                if (character == APOSTROPHE) {
                    component.add(new TextLeaf(TextComponentType.PUNCTUATION_MARK, character));
                } else {
                    component.add(new TextLeaf(TextComponentType.LETTER, character));
                }

        }

    }
}
