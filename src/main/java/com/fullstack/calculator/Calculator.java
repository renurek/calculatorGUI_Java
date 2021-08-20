/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.calculator;

import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Renu_15
 */
public class Calculator {

    private double a, b;
    private boolean error = false;
    Stack<Character> opStack;
    Stack<Double> valueStack;

    private double result, value;
    private String expression = "";

    public Calculator(String exp) {
        opStack = new Stack<>();
        valueStack = new Stack<>();

        expression = exp;
        result = 0;
        value = 0;
    }

    public double splitExpression() {
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
                JOptionPane.showMessageDialog(null, "Expression error");
                return 0.0;
            }else{
                JOptionPane.showMessageDialog(null, result+"");
                System.out.println(result);
                return result;
            }
        }
        
        return 0.0;
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
    
    public void operation(char t) {
        double a, b;
        if (valueStack.empty()) {
            JOptionPane.showMessageDialog(null, "expression error");
            error = true;
            return;
        } else {
            b = valueStack.pop();
        }
        if (valueStack.empty()) {
            JOptionPane.showMessageDialog(null, "expression error");
            error = true;
            return;
        } else {
            a = valueStack.pop();
        }

        double r = 0;
        switch (t) {
            case '+':
                r = add();
                break;
            case '-':
                r = subtract();
                break;
            case '*':
                r = multiple();
                break;
            case '/':
                r = divide();
                break;
            case '^':
                r = Math.pow(a, b);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Op error");
                error = true;
                break;
        }
        valueStack.push(r);

    }

    public double add() {
        value = a + b;
        return value;
    }

    public double subtract() {
        value = a - b;
        return value;
    }

    public double multiple() {
        value = a * b;
        return value;
    }

    public double divide() {
        value = a / b;
        return value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setResult(double r) {
        result = r;
    }

    public double getResult() {
        return result;
    }

    public void setExpression(String exp) {
        expression = exp;
    }

}
