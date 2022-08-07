package com.project.multiplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MultiplicationTable {
    private static final Logger log = LoggerFactory.getLogger(MultiplicationTable.class);
    private Number minimal;
    private Number maximal;
    private Number increment;
    char type;

    public MultiplicationTable(Double min, Double max, Double inc, String [] args){
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

    // комент

    public void showMultiplicationTable() {
        if (type == 'i') {
            int min = (int) minimal;
            int max = (int) maximal;
            int inc = (int) increment;
            if (inc == 0)
                inc = 1;
            for (int n = min; n <= max; n += inc) {
                for (int m = min; m <= max; m += inc) {
                    System.out.print(m * n + "\t");
                }
                System.out.println();
            }
        } else {
            double min = (double) minimal;
            double max = (double) maximal;
            double inc = (double) increment;
            for (double n = min; n <= max; n += inc) {
                for (double m = min; m <= max; m += inc) {
                    System.out.printf("%.3f", m * n);
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }
}
