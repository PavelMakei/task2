package by.makei.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{

    private String name;
    private List<TextComponent> components = new ArrayList();

    public TextComposite(String name) {
        this.name = name;
    }

    @Override
    public boolean add(TextComponent component)  {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component)  {
        return components.remove(component);
    }

    @Override
    public List<TextComponent> getChildren()  {
        return new ArrayList<>(components);
    }

}
