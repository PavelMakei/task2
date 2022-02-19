package by.makei.composite.entity;

import by.makei.composite.exception.CustomException;

import java.util.List;

public interface TextComponent {
    default boolean add(TextComponent component) throws CustomException {
        throw new CustomException("Operation is not supported");
    }

    default boolean remove(TextComponent component) throws CustomException {
        throw new CustomException("Operation is not supported");
    }

    default List<TextComponent> getChildren() throws CustomException {
        throw new CustomException("Operation is not supported");
    }


//    default TextComponent getChildByIndex(int index) throws CustomException {
//        throw new CustomException("Operation is not supported");
//    }

    String toString(); // the main method
}

