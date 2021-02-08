/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Tres_en_raya{
    public static void main(String[] args) {
        Marco m = new Marco();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Marco extends JFrame{
    public Marco(){
        setVisible(true);
        setBounds(400, 200, 400, 400);
        setTitle("3 en raya");
        add(new Lamina());
    }
}
class Lamina extends JPanel{
    
    private int i=10;
    private JLabel jugador, time; 
    private JPanel lamina2, lamina3;
    private JButton btns[][];
    private String jugador1, jugador2;
    private int turno;
    private Timer contador;
    
    public Lamina(){
        
        turno=0;
        
        try{
            jugador1 = JOptionPane.showInputDialog(this, "Nombre del primer jugador", "Información", 1);
            while(jugador1.equalsIgnoreCase("")){
                jugador1 = JOptionPane.showInputDialog(this, "Debe escribir el nombre del primer jugador!", "Información", 2);
            }
        }catch(NullPointerException n){
            jugador1 = "Jugador 1";
        }        
        
        try{
            jugador2 = JOptionPane.showInputDialog(this, "Nombre del segundo jugador", "Información", 1);
            while(jugador2.equalsIgnoreCase("")){
                jugador2 = JOptionPane.showInputDialog(this, "Debe escribir el nombre del segundo jugador!", "Información", 2);
            }
        }catch(NullPointerException n){
            jugador2 = "Jugador 2";
        }
            
        Tiempo listener = new Tiempo();
        contador = new Timer(1000, listener);
        contador.start();
        setLayout(new BorderLayout());
        lamina2 = new JPanel();
        lamina2.setLayout(new GridLayout(1, 2));
        jugador = new JLabel("Turno: "+jugador1, JLabel.LEFT);
        time = new JLabel("Tiempo: ", JLabel.RIGHT);
        lamina2.add(jugador);
        add(lamina2, BorderLayout.NORTH);
        
        lamina3 = new JPanel();
        lamina3.setLayout(new GridLayout(3,3));
        EventoBototes eb = new EventoBototes();
        btns = new JButton[3][3];
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                btns[j][k] = new JButton();
                lamina3.add(btns[j][k]);
                btns[j][k].addActionListener(eb);
            }
        }
        
        add(lamina3, BorderLayout.CENTER);
    
    }
    
    public void continuar(){
        
        turno=0;
        
        int confirmacion = JOptionPane.showConfirmDialog(null, "Desea continuar?", "Confirmar", JOptionPane.YES_OPTION);                    
        if (confirmacion == 0) {
            try{
                jugador1 = JOptionPane.showInputDialog(null, "Nombre del primer jugador", "Información", 1);
                while(jugador1.equalsIgnoreCase("")){
                    jugador1 = JOptionPane.showInputDialog(null, "Debe escribir el nombre del primer jugador!", "Información", 2);
                }
            }catch(NullPointerException n){
                jugador1 = "Jugador 1";
            } 
            jugador.setText("Turno: " + jugador1);

            try{
                jugador2 = JOptionPane.showInputDialog(null, "Nombre del segundo jugador", "Información", 1);
                while(jugador2.equalsIgnoreCase("")){
                    jugador2 = JOptionPane.showInputDialog(null, "Debe escribir el nombre del segundo jugador!", "Información", 2);
                }
            }catch(NullPointerException n){
                jugador2 = "Jugador 2";
            }

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    btns[j][k].setBackground(new JButton().getBackground());
                    btns[j][k].setText("");
                    btns[j][k].setEnabled(true);
                }
            }

            i=10;
        
        }else{
            contador.stop();
            System.exit(0);
        }
        
    }
    
    private class Tiempo implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            
            time.setText("Tiempo: "+i);
            add(time, BorderLayout.SOUTH);
            i--;
            if (i<0) {
                if (turno==0) {
                    JOptionPane.showMessageDialog(null, "Tiempo concluido. Ganador: "+jugador2);                    
                    continuar();          
                }
                else{
                    JOptionPane.showMessageDialog(null, "Tiempo concluido. Ganador: "+jugador1);
                    continuar();
                }
            }
        }
    }
    
    private class EventoBototes implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
            JButton boton = (JButton) e.getSource();
            if (turno == 0) {
                if (boton.getText().equals("")) {
                    boton.setBackground(Color.red);
                    boton.setText("X");
                    boton.setEnabled(false);
                    turno = 1;
                    jugador.setText("Turno: "+jugador2);
                    i=10;
                }
            }
            else{
                if (turno==1) {
                    if (boton.getText().equals("")) {
                        boton.setBackground(Color.BLUE);
                        boton.setText("O");
                        boton.setEnabled(false);
                        turno = 0;
                        jugador.setText("Turno: "+jugador1);
                        i=10;
                    }
                }
            }
            comprobar();
        }
        
        public void comprobar(){
            int ganador=0;
            for (int j = 0; j < 3; j++) {
                if (btns[0][j].getText().equals("X") && btns[1][j].getText().equals("X") && btns[2][j].getText().equals("X")) {
                    ganador=1;
                }
                else if(btns[j][0].getText().equals("X") && btns[j][1].getText().equals("X") && btns[j][2].getText().equals("X")){
                    ganador=1;
                }
                else if(btns[0][0].getText().equals("X") && btns[1][1].getText().equals("X") && btns[2][2].getText().equals("X")){
                    ganador=1;
                }
                else if(btns[0][2].getText().equals("X") && btns[1][1].getText().equals("X") && btns[2][0].getText().equals("X")){
                    ganador=1;
                }
            }
            for (int j = 0; j < 3; j++) {
                if (btns[0][j].getText().equals("O") && btns[1][j].getText().equals("O") && btns[2][j].getText().equals("O")) {
                    ganador=2;
                }
                else if(btns[j][0].getText().equals("O") && btns[j][1].getText().equals("O") && btns[j][2].getText().equals("O")){
                    ganador=2;
                }
                else if(btns[0][0].getText().equals("O") && btns[1][1].getText().equals("O") && btns[2][2].getText().equals("O")){
                    ganador=2;
                }
                else if(btns[0][2].getText().equals("O") && btns[1][1].getText().equals("O") && btns[2][0].getText().equals("O")){
                    ganador=2;
                }
            }
            if (ganador==1) {
                //contador.stop();
                JOptionPane.showMessageDialog(null, "Ganador: "+jugador1);
                //System.exit(0);
                continuar();
            }
            else if (ganador==2) {
                //contador.stop();
                JOptionPane.showMessageDialog(null, "Ganador: "+jugador2);
                //System.exit(0);
                continuar();
            }
        }
    }
}