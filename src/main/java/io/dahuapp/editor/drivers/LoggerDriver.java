/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dahuapp.editor.drivers;

import io.dahuapp.editor.utils.HTMLFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean-Baptiste
 */
public class LoggerDriver implements Driver {
    
    private static final Logger logger = Logger.getLogger(LoggerDriver.class.getName());
    private FileHandler fh;
    
    /**
     * @constructor LoggerDriver
     * @brief Constructs a LoggerDriver. The log file will be set
     * in the temporary directory of the system
     * eg. C:/temp/dahu[nb].log on Windows system
     *   or /var/tmp/dahu[nb] on Solaris system....
     */
    public LoggerDriver() {
    // create a logger and a handler in html format
    logger.setLevel(Level.ALL); //to throw messages from all levels
    logger.setUseParentHandlers(false); //to supress the default console
        try {
            fh = new FileHandler("%t/dahu%u.log.html");
            fh.setFormatter(new HTMLFormatter());
            logger.addHandler(fh);
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     * @constructor LoggerDriver
     * @param directory : the directory to place the log file
     * @brief Constructs a LoggerDriver. The log file will be set
     * in the directory passed on parameters
     */
    public LoggerDriver(String directory) {
    // create a logger and a handler in html format
    logger.setLevel(Level.ALL); //to throw messages from all levels
    logger.setUseParentHandlers(false); //to supress the default console
        try {
            fh = new FileHandler(directory + "/dahu%u.log.html");
            fh.setFormatter(new HTMLFormatter());
            logger.addHandler(fh);
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     * @method info
     * @param message : the message to log
     * @brief log the message with an INFO level (color : cyan)
     */
    public static void info(String message) {
        logger.log(Level.INFO, message) ;
    }
    
    /**
     * @method info
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @brief log the message with an INFO level (color : cyan)
     */
    public static void info(String sourceClass, String sourceMethod, String message) {
        logger.logp(Level.INFO, sourceClass, sourceMethod, message);
    }
    
     /**
     * @method severe
     * @param message : the message to log
     * @brief log the message with a SEVERE level (color : red)
     */
    public static void severe(String message) {
        logger.log(Level.SEVERE, message);
    }
    
     /**
     * @method severe
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @brief log the message with a SEVERE level (color : red)
     */
    public static void severe(String sourceClass, String sourceMethod, String message) {
        logger.logp(Level.SEVERE, sourceClass, sourceMethod, message);
    }
    
    /**
     * @method severe
     * @param message : the message to log
     * @param cause  : the cause of the severity
     * @brief log the message with a SEVERE level (color : red)
     */
    public static void severe(String message, Throwable cause) {
        logger.log(Level.SEVERE, message, cause);
    }
    
    /**
     * @method severe
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @param cause : the cause of the severity
     * @brief log the message with a SEVERE level (color : red)
     */
    public static void severe(String sourceClass, String sourceMethod, String message, 
                        Throwable cause) {
        logger.logp(Level.SEVERE, sourceClass, sourceMethod, message, cause);
    }
    
     /**
     * @method warning
     * @param message : the message to log
     * @brief log the message with a WARNING level (color : yellow)
     */
    public static void warning(String message) {
        logger.log(Level.WARNING, message);
    }
    /**
     * @method warning
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @brief log the message with a WARNING level (color : yellow)
     */
    public static void warning(String sourceClass, String sourceMethod, String message) {
        logger.logp(Level.WARNING, sourceClass, sourceMethod, message);
    }
    
     /**
     * @method fine
     * @param message : the message to log
     * @brief log the message with a FINE level (color : blue)
     */
    public static void fine(String message) {
        logger.log(Level.FINE, message);
    }
    
     /**
     * @method fine
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @brief log the message with a FINE level (color : blue)
     */
    public static void fine(String sourceClass, String sourceMethod, String message) {
        logger.logp(Level.FINE, sourceClass, sourceMethod, message);
    }
        
     /**
     * @method config
     * @param message : the message to log
     * @brief log the message with a CONFIG level (color : green)
     */
    public static void config(String message) {
        logger.log(Level.CONFIG, message);
    }
        
     /**
     * @method config
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @brief log the message with a CONFIG level (color : green)
     */
    public static void config(String sourceClass, String sourceMethod, String message) {
        logger.logp(Level.CONFIG, sourceClass, sourceMethod, message);
    }
    
    /**
     * @method log
     * @param message : the message to log
     * @brief log the message with an ALL level (color : black)
     */
    public static void log(String message) {
        logger.log(Level.ALL, message);
    }
     
     /**
     * @method log
     * @param sourceClass : name of class that issued the logging request
     * @param sourceMethod : name of method that issued the logging request
     * @param message : the message to log
     * @brief log the message with an ALL level (color : black)
     */
    public static void log(String sourceClass, String sourceMethod, String message) {
        logger.logp(Level.ALL, sourceClass, sourceMethod, message);
    }
    
    /**
     * @method setLogLevel
     * @param level 
     * @brief changes the level (default is Level.ALL)
     * The level value Level.OFF can be used to turn off logging.
     * @see java.util.logging.Logger
     */
    public void setLogLevel(Level level) {
        logger.setLevel(level);
    }

    @Override
    public void onLoad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onStop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
