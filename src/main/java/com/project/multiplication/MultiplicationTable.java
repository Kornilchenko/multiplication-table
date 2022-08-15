package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class MultiplicationTable {
    private static final Logger log = LoggerFactory.getLogger(MultiplicationTable.class);
    private final Double minimal;
    private final Double maximal;
    private final Double increment;
    char type;

    public MultiplicationTable(Double min, Double max, Double inc, String[] args) {
        this.minimal = min;
        this.maximal = max;
        this.increment = inc;
        this.type = readingArguments(args);
    }

    /**
     * reads arguments devotees with the program
     *
     * @param argumens - argument betrayed at startup
     * @return -
     */
    private static char readingArguments(String[] argumens) {
        if (argumens.length == 0)
            return 'i';
        for (String arg : argumens) {
            if (arg.equalsIgnoreCase("double") || arg.equalsIgnoreCase("float")) {
                return 'd';
            }
        }
        log.debug("default type variable int");
        return 'i';
    }

    /**
     * validates property variables for further use
     *
     * @return - true if the properties are suitable for use
     */
    private boolean checkVariableValues() {
        if (minimal == null) {
            log.error("no values in properties, program exit");
            return false;
        } else if (minimal > maximal) {
            log.error("variable error minimum greater than maximum");
            return false;
        } else if (maximal - minimal <= increment) {
            log.error("the increment is greater than or equal to the difference between the maximum and minimum values");
            return false;
        } else if (increment <= 0) {
            log.error("increment is less than or equal to zero");
            return false;
        }
        return true;
    }

    /**
     * prints the multiplication table to the console
     */
    public void showMultiplicationTable() {
        StringBuilder str= new StringBuilder();
        if (checkVariableValues()) {
            if (type == 'i') {
                int min = minimal.intValue();
                int max = maximal.intValue();
                int inc = increment.intValue();
                if (inc == 0)
                    inc = 1;
                for (int n = min; n <= max; n += inc) {
                    for (int m = min; m <= max; m += inc) {
                        str.append(m * n).append("\t");
                    }
                    String result = str.toString();
                    log.info(result);
                    str.setLength(0);
                }
            } else {
                for (double n = minimal; n <= maximal; n += increment) {
                    for (double m = minimal; m <= maximal; m += increment) {
                        str.append(new DecimalFormat("#0.0000").format(m*n)).append("\t");
                    }
                    String result = str.toString();
                    log.info(result);
                    str.setLength(0);
                }
            }
        }
    }
}
