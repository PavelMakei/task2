package by.makei.composite.reader;


import by.makei.composite.exception.CustomException;

import java.io.File;


public interface CustomFileReader {
    String readLinesFromFile(File fileName) throws CustomException;
}
