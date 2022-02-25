package by.makei.composite.service.impl;

import by.makei.composite.entity.TextComponent;
import by.makei.composite.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;


class TextServiceImplTest {
    public final String text1 = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
            + "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"
            + "\tBye.\n"
            + "\tIt is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n";
    public final String text2 = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
            + "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"
            + "\tIt is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n"
            + "\tBye.\n";
    public final String text3 = "\tHello my dear friend!\n"
            + "\tThis sentence contains the loooooooongest word in this text.\n"
            + "\tThis text for check. Bye.\n";
    public final String text4 = "\tqwerty uiopa sdfg hjkl zxcvbnmQWERTY UIOPASD FGHJK LZXC VBNM!\n"
            + "\tёйцук енгшщ зхъф ывапр олджэя чсмит ьбю ЁЙЦУ КЕНГ ШЩЗХЪ ФЫВ АПРОЛДЖЭ ЯЧС МИТЬ БЮ.\n";
    public final String text5 = "\tA, E, I, O, U, Y aeiouy.\n"
            +"\tB, C, D, F, G, H, J, K, L, M, N, P, Q, R, S, T, V, W, X, Z.\n"
            +"\tЁУЕЫАОЭЯИЮ ёуеыаоэяиюB, C, D, F, G, H, J, K, L, M, N, P, Q, R, S, T, V, W, X, Z б, в, г, д, ж, з, й, к, л, м, н, п, р, с, т, ф, х, ц, ч, ш, щБ, В, Г, Д, Ж, З, Й, К, Л, М, Н, П, Р, С, Т, Ф, Х, Ц, Ч, Ш, Щ.\n";


    public TextServiceImpl service;
    public final int minWordValue = 3;

    @BeforeEach
    void setUp(){
        service = TextServiceImpl.getInstance();
    }


    @Test
    void sortParagraphsBySentenceNumberTest() throws CustomException {
        List<TextComponent> sorted = service.sortParagraphsBySentenceNumber(text1);
        int[] actual = new int[sorted.size()];
        int[] expected = {1,1,2,2};
        for (int i = 0; i<sorted.size();i++){
            actual[i] = sorted.get(i).getChildren().size();
        }
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    void findSentencesWithLongestWord() throws CustomException {
        List sentences = service.findSentencesWithLongestWord(text3);
        String expected = "This sentence contains the loooooooongest word in this text. ";
        String actual = sentences.get(0).toString();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void removeAllSentencesWithNumberWordsTest() throws CustomException {
        List paragraphs = service.removeAllSentencesWithNumberWordsLessThen(text1, minWordValue);
        String expected = "\t\n";
        String actual = paragraphs.get(2).toString();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void findDuplicateNumberTest() throws CustomException {
        Map sentences = service.findDuplicateNumber(text3);
        long expected = 3l;
        long actual = (Long) sentences.get("this");
        assert (actual == expected);
    }

    @Test
    void countVowelTest() throws CustomException {
        int actual = service.countVowel(text5);
        int expected = (6+10)*2;
        Assertions.assertEquals (expected,actual);
    }

    @Test
    void countСonsonants() throws CustomException {
        int actual = service.countСonsonant(text5);
        int expected = (20+21)*2;
        Assertions.assertEquals (expected,actual);

    }
}