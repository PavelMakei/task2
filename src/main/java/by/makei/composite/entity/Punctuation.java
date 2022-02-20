package by.makei.composite.entity;

public class Punctuation implements TextComponent{
    private char punctuationMark;

    public Punctuation(char punctuationMark) {
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

        Punctuation that = (Punctuation) o;

        return punctuationMark == that.punctuationMark;
    }

    @Override
    public int hashCode() {
        return punctuationMark;
    }
}
