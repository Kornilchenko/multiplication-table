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
    private int minimum;
    private int maximum;

    public ReaderProperties(String nameFile) throws IOException {
        Properties properties = new Properties();
        String propInternalFileName = "internal.properties";
        String thePathToExternalFile = "external.properties";

        try {
            FileInputStream fis = new FileInputStream(thePathToExternalFile);
            properties.load(new InputStreamReader(fis, StandardCharsets.UTF_8));

        } catch (IOException e) {
            log.error("Error, read external file! ");
        }


        if(properties.isEmpty()){
            InputStream inputStream = Multiplication.class.getClassLoader().getResourceAsStream(propInternalFileName);
            assert inputStream != null;
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }

         minimum = Integer.parseInt(properties.getProperty("min"));
         maximum = Integer.parseInt(properties.getProperty("max"));
         increment = Integer.parseInt(properties.getProperty("increment"));
    }

    public int getMinimal(){
        return minimum;
    }

    public int getMaximum(){
        return maximum;
    }
    public int getIncrement(){
        return increment;
    }
}
