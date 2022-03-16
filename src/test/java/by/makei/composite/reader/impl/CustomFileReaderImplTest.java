package by.makei.composite.reader.impl;

import by.makei.composite.exception.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderImplTest {
    public static final String FILE_NAME = "data/text1.txt";
    public static final String EXPECTED_TEXT = """
                It has survived - not only (five) centuries, but also the leap into electronic
            typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)
            with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and
            more recently with desktop publishing software like Aldus PageMaker Faclon9 including
            versions of Lorem Ipsum!
                It is a long a!=b established fact that a reader will be distracted by the readable
            content of a page when looking at its layout. The point of using Ipsum is that it has a
            more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here),
            content here's, making it look like readable English?
                It is a established fact that a reader will be of a page when looking at its layout...
                Bye бандерлоги."""
            ;

    @Test
    void readLinesFromFile() throws CustomException {
        CustomFileReaderImpl reader = CustomFileReaderImpl.getInstance();
        String actual = reader.readTextFromFile(FILE_NAME);
        assertEquals(EXPECTED_TEXT,actual);

    }
}