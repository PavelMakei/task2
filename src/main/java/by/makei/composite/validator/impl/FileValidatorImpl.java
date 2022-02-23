package by.makei.composite.validator.impl;

import by.makei.composite.validator.FileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;


public class FileValidatorImpl implements FileValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final FileValidatorImpl instance = new FileValidatorImpl();
    private static final String WINDOWS_FILE_SEPARATOR = "\\";
    private static final String URL_FILE_SEPARATOR = "/";

    private FileValidatorImpl() {    }

    public static FileValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateFile(String fileName) {
        boolean isValid = false;
        if (fileName == null) {
            logger.log(Level.ERROR, "FileName is null");
            return false;
        }
        fileName = fileName.replace(WINDOWS_FILE_SEPARATOR, URL_FILE_SEPARATOR);
        File file;
        URL url = getClass().getClassLoader().getResource(fileName);
        if (url == null) {
            logger.log(Level.ERROR, "Wrong filename");
            return false;
        }

        file = new File(url.getFile());
        if (file.length() > 0) {
            logger.log(Level.INFO, "File is correct");
            isValid = true;
        } else {
            logger.log(Level.ERROR, "File {} does not exist or has size 0", fileName);
        }

        return isValid;
    }
}
