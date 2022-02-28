package by.makei.composite.entity;

import by.makei.composite.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface TextComponent {
    Logger logger = LogManager.getLogger();

    TextComponentType getType();

    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    List<TextComponent> getChildren();



//    default TextComponent getChildByIndex(int index) throws CustomException {
//    logger.log(Level.ERROR, "Operation is not supported");
//        throw new CustomException("Operation is not supported");
//    }

    String toString(); // the main method
}

