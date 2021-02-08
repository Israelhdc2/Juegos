/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora{
    public static void main(String[] args) {
        Marco m = new Marco();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class Marco extends JFrame{
    public Marco(){        
        setBounds(300, 200, 400, 400);
        setTitle("Calculadora");
        add(new Lamina());
        setVisible(true);
    }
}
class Lamina extends JPanel{

    private JPanel lamina2;
    private JTextArea display;
    private boolean bool;
    private String ultimaoperacion;
    private double resultado;
    private double memoria;
    private double medioresultado;
    private int cont;
    
    public Lamina(){
        cont=0;
        ultimaoperacion = "=";
     
        bool=true;
        setLayout(new BorderLayout());
        display = new JTextArea("0", 1, 0);
        display.setBackground(new Color(76, 209, 201));        
        add(display, BorderLayout.NORTH);
        
        lamina2 = new JPanel();
        ActionListener n = new EventoNumero();
        ActionListener o = new EventoOperar();
        ActionListener o2 = new EventoOperar2();
        lamina2.setLayout(new GridLayout(10, 5, 5, 5));
        agregarbtn("OFF", o2);
        agregarbtn("¤", o);
        agregarbtn("$", o2);
        agregarbtn("€", o2);
        agregarbtn("C", o2);
        agregarbtn("asin", o2);
        agregarbtn("acos", o2);
        agregarbtn("atan", o2);
        agregarbtn("e^x", o2);
        agregarbtn("10^x", o2);
        agregarbtn("sin^-1", o2);
        agregarbtn("cos^-1", o2);
        agregarbtn("tan^-1", o2);
        agregarbtn("ln", o2);
        agregarbtn("log",o2);
        agregarbtn("sin",o2);
        agregarbtn("cos",o2);
        agregarbtn("tan",o2);
        agregarbtn("π", n);
        agregarbtn("e", o2);
        agregarbtn("sqrt", o2);
        agregarbtn("x^2", o2);
        agregarbtn("1/x", o2);
        agregarbtn("x!", o2);
        
        agregarbtn("x^M", o);
        agregarbtn("+-", o2);
        agregarbtn("(", o);
        agregarbtn(")", o);
        agregarbtn("exp",o2);
        
        agregarbtn("MC",o2);
        agregarbtn("7", n);
        agregarbtn("8", n);
        agregarbtn("9", n);
        agregarbtn("/", o);
        
        agregarbtn("MS",o2);
        agregarbtn("4", n);
        agregarbtn("5", n);
        agregarbtn("6", n);
        agregarbtn("*", o);
        
        agregarbtn("MR",o2);
        agregarbtn("1", n);
        agregarbtn("2", n);
        agregarbtn("3", n);
        agregarbtn("-", o);
        
        agregarbtn("M-",o2);
        agregarbtn("0", n);
        agregarbtn(".", n);
        agregarbtn("=", o);
        agregarbtn("+", o);
        
        agregarbtn("M+",o2);
        add(lamina2, BorderLayout.CENTER);
    }
    
    private void agregarbtn(String b, ActionListener evento){
        JButton btn = new JButton(b);
        btn.setBackground(Color.gray);
        if (b == "OFF" || b == "C") {
            btn.setBackground(Color.red);
        }
        if(b == ")" || b == "("){
            btn.setEnabled(false);
        }
        lamina2.add(btn);
        btn.addActionListener(evento);
    }
    private class EventoNumero implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bool==true) {
                display.setText("");
                bool=false;
            }
            String num = e.getActionCommand();
            if (num == "π") {
                display.setText(""+Math.PI);
            }else{
                display.setText(display.getText() + num);
            }
        }
    }
    
    private class EventoOperar implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String op=e.getActionCommand();
            calcular(Double.parseDouble(display.getText()));
            ultimaoperacion = op;
            bool = true;
        }
        private void calcular(double x){
            if (ultimaoperacion.equals("+")) {
                resultado += x;
            }
            else if (ultimaoperacion.equals("-")) {
                resultado -= x;
            }
            else if (ultimaoperacion.equals("/")) {
                resultado /= x;
            }
            else if (ultimaoperacion.equals("*")) {
                resultado *= x;
            }
            else if (ultimaoperacion.equals("¤")) {
                resultado *= x;
            }
            else if (ultimaoperacion.equals("x^M")) {
                resultado = Math.pow(resultado, x);
            }
            else if (ultimaoperacion.equals("=")) {
                resultado = x;
            }
            
            display.setText(""+resultado);
        }
    }
  
    private class EventoOperar2 implements ActionListener{
        private String op2;
        @Override
        public void actionPerformed(ActionEvent e) {
            op2=e.getActionCommand();
            
            calcular(Double.parseDouble(display.getText()));
          
            bool = true;
        }
        private void calcular(double x){
            
            if (op2.equals("x^2")) {
                resultado = Math.pow(x, 2);
            }
            else if (op2.equals("sqrt")) {
                resultado = Math.sqrt(x);
            }
            else if (op2.equals("asin")) {
                resultado = Math.asin(x);
            }
            else if (op2.equals("acos")) {
                resultado = Math.acos(x);
            }
            else if (op2.equals("atan")) {
                resultado = Math.atan(x);
            }
            else if (op2.equals("sin^-1")) {
                resultado = Math.asin(x);
            }
            else if (op2.equals("cos^-1")) {
                resultado = Math.acos(x);
            }
            else if (op2.equals("tan^-1")) {
                resultado = Math.atan(x);
            }
            else if (op2.equals("sin")) {
                resultado = Math.sin(x);
            }
            else if (op2.equals("cos")) {
                resultado = Math.cos(x);
            }
            else if (op2.equals("tan")) {
                resultado = Math.tan(x);
            }
            else if (op2.equals("ln")) {
                resultado = Math.log10(x);
            }
            else if (op2.equals("log")) {
                resultado = Math.log(x);
            }
            else if (op2.equals("e^x")) {
                resultado = Math.exp(x);
            }
            else if (op2.equals("e")) {
                resultado = Math.exp(x);
            }
            else if (op2.equals("10^x")) {
                resultado = Math.pow(10, x);
            }
            else if (op2.equals("OFF")) {
                System.exit(0);
            }
            else if (op2.equals("C")) {
                resultado=0;
            }
            else if (op2.equals("1/x")) {
                resultado = 1/x;
            }
            else if (op2.equals("+-")) {
                if (x>0) {
                    resultado = x-(2*x);
                }else if(x<0){
                    resultado = x+2*(-x);
                }
            }
            else if (op2.equals("x!")) {
                double fact=1;
                for (int i = 1; i <= x; i++) {
                    fact = fact*i;
                }
                resultado = fact;
            }
            else if (op2.equals("$")) {
                resultado = x*0.31;
            }
            else if (op2.equals("€")) {
                resultado = x*0.26;
            }
            else if (op2.equals("MR")) {
                resultado = medioresultado;
            }
            else if (op2.equals("M+")) {
                if (cont == 0) {
                    memoria = x;
                    resultado = x;
                    cont++;
                }else{
                    medioresultado = memoria+x;
                    resultado=x;
                }
            }
            else if (op2.equals("M-")) {
                if (cont == 0) {
                    memoria = x;
                    resultado = x;
                    cont++;
                }else{
                    medioresultado = memoria-x;
                    resultado=x;
                }
            }
            else if (op2.equals("MC")) {
                memoria = 0;
                cont=0;
                resultado = x;
            }
            
            display.setText(""+resultado);
        }
    }
    
}
