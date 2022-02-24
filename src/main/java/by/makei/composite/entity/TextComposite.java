package by.makei.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{

    private final TextComponentType type;
    private List<TextComponent> components = new ArrayList();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public TextComponentType getType(){
        return type;
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

    @Override
    public String toString() {
       StringBuilder common = new StringBuilder();
       common.append(type.getPrefix());
       components.stream().forEach(part->common.append(part.toString()));
       common.append(type.getPostfix());
       return common.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        if (type != that.type) return false;
        return components != null ? components.equals(that.components) : that.components == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }
}
