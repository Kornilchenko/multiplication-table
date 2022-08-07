package com.project.multiplication;

public class MultiplicationTable {
    private Double min;
    private Double max;
    private Double inc;
    char type;

    public MultiplicationTable(Double min, Double max, Double inc, char type){
        this.min = min;
        this.max = max;
        this.inc = inc;
        this.type = type;
    }
}
