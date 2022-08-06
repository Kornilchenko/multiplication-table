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
    private static final ArrayList<Double> PROPERTY_VALUES = new ArrayList<>(KEYS.length);

    /**
     * class constructor
     *
     * @param fileProperties - external file to read properties
     * @throws IOException - error reading external file
     */
    public ReaderProperties(String fileProperties) throws IOException {
        log.info("run constructor ReaderProperties class");
        Properties properties = new Properties();

        log.info("read external file properties");
        try (
                FileInputStream fis = new FileInputStream(fileProperties);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)
        ) {
            properties.load(isr);
        }
        catch (IOException e) {
            log.error("Error, read external file! ");
        }
        if (readPropertiesFromMyFile(properties)) {
            InputStream inputStream = Multiplication.class.getClassLoader().getResourceAsStream("internal.properties");
            assert inputStream != null;
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        if (readPropertiesFromMyFile(properties)) {
            log.error("no indicators for mathematical operations");
            for (int i = 0; i < PROPERTY_VALUES.size(); ++i) {
                PROPERTY_VALUES.add(null);
            }
        }
    }

    /**
     * reads properties from a file
     *
     * @param properties - properties written in the file
     * @return - true if the required property keys exist and they contain numeric values
     */
    private boolean readPropertiesFromMyFile(Properties properties) {
        log.info("check the properties file for the required values");
        if (properties.containsKey(KEYS[0]) && properties.containsKey(KEYS[1]) && properties.containsKey(KEYS[2])) {
            for (int i = 0; i < KEYS.length; ++i) {
                String temp = properties.getProperty(KEYS[i]);
                temp = temp.replace(" ", "").replace(",", ".");
                if (!checkArgumentForValue(temp))
                    return true;
                PROPERTY_VALUES.add(Double.parseDouble(temp));
            }
            log.info("properties from the file are read");
            return false;
        }
        log.debug("properties file does not contain required properties");
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
        return PROPERTY_VALUES.get(0);
    }

    /**
     * @return - value from properties whose key "max"
     */
    public Double getMaximum() {
        return PROPERTY_VALUES.get(1);
    }

    /**
     * @return - value from properties whose key "increment"
     */
    public Double getIncrement() {
        return PROPERTY_VALUES.get(2);
    }
}
