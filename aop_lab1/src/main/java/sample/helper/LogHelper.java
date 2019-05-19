package sample.helper;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHelper {
    private final static Logger logger = Logger.getLogger("AOP");

    public static LogHelper Instance = new LogHelper();

    private LogHelper() {
        try {
            logger.addHandler(new FileHandler("log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogError(String log) {
        logger.log(Level.SEVERE, log);
    }

    public void LogError(Exception e) {
        logger.log(Level.SEVERE, e.getMessage());
    }

    public void LogInfo(String log) {
        logger.log(Level.INFO, log);
    }
}