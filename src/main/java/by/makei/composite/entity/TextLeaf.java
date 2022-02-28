package by.makei.composite.entity;


import org.apache.logging.log4j.Level;
import java.util.List;

public class TextLeaf implements TextComponent {
    private char character;
    private TextComponentType type;

    public TextLeaf(TextComponentType type, char character) {
        this.type = type;
        this.character = character;
    }

    @Override
    public TextComponentType getType() {return type;}


    @Override
    public boolean add(TextComponent component) {
        {
            logger.log(Level.ERROR, "Operation is not supported");
            throw new UnsupportedOperationException("Operation is not supported");
        }
    }

    @Override
    public boolean remove(TextComponent component) {
        {
            logger.log(Level.ERROR, "Operation is not supported");
            throw new UnsupportedOperationException("Operation is not supported");
        }
    }

    @Override
    public List<TextComponent> getChildren() {
        {
            logger.log(Level.ERROR, "Operation is not supported");
            throw new UnsupportedOperationException("Operation is not supported");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextLeaf textLeaf1 = (TextLeaf) o;

        return character == textLeaf1.character;
    }

    @Override
    public int hashCode() {
        return character;
    }
}
