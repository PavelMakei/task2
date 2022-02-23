package by.makei.composite.entity;

public class PunctuationLeaf implements TextComponent{
    private char punctuationMark;

    public PunctuationLeaf(char punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Punctuation{");
        sb.append("punctuationMark=").append(punctuationMark);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PunctuationLeaf that = (PunctuationLeaf) o;
        return punctuationMark == that.punctuationMark;
    }

    @Override
    public int hashCode() {
        return punctuationMark;
    }
}
