package com.example.pronewton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {
    public TextField fieldX;
    public TextField fieldY;
    public double[][] grid;
    public TextField answer;

    @FXML
    protected void calculate() {
        String textX = fieldX.getText() + ";;";
        String textY = fieldY.getText() + ";;";
        ArrayList<Double> listX = new ArrayList<>();
        ArrayList<Double> listY = new ArrayList<>();
        String piece = "";
        boolean doubleIndicator = false;

        for(int i = 1, k = 0; i < textX.length(); i++){
            piece = textX.substring(k,i);
            if(checkDoubleParsing(piece)){
                doubleIndicator = true;
            }
            if(!checkDoubleParsing(piece) && doubleIndicator){
                listX.add(Double.parseDouble(piece.substring(0,piece.length()-1)));
                k = i;
                doubleIndicator = false;
            }
        }
        piece = "";
        for(int i = 1, k = 0; i < textY.length(); i++){
            piece = textY.substring(k,i);
            if(checkDoubleParsing(piece)){
                doubleIndicator = true;
            }
            if(!checkDoubleParsing(piece) && doubleIndicator){
                listY.add(Double.parseDouble(piece.substring(0,piece.length()-1)));
                k = i;
                doubleIndicator = false;
            }
        }
        /*double[][] grid;
        for(int i = 0; i < 8; i ++){
            if(checkDoubleParsing(tgrid[0][i].getText())){
                qu1++;
            }
        }
        for(int i = 0; i < 8; i ++){
            if(checkDoubleParsing(tgrid[1][i].getText())){
                qu2++;
            }
        }*/
        if(listX.size() == listY.size()){
            grid = new double[2][listX.size()];
            for(int i = 0; i < listX.size(); i ++){
                grid[0][i] = listX.get(i);
                grid[1][i] = listY.get(i);
            }/*
            for(int i = 0; i < listX.size(); i ++){
                grid[1][i] = listY.get(i);
            }*/
            String text = theTask(grid);
            Expression exp = new Expression(text);
            exp.root.summation();
            String total = "y = ";
            for(int i = exp.root.getSummands().size()-1; i >= 0; i--){
                if(exp.root.getSummands().get(i).getNumber() == 0){
                    continue;
                }
                String num = Double.toString(exp.root.getSummands().get(i).getNumber());
                String pow = Double.toString(exp.root.getSummands().get(i).getPower());
                if(Math.abs(exp.root.getSummands().get(i).getNumber()) - Math.abs(Math.floor(exp.root.getSummands().get(i).getNumber())) == 0){
                    num = num.substring(0, num.length()-2);
                }
                if(Math.abs(exp.root.getSummands().get(i).getPower()) - Math.abs(Math.floor(exp.root.getSummands().get(i).getPower())) == 0){
                    pow = pow.substring(0, pow.length()-2);
                }
                if(exp.root.getSummands().get(i).getNumber() >= 0){
                    if(exp.root.getSummands().get(i).getNumber() == 1){
                        if(i == exp.root.getSummands().size() - 1){

                        }else{
                            total+= " + ";
                        }
                    }else{
                        if(i == exp.root.getSummands().size() - 1){
                            total+= " "+ num;
                        }else{
                            total+= " + "+ num;
                        }
                    }
                }else{
                    if(exp.root.getSummands().get(i).getNumber()==0){
                        total+= " + "+ num;

                    }else{


                        num = num.substring(1,num.length());
                        total+= " - " + num;
                    }
                }
                if(exp.root.getSummands().get(i).getPower() != 0){
                    total += "x";
                    if(exp.root.getSummands().get(i).getPower() != 1){
                        total += "^" + pow;
                    }
                }
            }
            answer.setText(total);
        }
    }

    public static String theTask(double[][] input){
        DeltaCalculator calculator = new DeltaCalculator();
        double[] deltas = calculator.calculate(input);
        String text = "";
        text += Double.toString(input[1][0]) + "x^0+";
        String piece = "";
        for(int i = 0; i < deltas.length; i++){
            if(input[0][i] > 0){
                piece += "*(1x^1"+Double.toString(-input[0][i]) + "x^0)";
            }else{
                if(input[0][i]==0){
                    piece += "*(1x^1+"+Double.toString(input[0][i]) + "x^0)";
                }else{
                    piece += "*(1x^1+"+Double.toString(-input[0][i]) + "x^0)";
                }
            }
            text+="("+Double.toString(deltas[i])+"x^0"+piece+")+";
        }
        text = text.substring(0, text.length()-1);
        return text;
    }
    boolean checkDoubleParsing(String checked){
        boolean answer = false;
        double experiment;
        try
        {
            experiment = Double.parseDouble(checked);
            answer = true;
        }catch(NumberFormatException e){}
        return answer;
    }
}