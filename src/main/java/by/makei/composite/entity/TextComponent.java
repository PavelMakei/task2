package by.makei.composite.entity;

import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface TextComponent {
    static final Logger logger = LogManager.getLogger();

    default boolean add(TextComponent component) throws CustomException {
        logger.log(Level.ERROR, "Operation is not supported");
        throw new CustomException("Operation is not supported");
    }

    default boolean remove(TextComponent component) throws CustomException {
        logger.log(Level.ERROR, "Operation is not supported");
        throw new CustomException("Operation is not supported");
    }

    default List<TextComponent> getChildren() throws CustomException {
        logger.log(Level.ERROR, "Operation is not supported");
        throw new CustomException("Operation is not supported");
    }


//    default TextComponent getChildByIndex(int index) throws CustomException {
//    logger.log(Level.ERROR, "Operation is not supported");
//        throw new CustomException("Operation is not supported");
//    }

    String toString(); // the main method
}

