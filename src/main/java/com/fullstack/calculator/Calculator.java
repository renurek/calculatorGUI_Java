/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fullstack.calculator;

import javax.swing.JOptionPane;

/**
 *
 * @author Renu_15
 */
public class Calculator {
    
    private double result,value;
    private String expression="aa";
    public Calculator(String exp){
        expression = exp;
        result = 0;
        value=0;
    }
    
    public void splitExpression(){
        String[] opArr = expression.split("[/*+-]");
        String[] numArray = expression.split("[0-9]");
        double[] numArr = new double[numArray.length];
        
        for(int i=0; i<numArray.length;i++){
            try{
            numArr[i] = Double.parseDouble(numArray[i]);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                break;
            }
        }
        
        for (int i=0;i<opArr.length;i++) {
            
            
            switch (opArr[i]) {
                case "+": add("5");break;
                case "-": subtract("4");break;
                case "*": multiple("5");break;
                case "/": divide("4");break;
                case "^": break;
                default:JOptionPane.showMessageDialog(null, "Error in expression");
            }
        }
    }
            
    
    public double add(String x){
        value = result + Double.parseDouble(x);
        return value;
    }
    
    public double subtract(String x){
        value = result - Double.parseDouble(x);
        return value;
    }
    
    public double multiple(String x){
        value = result * Double.parseDouble(x);
        return value;
    }
    
    public double divide(String x){
        value = result / Double.parseDouble(x);
        return value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
    
    public void setResult(double r){
        result=r;
    }
    public double getResult(){
        return result;
    }
    
    public void setExpression(String exp){
        expression = exp;
    }
    
}
