package com.example.pronewton.Operators;

import java.util.ArrayList;

public class Multiplication implements Operator {
    @Override
    public int[] operation(ArrayList<Operator> arr, int index)
    {
        int[] indexes = new int[5];
        double[][] matrix = new double[2][2];
        double doub1;
        double doub2;
        double doub3;
        double doub4;
        double doub5;
        double doub6;

        doub1 = arr.get(index-4).getValue();
        doub2 = arr.get(index+1).getValue();
        doub3 = doub1 * doub2;
        doub4 = arr.get(index-1).getValue();
        doub5 = arr.get(index+4).getValue();
        doub6 = doub4 + doub5;

        matrix[0][0] = (double)index-4.0;
        matrix[0][1] = doub3;
        matrix[1][0] = (double)index-1.0;
        matrix[1][1] = doub6;

        for(int i = 0; i < indexes.length; i++)
        {
            indexes[i] = index + i;
        }
        return indexes;
    }

    @Override
    public double getValue() {
        return 0;
    }
    @Override
    public void setValue(double number) {

    }
}