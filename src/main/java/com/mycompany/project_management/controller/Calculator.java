package com.mycompany.project_management.controller;

public class Calculator {
    public static void main(String args[])
    {
        CalculatorController cc=new CalculatorController();
        Double result=cc.add(4.5,8.5,2.2);
        System.out.println(result);
    }
}
