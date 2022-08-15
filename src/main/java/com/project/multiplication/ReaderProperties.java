package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ReaderProperties {
    private static final Logger log = LoggerFactory.getLogger(ReaderProperties.class);
    private final HashMap<String, Double> property;

    /**
     *
     * @param externalPropertiesFile -external file to read properties
     * @throws IOException - error reading external file
     */
    public ReaderProperties(String externalPropertiesFile) throws IOException {
       this(externalPropertiesFile, "internal.properties");
    }
    /**
     * class constructor
     *
     * @param externalPropertiesFile - external file to read properties
     * @param internalPropertiesFile - internal file to read properties
     * @throws IOException - error reading external file
     */
    public ReaderProperties(String externalPropertiesFile, String internalPropertiesFile) throws IOException {
        log.info("run constructor ReaderProperties class");
        property = new HashMap<>();
        property.put("min", null);
        property.put("max", null);
        property.put("increment", null);
        Properties properties = readExternalFile(externalPropertiesFile);
        boolean reads = readPropertiesFromMyFile(properties);
        if (!reads) {
            properties = readInternalFile(internalPropertiesFile);
            reads = readPropertiesFromMyFile(properties);
        }
        if(!reads){
            for (Map.Entry<String, Double> item : property.entrySet()) {
                item.setValue(null);
            }
            log.debug("no required properties");
        }
    }

    /**
     * reading an internal properties file
     *
     * @return - properties file
     * @throws IOException - error reading external file
     */
    private Properties readInternalFile(String filename) throws IOException {
        Properties properties = new Properties();
        log.debug("read internal file properties");
        InputStream inputStream = Multiplication.class.getClassLoader().getResourceAsStream(filename);
        assert inputStream != null;
        properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        return properties;
    }

    /**
     * reading an external properties file
     *
     * @param fileName - external file to read properties
     * @return - properties file
     */
    private Properties readExternalFile(String fileName) {
        Properties properties = new Properties();
        log.info("read external file properties");
        try (
                FileInputStream fis = new FileInputStream(fileName);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)
        ) {
            properties.load(isr);
        } catch (IOException e) {
            log.error("Error, read external file! ");
        }
        return properties;
    }

    /**
     * reads properties from a file
     *
     * @param properties - properties written in the file
     * @return - true if the required property keys exist and they contain numeric values
     */
    private boolean readPropertiesFromMyFile(Properties properties) {
        log.info("check the properties file for the required values");
        for (Map.Entry<String, Double> item : property.entrySet()) {
            String temp = properties.getProperty(item.getKey());
            if (temp == null) {
                log.debug("properties file does not contain required properties");
                return false;
            }
            temp = temp.replace(" ", "").replace(",", ".");
            if (!checkArgumentForValue(temp)) {
                log.debug("properties file does not contain required properties");
                return false;
            }
            item.setValue(Double.parseDouble(temp));
        }
        log.info("properties from the file are read");
        return true;
    }

    /**
     * checks property values for numeric values
     *
     * @param value - property value in string
     * @return - true if value is a number
     */
    private boolean checkArgumentForValue(String value) {
        if (!(value.matches("\\d+[.]?\\d*"))) {
            log.error("Variable error! Invalid argument, does not contain a number {}", value);
            return false;
        }
        return true;
    }

    /**
     * @return - value from properties whose key "min"
     */
    public Double getMinimal() {
        return property.get("min");
    }

    /**
     * @return - value from properties whose key "max"
     */
    public Double getMaximum() {
        return property.get("max");
    }

    /**
     * @return - value from properties whose key "increment"
     */
    public Double getIncrement() {
        return property.get("increment");
    }
}