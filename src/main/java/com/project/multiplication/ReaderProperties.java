package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

public class ReaderProperties {
    private static final Logger log = LoggerFactory.getLogger(ReaderProperties.class);
    private static final String[] KEYS = new String[]{"min", "max", "increment"};
    private static final ArrayList <Double> PROPERTY_VALUES = new ArrayList<>(KEYS.length);

    /**
     *
     * @param fileProperties
     * @throws Exception
     */
    public ReaderProperties(String fileProperties) throws Exception {
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
        if(!readPropertiesFromFile(properties)){
            InputStream inputStream = Multiplication.class.getClassLoader().getResourceAsStream("internal.properties");
            assert inputStream != null;
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        readPropertiesFromFile(properties);
    }

    /**
     *
     * @param properties
     * @return
     * @throws Exception
     */
    private boolean readPropertiesFromFile(Properties properties) throws Exception {
        log.info("check the properties file for the required values");
        if(properties.containsKey(KEYS[0]) && properties.containsKey(KEYS[1]) && properties.containsKey(KEYS[2])){
           for (int i=0; i <KEYS.length; ++i){
               String temp = properties.getProperty(KEYS[i]);
               temp = temp.replace(" ", "");
               temp = temp.replace(",", ".");
               if(!checkArgumentForValue(temp))
                   return false;
               PROPERTY_VALUES.add(Double.parseDouble(temp));
           }
            log.info("properties from the file are read");
            return true;
        }
        log.debug("properties file does not contain required properties");
        return false;
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean checkArgumentForValue(String value){
        if (!(value.matches("\\d+[.]?\\d*"))) {
            log.error("Variable error! Invalid argument, does not contain a number {}", value);
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public double getMinimal(){
        return PROPERTY_VALUES.get(0);
    }

    /**
     *
     * @return
     */
    public double getMaximum(){
        return PROPERTY_VALUES.get(1);
    }

    /**
     *
     * @return
     */
    public double getIncrement(){
        return PROPERTY_VALUES.get(2);
    }
}
