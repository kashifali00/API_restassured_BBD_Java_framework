package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager configManager = null;
    public static Properties prop = new Properties();
    static Logger logger = LogManager.getLogger(ConfigManager.class);


    //Private constractor to only get the single object of that class

    private ConfigManager() {
        try {


            logger.info("Loading properties files");
            String filename = System.getProperty("user.dir") + File.separator +
                    "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";
            logger.info("File name :" + filename);
            prop.load(new FileInputStream(filename));
            logger.info("properties file loaded successfully");
        }catch (Exception e){
            logger.error(e);
        }
    }

    // synchronized used to make it thread safe get instance method

    public static synchronized ConfigManager getConfigManagerInstance(){
        logger.info("executing getinstancemethod");
        if(configManager == null) {
            synchronized (ConfigManager.class) {
                configManager = new ConfigManager();
            }
        }
        return configManager;
    }

    // Get value from properties files
    public String getKeyValue(String key)
    {
        System.out.println("Returing value from key [" + key+ "] :" + System.getProperty(key, prop.getProperty(key)));
        return System.getProperty(key, prop.getProperty(key));
    }


}
