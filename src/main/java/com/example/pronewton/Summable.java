package com.example.pronewton;
import com.example.pronewton.Operators.Operator;

import java.util.ArrayList;

public interface Summable {
    double getNumber();
    double getPower();
    boolean getForDeleting();
    void setNumber(double number);
    void setPower(double power);
    void setForDeleting(boolean state);
    void addFactor(double number, double power);
    void multiplication();
    ArrayList<Multipliable> getFactors();
    String getClassName();
    void printNames();
    Summable makeFactorArrayByOperators(ArrayList<Operator> operators);
    void multiplicateBrackets();
    void setParent(Multipliable parent);
    Multipliable getParent();
}
//makeFactorArrayByOperators
//multiplicateBrackets
