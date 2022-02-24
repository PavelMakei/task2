package by.makei.composite.entity;

public class TextLeaf implements TextComponent {
    private char character;
    private TextComponentType type;

    public TextLeaf(TextComponentType type, char character) {
        this.type = type;
        this.character = character;
    }

    @Override
    public TextComponentType getType() {
        return type;
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
