package com.example.pronewton;
import com.example.pronewton.Operators.Operator;
import java.util.ArrayList;
public class SummandArray implements Multipliable{
    ArrayList<Summable> summands;
    double number;
    double power;
    boolean globalSubtractionCheck;

    boolean forDeleting;

    SummandArray(Summable... summands) {
        this.forDeleting = false;
        this.summands = new ArrayList<>();
        for(Summable summand : summands) {
            this.summands.add(summand);
        }
        this.number = 0;
        this.power = 0;
        globalSubtractionCheck = false;
    }
    public void makeFactorArray(Multipliable... factors) {
        FactorArray arr = new FactorArray(factors);
        this.summands.add(arr);
        arr.parent = this;
    }
    public int getSummandsSize()
    {
        return this.summands.size();
    }
    public Summable getSummable(int index)
    {
        return this.summands.get(index);
    }
    public ArrayList<Summable> getSummands()
    {
        return this.summands;
    }

    public void addSummand(double number, double power)
    {
        this.summands.add(new ArgumentX(number, power));
    }
    public void addFactorArray(double number, double power)
    {
        this.summands.add(new FactorArray(new ArgumentX(number, power)));
    }
    public String getCombo() {
        return null;
    }

    public double getNumber() {
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


    public void summation()
    {
        for(int i = 0; i < this.summands.size(); i ++){
            if(!this.summands.get(i).getForDeleting()){
                this.summands.get(i).multiplication();
                if(this.summands.get(i).getFactors() != null && this.summands.get(i).getFactors().size() == 1){
                    Summable soloArg = new ArgumentX(this.summands.get(i).getFactors().get(0).getNumber(), this.summands.get(i).getFactors().get(0).getPower());
                    this.summands.remove(i);
                    this.summands.add(i, soloArg);
                }
            }
        }
        for(int i = 0; i < this.summands.size(); i++) {
            if (!this.summands.get(i).getForDeleting()) {
                this.summands.get(i).multiplication();
            }
        }
        ArrayList<Summable> bufferAS = new ArrayList<>();
        ArgumentX bufferS = new ArgumentX();
        for(int i = 0; i < this.summands.size(); i++){
            for(int l = i + 1; l < this.summands.size(); l++){
                if(this.summands.get(i).getPower() == this.summands.get(l).getPower() && !this.summands.get(l).getForDeleting() && this.summands.get(l).getClass().getCanonicalName() == "com.example.pronewton.ArgumentX") {
                    this.summands.get(i).setNumber(this.summands.get(i).getNumber() + this.summands.get(l).getNumber());
                    this.summands.get(l).setForDeleting(true);
                }
            }
            }

        for(int i = this.summands.size()-1; i >= 0; i--){
            if(this.summands.get(i).getForDeleting()){
                this.summands.remove(i);
            }
        }
    }
    public Multipliable makeSummandArrayByOperators(ArrayList<Operator> operators)
    {
        if(operators.get(0).getClass().getCanonicalName() == "com.example.pronewton.Operators.Subtraction"){
            operators.add(0, new com.example.pronewton.Operators.Subtraction());
        }else{
            operators.add(0, new com.example.pronewton.Operators.Addition());
        }
        Multipliable arrS = new SummandArray();
        Summable bufferS = new ArgumentX();
        Summable bufferAF = null;
        ArrayList<Operator> piece = null;
        Operator coef = null;
        Operator power = null;
        int bracketCounter = 0;
        boolean bracketIndicator = false;
        piece = new ArrayList<>();
        boolean subtractCheck = false;
        Summable fakeArray = null;
        for (int i = 0; i < operators.size(); i++) {
            if(bracketCounter == 0) {
                if (operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.Subtraction") {
                    subtractCheck = true;
                }
                if (operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.Number") {
                    if (coef == null) {
                        bufferS = new ArgumentX();
                        coef = operators.get(i);
                        if(subtractCheck){
                            bufferS.setNumber(-(coef.getValue()));
                            subtractCheck = false;
                        }else{
                            bufferS.setNumber(coef.getValue());
                        }
                    } else {
                        power = operators.get(i);
                        bufferS.setPower(power.getValue());
                        if(this.globalSubtractionCheck){
                            bufferS.setNumber(-(bufferS.getNumber()));
                        }
                        arrS.getSummands().add(bufferS);
                        coef = null;
                        power = null;
                    }
                }
            }else{
                piece.add(operators.get(i));
            }

            if(operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.OpenBracket")
            {
                if(bracketCounter == 0){
                    piece = new ArrayList<>();
                }
                bracketCounter ++;

            }
            if(operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.CloseBracket")
            {
                bracketCounter --;
                if(bracketCounter == 0){
                    bracketIndicator = true;
                }
            }
            if(bracketIndicator){
                bufferAF = new FactorArray();
                bufferAF = bufferAF.makeFactorArrayByOperators(piece);
                bufferS = bufferAF;
                arrS.getSummands().add(bufferS);
                bracketIndicator = false;
            }
        }
        arrS.getSummands().add(new ArgumentX(0, 0));
        for(int i = 0; i < arrS.getSummands().size(); i++){
            arrS.getSummands().get(i).setParent(arrS);
        }
        return arrS;
    }

    @Override
    public boolean getGlobalSubtractionCheck() {
        return this.globalSubtractionCheck;
    }

    @Override
    public void setGlobalSubtractionCheck(boolean state) {
        this.globalSubtractionCheck = state;
    }

    public String getClassName()
    {
        return this.getClass().getCanonicalName();
    };
    public void printNames()
    {
        for (int i = 0; i < this.summands.size(); i++) {
            System.out.println(this.summands.get(i).getClassName() + " " + this.summands.get(i).getNumber() + " " + this.summands.get(i).getPower());
            if(this.summands.get(i).getClass().getCanonicalName() == "FactorArray")
            {
                this.summands.get(i).printNames();
            }
        }
    }
}