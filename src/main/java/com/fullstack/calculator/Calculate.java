package com.fullstack.calculator;

import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Renu_15
 */
public class Calculate {

    private Stack<Character> opStack;
    private Stack<Double> valueStack;
    private boolean error = false;

    public Calculate() {
        opStack = new Stack<>();
        valueStack = new Stack<>();
    }

    public boolean isOp(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == 's';
    }

    public int getPrecedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        }
        if (ch == '*' || ch == '/') {
            return 2;
        }
        return 0;
    }

    public String splitExpression(String expression) throws Exception{
        String[] tokens = expression.split(" ");
        
        for(int i=0;i<tokens.length;i++){
            String nextToken = tokens[i]; 
            
            char ch = nextToken.charAt(0);
            if(ch>='0' && ch<='9'){
                double val = Double.parseDouble(nextToken);
                valueStack.push(val);
            }else if(isOp(ch)){
                if(opStack.empty()){
                    opStack.push(ch);
                }else{
                    while(!opStack.empty()&& getPrecedence(ch)<=getPrecedence(opStack.peek())){
                        char operationToBeDOne = opStack.peek();
                        opStack.pop();
                        operation(operationToBeDOne);
                    }
                    opStack.push(ch);
                }
            } 
        }
        while(!opStack.empty() && isOp(opStack.peek())){
            char operationToBeDone = opStack.peek();
            opStack.pop();
            operation(operationToBeDone);
        }
        if(error==false){
            double result = valueStack.peek();
            valueStack.pop();
            if(!opStack.empty()){
                return "Expression error";
            }else{
                return result+"";
            }
        }
        
        return 0.0+"";
    }
    

    public String operation(char t) {
        double a, b;
        if (valueStack.empty()) {
            error = true;
            return "Expression error";
        } else {
            b = valueStack.pop();
        }
        if (valueStack.empty() && t!='s') {
            error = true;
            return "Expression error";
        } else if(t=='s'){
            a = 0.5;
        }else{
            a = valueStack.pop();
        }

        double r = 0;
        switch (t) {
            case '+':
                r = a + b;
                break;
            case '-':
                r = a - b;
                break;
            case '*':
                r = a * b;
                break;
            case '/':
                r = a / b;
                break;
            case '^':
                r = Math.pow(a, b);
                break;
            case 's':
                r = Math.pow(b, a);
                break;
            default:
                error = true;
                return "Operator error";
        }
        valueStack.push(r);
        return "";
    }
    
    public static void main(String[] args) throws Exception{
        Calculate c = new Calculate();
        System.out.println(c.splitExpression("sqrt 6"));
        System.out.println(c.splitExpression("6 + 4"));
        System.out.println(c.splitExpression("6 - 3"));
        System.out.println(c.splitExpression("36 / 3"));
        System.out.println(c.splitExpression("6 / 4"));
        System.out.println(c.splitExpression("3 ^ 3"));
        System.out.println(c.splitExpression("sqrt 6 + 10"));
        System.out.println(c.splitExpression("6.4 / 2 / 1 - * 3"));
        
        
        
    }

}
