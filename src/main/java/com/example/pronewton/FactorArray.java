package com.example.pronewton;
import com.example.pronewton.Operators.Operator;
import java.util.ArrayList;
public class FactorArray implements Summable
{
    ArrayList<Multipliable> factors;
    double number;
    double power;
    boolean forDeleting;
    Multipliable parent;

    FactorArray(Multipliable... factors)
    {
        this.forDeleting = false;
        this.factors = new ArrayList<>();
        for(Multipliable factor : factors)
        {
            this.factors.add(factor);
        }
        this.number = 0;
        this.power = 0;
    }
    public void setParent(Multipliable parent){
        this.parent = parent;
    }
    public Multipliable getParent(){
        return this.parent;
    }

    public void addFactor(double number, double power)
    {
        this.factors.add(new ArgumentX(number, power));
    }
    public ArrayList<Multipliable> getFactors()
    {
        return this.factors;
    }

    @Override
    public double getNumber() {
        return this.number;
    }

    @Override
    public double getPower() {
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
    public void multiplication()
    {
        for(int i = 0; i < this.factors.size(); i++){
            if (!this.factors.get(i).getForDeleting()) {
                this.factors.get(i).summation();
                if (this.factors.get(i).getSummands() != null && this.factors.get(i).getSummands().size() == 1) {
                    Multipliable soloArg = new ArgumentX(this.factors.get(i).getSummands().get(0).getNumber(), this.factors.get(i).getSummands().get(0).getPower());
                    this.factors.remove(i);
                    this.factors.add(i, soloArg);
                }
            }
        }
        for(int i = 0; i < this.factors.size(); i++){
            if (!this.factors.get(i).getForDeleting()) {
                    for (int l = i + 1; l < this.factors.size(); l++) {
                        if (!this.factors.get(l).getForDeleting() && this.factors.get(l).getClass().getCanonicalName() == "com.example.pronewton.ArgumentX") {// при делении будет второй блок, аналогичный этому, но дополнительная проверка на, является ли процедура делением, и если "да", то:
                            this.factors.get(i).setNumber(this.factors.get(i).getNumber() * this.factors.get(l).getNumber()); // в этой строке вместо * будет /
                            this.factors.get(i).setPower(this.factors.get(i).getPower() + this.factors.get(l).getPower()); // в этой строке вместо + будет -
                            this.factors.get(l).setForDeleting(true);
                        }
                    }
            }
        }
        for(int i = this.factors.size()-1; i >= 0; i--)
        {
            if(this.factors.get(i).getForDeleting())
            {
                this.factors.remove(i);
            }
        }
        multiplicateBrackets();

        if(this.factors.size() == 1){
            for(int i = this.factors.get(0).getSummands().size()-1; i >= 0; i--){
                this.getParent().getSummands().add(this.factors.get(0).getSummands().get(i));
                this.factors.get(0).getSummands().remove(i);
                    this.forDeleting = true;
            }
        }
    }
    public Summable makeFactorArrayByOperators(ArrayList<Operator> operators){
        operators.add(0, new com.example.pronewton.Operators.Multiplication());
        FactorArray arrF = new FactorArray();
        Multipliable bufferF = new ArgumentX();
        Multipliable bufferAS = null;
        ArrayList<Operator> bracketContent = null;
        Operator coef = null;
        Operator power = null;
        int bracketCounter = 0;
        boolean bracketIndicator = false;
        bracketContent = new ArrayList<>();
        boolean divideCheck = false;
        Multipliable fakeArray = null;
        boolean subtractCheck = false;
        for (int i = 0; i < operators.size(); i++) {
            if(bracketCounter == 0) {
                if (operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.Subtraction") {
                    subtractCheck = true;
                }
                if (operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.Number") {
                    if (coef == null) {
                        bufferF = new ArgumentX();
                        coef = operators.get(i);
                        bufferF.setNumber(coef.getValue());

                    }else{
                        power = operators.get(i);
                        bufferF.setPower(power.getValue());
                        if (subtractCheck) {
                            fakeArray = new SummandArray(new ArgumentX(-bufferF.getNumber(), bufferF.getPower()), new ArgumentX(0, 0));
                            arrF.getFactors().add(fakeArray);
                            subtractCheck = false;
                        }else{
                            fakeArray = new SummandArray(new ArgumentX(bufferF.getNumber(), bufferF.getPower()), new ArgumentX(0, 0));
                            arrF.getFactors().add(fakeArray);
                        }
                        coef = null;
                        power = null;
                    }
                }
                }else{
                bracketContent.add(operators.get(i));
            }
            if(operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.OpenBracket") {
                if(bracketCounter == 0){
                    bracketContent = new ArrayList<>();
                }
                bracketCounter ++;
            }
            if(operators.get(i).getClass().getCanonicalName() == "com.example.pronewton.Operators.CloseBracket") {
                bracketCounter --;
                if(bracketCounter == 0){
                    bracketIndicator = true;
                }
            }
            if(bracketIndicator) {
                if (subtractCheck) {
                    bufferAS = new SummandArray();
                    bufferAS.setGlobalSubtractionCheck(true);
                    bufferAS = bufferAS.makeSummandArrayByOperators(bracketContent);
                    bufferF = bufferAS;
                    arrF.getFactors().add(bufferF);
                    subtractCheck = false;
                }else{
                    bufferAS = new SummandArray();
                    bufferAS = bufferAS.makeSummandArrayByOperators(bracketContent);
                    bufferF = bufferAS;
                    arrF.getFactors().add(bufferF);
                }
                bracketIndicator = false;
            }
        }
        return arrF;
    }
    public void multiplicateBrackets(){
        Multipliable bufferAS = new SummandArray();
        ArgumentX buffer = new ArgumentX();
        bufferAS.addSummand(1, 0);
        for(int m = 0; m < this.getFactors().size(); m++){
                for(int k = bufferAS.getSummands().size(); bufferAS.getSummands().size() < k * this.getFactors().get(m).getSummands().size();){
                    for(int i = 0; i < this.getFactors().get(m).getSummands().size(); i++){
                        bufferAS.addSummand(bufferAS.getSummands().get(0).getNumber() * this.getFactors().get(m).getSummands().get(i).getNumber(), bufferAS.getSummands().get(0).getPower() + this.getFactors().get(m).getSummands().get(i).getPower());
                    }
                    bufferAS.getSummands().remove(0);
                }
            this.getFactors().get(m).setForDeleting(true);
        }
        for(int i = 0; i < this.getFactors().size(); i++){
            if(this.getFactors().get(i).getForDeleting()){
                this.getFactors().remove(i);
            }
        }
        for(int i = 0; i < bufferAS.getSummands().size(); i++){
            if(bufferAS.getSummands().get(i).getNumber() == 0){
                bufferAS.getSummands().remove(i);
            }
        }
            this.getFactors().add(0, bufferAS);
    }
    public String getClassName(){
        return this.getClass().getCanonicalName();
    }
    public void printNames(){
        for (int i = 0; i < this.factors.size(); i++) {
            System.out.println(this.factors.get(i).getClassName() + " " + this.factors.get(i).getNumber() + " " + this.factors.get(i).getPower());
            if(this.factors.get(i).getClass().getCanonicalName() == "SummandArray"){
                this.factors.get(i).printNames();
            }
        }
    }
}

