package com.example.pronewton;
import com.example.pronewton.Operators.Operator;

import java.util.ArrayList;

public interface Multipliable {
    double getNumber();
    double getPower();
    boolean getForDeleting();
    void setNumber(double number);
    void setPower(double power);
    void setForDeleting(boolean state);
    void addSummand(double number, double power);
    void summation();
    ArrayList<Summable> getSummands();
    int getSummandsSize();
    Summable getSummable(int index);
    String getClassName();
    void printNames();
    Multipliable makeSummandArrayByOperators(ArrayList<Operator> operators);
    boolean getGlobalSubtractionCheck();
    void setGlobalSubtractionCheck(boolean state);
}