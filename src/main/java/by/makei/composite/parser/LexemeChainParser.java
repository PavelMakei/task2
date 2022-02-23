package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComposite;
import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;

public class LexemeChainParser extends AbstractTextChainParser {
    private static final String LEXEME_SPLITTER_REGEX = "\\s";
    private static final String BIT_OPERATION_REGEX_MATHER = "([\\d+\\&\\|\\^\\(\\~\\<+\\>\\)]){3,}";



    public LexemeChainParser() {
        this.nextParser = new WordAndPunctuationChainParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        for (int i = 0; i<lexemes.length ; i++){
            TextComponent lexemeConcreteComponent = new TextComposite("LEXEME");
            //TODO check if lexeme is bit operation and parse it

            if(lexemes[i].matches(BIT_OPERATION_REGEX_MATHER)){
                System.out.println(lexemes[i]);
            }

            try {
                component.add(lexemeConcreteComponent);
            } catch (CustomException e) {
                logger.log(Level.ERROR, "Can't be reached", e);
            }

            nextParser.parse(lexemeConcreteComponent,lexemes[i]);
        }

    }
}
