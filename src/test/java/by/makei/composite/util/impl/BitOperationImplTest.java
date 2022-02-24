package by.makei.composite.util.impl;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BitOperationImplTest {
    public final String expression1 = "(7^5|1&2<<(2|5>>2&71))|1200";
    public final String expression2 = "(7^5|1&2<<(2|5>>2&71))|-1200";
    public BitOperationImpl util;

    @BeforeEach
    void setUp() {
        util = BitOperationImpl.getInstance();
    }

    @Test
    void parseAndCalculateBitOperationTest() {
        String actual = util.parseAndCalculateBitOperation(expression1);
        String expected = "1202";
        assertEquals(actual, expected);
    }

    @Test
    void parseAndCalculateBitOperationWithNegativeTest() {
        String actual = util.parseAndCalculateBitOperation(expression2);
        String expected = "-1198";
        assertEquals(actual, expected);
    }
}