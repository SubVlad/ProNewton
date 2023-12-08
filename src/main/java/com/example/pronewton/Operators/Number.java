package com.example.pronewton.Operators;

import java.util.ArrayList;

public class Number implements Operator {
    double value;
    public Number(double value)
    {
        this.value = value;
    }
    @Override
    public int[] operation(ArrayList<Operator> arr, int index) {
        return null;
    }

    @Override
    public double getValue() {
        return this.value;
    }
    @Override
    public void setValue(double value) {
        this.value = value;

    }
}