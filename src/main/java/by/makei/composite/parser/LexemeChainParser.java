package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComponentType;
import by.makei.composite.entity.TextComposite;
import by.makei.composite.util.BitOperationUtil;
import by.makei.composite.util.impl.BitOperationImpl;

public class LexemeChainParser extends AbstractTextChainParser {
    private static final String LEXEME_SPLITTER_REGEX = "\\s";
    private static final String BIT_OPERATION_REGEX_MATHER = "([\\d+\\&\\|\\^\\(\\~<+\\>+\\)]){2,}";

    public LexemeChainParser() {
        this.nextParser = new WordAndPunctuationChainParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        for (int i = 0; i < lexemes.length; i++) {
            TextComponent lexemeConcreteComponent = new TextComposite(TextComponentType.LEXEME);

            if (lexemes[i].matches(BIT_OPERATION_REGEX_MATHER)) {
                BitOperationUtil bitUtil = BitOperationImpl.getInstance();
                String calculatedBitOperations;
                calculatedBitOperations = bitUtil.parseAndCalculateBitOperation(lexemes[i]);
                lexemes[i] = calculatedBitOperations;
            }
            component.add(lexemeConcreteComponent);
            nextParser.parse(lexemeConcreteComponent, lexemes[i]);
        }

    }
}
