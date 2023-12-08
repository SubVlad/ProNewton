package com.example.pronewton;
public class DeltaCalculator {

    public double[] calculate(double[][] input){
        double[][] guarder = input.clone();
        int tableWidth = input[1].length;
        int step = 1;
        double[] result = new double[input[1].length-1];
        int index = 0;
        double[] bufferMatrix;
        for(int i = tableWidth-1; i > 0; i--){
            bufferMatrix = new double[i];
            for(int k = 0; k < bufferMatrix.length; k++){
                bufferMatrix[k] = (guarder[1][k+1]-guarder[1][k])/(guarder[0][k+step]-guarder[0][k]);
            }
            result[index] = bufferMatrix[0];
            index++;
            guarder[1] = bufferMatrix;
            step++;
        }
        return result;
    }
}
