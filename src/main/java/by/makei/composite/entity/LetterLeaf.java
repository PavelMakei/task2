package by.makei.composite.entity;

public class LetterLeaf implements TextComponent {
    private char letter;

    public LetterLeaf(char letter) {
        this.letter = letter;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Letter{");
        sb.append("letter=").append(letter);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LetterLeaf letterLeaf1 = (LetterLeaf) o;

        return letter == letterLeaf1.letter;
    }

    @Override
    public int hashCode() {
        return letter;
    }
}