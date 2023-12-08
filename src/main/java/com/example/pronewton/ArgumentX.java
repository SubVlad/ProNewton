package com.example.pronewton;
import com.example.pronewton.Operators.Operator;

import java.util.ArrayList;

public class ArgumentX implements Summable, Multipliable {
    double number;
    double power;
    String simbol;
    String combo;
    boolean forDeleting;

    ArgumentX(double number, double power)
    {
        this.number = number;
        this.simbol = "x^";
        this.power = power;
        this.forDeleting = false;

        this.combo = this.number + this.simbol + this.power;
    }

    ArgumentX()
    {
        this.simbol = "x^";
        this.forDeleting = false;
        this.combo = this.number + this.simbol + this.power;
    }
    public double getNumber()
    {
        return this.number;
    }
    public double getPower()
    {
        return this.power;
    }

    @Override
    public boolean getForDeleting() {
        return this.forDeleting;
    }

    @Override
    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public void setForDeleting(boolean state) {
        this.forDeleting = state;
    }

    @Override
    public void addFactor(double number, double power) {

    } //for containers
    @Override
    public void addSummand(double number, double power) {

    } //for containers

    @Override
    public void multiplication() {

    } //for containers
    @Override
    public void summation() {
    } //for containers

    @Override
    public ArrayList<Multipliable> getFactors() {
        return null;
    } //for containers



    @Override
    public ArrayList<Summable> getSummands() {
        ArrayList<Summable> arr = new ArrayList<>();
        arr.add(this);
        arr.add(new ArgumentX(0, 0));
        return arr;
    }

    @Override
    public int getSummandsSize() {
        return 0;
    }

    @Override
    public Summable getSummable(int index) {
        return null;
    }
    public String getClassName()
    {
        return this.getClass().getCanonicalName();
    };

    public void printNames()
    {

    }

    @Override
    public Summable makeFactorArrayByOperators(ArrayList<Operator> operators) {
        return null;
    }

    @Override
    public void multiplicateBrackets() {

    }

    @Override
    public void setParent(Multipliable parent) {

    }

    @Override
    public Multipliable getParent() {
        return null;
    }

    @Override
    public Multipliable makeSummandArrayByOperators(ArrayList<Operator> operators) {
        return null;
    }

    @Override
    public boolean getGlobalSubtractionCheck() {
        return false;
    }

    @Override
    public void setGlobalSubtractionCheck(boolean state) {

    }
}
