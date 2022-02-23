package by.makei.composite.reader.impl;

import by.makei.composite.exception.CustomException;
import by.makei.composite.reader.CustomFileReader;

import by.makei.composite.util.CustomFileUtil;
import by.makei.composite.util.impl.CustomFileUtilImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class CustomFileReaderImpl implements CustomFileReader {

    private static final Logger logger = LogManager.getLogger();
    private static final CustomFileReaderImpl instance = new CustomFileReaderImpl();
    private static final String WINDOWS_FILE_SEPARATOR = "\\";
    private static final String URL_FILE_SEPARATOR = "/";

    private CustomFileReaderImpl() {}

    public static CustomFileReaderImpl getInstance() {
        return instance;
    }

    @Override
    public String readLinesFromFile(String stringFileName) throws CustomException {
        CustomFileUtil fileUtil = CustomFileUtilImpl.getInstance();
        File file = fileUtil.getFileFromStringForResourcesPackage(stringFileName);
        Path path = file.toPath();
        try {
        return Files.readString(path);
        } catch (IOException e) {
            logger.log(Level.ERROR, "file wasn't read", e);
            throw new CustomException("file wasn't read", e);
        }
    }
}
