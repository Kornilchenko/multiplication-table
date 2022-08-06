package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ReaderProperties {
    private static final Logger log = LoggerFactory.getLogger(ReaderProperties.class);
    private int increment;
    private double minimum;
    private double maximum;

    public ReaderProperties(String fileProperties) throws IOException {
        log.info("run constructor ReaderProperties class");
        Properties properties = new Properties();
        try {
            log.info("read external file properties");
            FileInputStream fis = new FileInputStream(fileProperties);
            log.info("get properties from external file");
            properties.load(new InputStreamReader(fis, StandardCharsets.UTF_8));
            fis.close();
        } catch (IOException e) {
            log.error("Error, read external file! ");
        }
        if(readPropertiesFromFile(properties)){
            InputStream inputStream = Multiplication.class.getClassLoader().getResourceAsStream("internal.properties");
            assert inputStream != null;
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        readPropertiesFromFile(properties);
    }

    private boolean readPropertiesFromFile(Properties properties){
        log.info("check the properties file for the required values");
        if(properties.containsKey("min") && properties.containsKey("max") && properties.containsKey("increment")){
            minimum = Double.parseDouble(properties.getProperty("min"));
            maximum = Double.parseDouble(properties.getProperty("max"));
            increment = Integer.parseInt(properties.getProperty("increment"));
            log.info("properties from the file are read");
            return false;
        }
        log.debug("properties file does not contain required properties");
        return true;
    }
    public double getMinimal(){
        return minimum;
    }
    public double getMaximum(){
        return maximum;
    }
    public int getIncrement(){
        return increment;
    }
}
