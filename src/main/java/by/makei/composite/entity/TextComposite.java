package by.makei.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{

    private ConcreteTextComponent type;
    private List<TextComponent> components = new ArrayList<TextComponent>();

    public TextComposite(ConcreteTextComponent type) {
        this.type = type;
    }
}
