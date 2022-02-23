package by.makei.composite.parser;

import by.makei.composite.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextChainParser {
    public static final Logger logger = LogManager.getLogger();
    protected AbstractTextChainParser nextParser;

    protected AbstractTextChainParser() {}

    public abstract void parse(TextComponent component, String data);

}
