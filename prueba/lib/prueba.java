package prueba.lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class prueba implements ActionListener{

    //Definimos nuestros componentes a utilizar

    JFrame frame;
    JButton boton;
    JTextField textField;

    Font myFont = new Font("Osaka", Font.ITALIC, 20);

   


    //Constructor
    prueba(){
        //Aqui ejectuamos nuestra aplicación

        frame = new JFrame("Happy Birthday");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setResizable(false);
        frame.setLayout(null);


        textField = new JTextField();
        textField.setBounds(80, 100, 250, 50);
        textField.setEditable(false);
        textField.setFont(myFont);
 

        boton = new JButton("Click me");
        boton.setBounds(120,250,150,100);
        boton.addActionListener(this);
        boton.setFont(myFont);
        boton.setBackground(Color.CYAN);
     

        
        frame.add(textField);
        frame.add(boton);
        frame.setVisible(true);
    }

    public static void main (String[] args){

        prueba pr = new prueba();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == boton){
            textField.setText("Felizzz Cumpleee Taylorrr!");
        }
    }
        
    
}
