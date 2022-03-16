package by.makei.composite.reader;


import by.makei.composite.exception.CustomException;


public interface CustomFileReader {
    String readTextFromFile(String stringFileName) throws CustomException;
}
