package com.example.pronewton;
import com.example.pronewton.Operators.*;
import com.example.pronewton.Operators.Number;
import java.util.ArrayList;
public class Expression {
    int index;
    static int quantity = 0;
    String input;
    ArrayList<Operator> operators;
    Multipliable root = new SummandArray();



    Expression(String input)
    {
        this.index = quantity;
        quantity ++;
        this.input = input;
        makeOperators();
        root = root.makeSummandArrayByOperators(operators);
    }
    public void makeOperators(){
        this.operators = new ArrayList<>();
        double element;
        String piece;
        for(int i = 1, k = 0; i <= this.input.length(); i ++){
            piece = this.input.substring(k,i);
            if(checkDoubleParsing(piece)){
                element = Double.parseDouble(piece);
                if(operators.size() > 0){
                    if(operators.get(operators.size()-1).getClass().getCanonicalName() == "com.example.pronewton.Operators.Number") {
                        operators.get(operators.size()-1).setValue(element);
                    }else{
                        operators.add(new Number(element));
                    }
                }else{
                    operators.add(new Number(element));
                }
            }else{
// ( 40     ) 41   + 43   - 45   * 42   / 47   ^ 94    = 61   x 120   y 121
                k = i;
                switch(piece.codePointAt(piece.length()-1))
                {
                    case 43:
                        operators.add(new Addition());
                        break;
                    case 45:
                        operators.add(new Subtraction());
                        break;
                    case 42:
                        operators.add(new Multiplication());
                        break;
                    case 47:
                        operators.add(new Division());
                        break;
                    case 120:
                        operators.add(new Sign());
                        break;
                    case 94:
                        operators.add(new PowerSign());
                        break;
                    case 40:
                        operators.add(new OpenBracket());
                        break;
                    case 41:
                        operators.add(new CloseBracket());
                        break;
                }
            }
        }
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