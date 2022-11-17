package ba.unsa.etf.rpr;

import java.util.Stack;

public class ExpressionEvaluator {
    private Stack<String> ops;
    private Stack<Double> vals;

    private boolean isANumber(String s){
        if (s.length() == 0) return false;
        int numberOfDots=0;
        for(int i =0; i<s.length();i++){
            if((s.charAt(0) >'9' || s.charAt(0)<'0') && s.charAt(0) != '-') return false;
            if((s.charAt(i) >'9' || s.charAt(i)<'0') && s.charAt(i) != '.') return false;
            if(s.charAt(i) == '.' && numberOfDots != 0)return false;
            if(s.charAt(i) == '.') numberOfDots ++;
        }
        return true;
    }

    public Double evaluate(String str) throws  RuntimeException{
        ops = new Stack<String>();
        vals = new Stack<Double>();
        int i = 0,br = 1, numberOfLeftParetheses = 0, numberOfRightParentheses = 0, numberOfBasicArm = 0;
        for( String s : str.split(" ")){
            if(str.split(" ").length == 1) throw new RuntimeException("Neispravan izraz");
            if ( numberOfBasicArm > numberOfLeftParetheses) throw new RuntimeException("Neispravne zagrade");
            if ( numberOfLeftParetheses < numberOfRightParentheses) throw new RuntimeException("Neispravne zagrade");
            if(s.equals("(")) numberOfLeftParetheses++;
            else if(s.equals(" "));
            else if (s.equals("+")){
                ops.push(s);
                numberOfBasicArm++;
            }
            else if (s.equals("-")){
                ops.push(s);
                numberOfBasicArm++;
            }
            else if (s.equals("*")){
                numberOfBasicArm++;
                ops.push(s);
            }
            else if (s.equals("/")){
                ops.push(s);
                numberOfBasicArm++;
            }
            else if (s.equals("sqrt"))ops.push(s);
            else if (isANumber(s)) vals.push(Double.parseDouble(s));
            else if (s.equals(")")){
                numberOfRightParentheses++;
                if ( numberOfLeftParetheses < numberOfRightParentheses) throw new RuntimeException("Neispravne zagrade");
                if ( vals.size() == 0) throw new RuntimeException("Neispravne zagrade");
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v= vals.pop()+v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) {
                    if ( v == 0 ) throw new RuntimeException("Dijeljenje sa 0");
                    v = vals.pop() / v;

                }
                else if (op.equals("sqrt")){

                    if ( v < 0 ) throw new RuntimeException("Negativna vrijednost potkorijene veličine");
                    v = Math.sqrt(v);
                }
                vals.push(v);

            }

            else {
                try {
                    vals.push(Double.parseDouble(s));
                }catch(Exception e){
                    throw new RuntimeException("Neispravan izraz");
                }
            }


        }
        if ( numberOfLeftParetheses != numberOfRightParentheses) throw new RuntimeException("Neispravne zagrade");
        if ( vals.size() != 1) throw new RuntimeException("Neispravan izraz");
        if ( ops.size() != 0 ) throw new RuntimeException("Neispravan izraz");
        return vals.pop();



    }
}
