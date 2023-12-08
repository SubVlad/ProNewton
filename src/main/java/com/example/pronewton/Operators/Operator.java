package com.example.pronewton.Operators;

import java.util.ArrayList;

public interface Operator {
    int[] operation(ArrayList<Operator> arr, int index);
    double getValue();
    void setValue(double value);

}
