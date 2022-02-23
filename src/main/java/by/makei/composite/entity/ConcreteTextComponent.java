package by.makei.composite.entity;

public enum ConcreteTextComponent {
    TEXT("", ""),
    PARAGRAPH("\t", "\n"),
    SENTENCE("", ""), //предложение
    LEXEME("", " "),
    WORD("", ""),
    BIT_EXPRESSION("","");//TODO if it has to be there?

    private final String prefix;
    private final String postfix;

    private ConcreteTextComponent(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
