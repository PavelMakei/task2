package by.makei.composite.parser;

import by.makei.composite.entity.ConcreteTextComponent;
import by.makei.composite.entity.TextComponent;
import by.makei.composite.entity.TextComposite;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphChainParserTest {

    @Test
    void parseTest() {
        String text = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
        + "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"
        + "\tIt is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n"
        + "\tBye.\n";

        TextComponent component = new TextComposite("TEXT");
        AbstractTextChainParser parser = new ParagraphChainParser();
        parser.parse(component, text);

        String actual = component.toString();
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n"
                + "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n"
                + "\tIt is a established fact that a reader will be of a page when looking at its layout\u2026 \n"
                + "\tBye бандерлоги. \n";


        Assertions.assertEquals(actual, expected);

    }
}