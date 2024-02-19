package calculator.lib;
//Importamos las librerias a usar

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculadora implements ActionListener{

    //Los componentes que vamos a utilizar para nuestra calculadora

    JFrame frame; //La ventana
    JTextField textField; //Entrada de texto
    JButton [] numeroBotones = new JButton[10]; //Aqui distribuiremos los botones con los números
    JButton [] botonFunciones = new JButton[9]; //Aquí distribuiremos los botones con las funciones
    JButton addButton,subButton,mulButton,divButton;
    JButton decButton,eqButton,delButton,clrButton, negButton;
    JPanel panel;

    Font myFont= new Font("Ink Free", Font.BOLD,30);
    
    double num1=0,num2=0,resultado=0;
    char simboloOperacion;


    //Constructor
    calculadora(){

        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(420,550);
        frame.setResizable(false);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);//Con esto nos aseguramos que el espacio no pueda ser editado por el teclado, ya que no permitimos que el campo pueda ser editado


        //Definimos los botones

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");
        negButton = new JButton("(-)");

        //Ahora añadimos los botones creados al nuestro arreglo que contendra los botones

        botonFunciones[0] = addButton;
        botonFunciones[1] = subButton;
        botonFunciones[2] = mulButton;
        botonFunciones[3] = divButton;
        botonFunciones[4] = decButton;
        botonFunciones[5] = eqButton;
        botonFunciones[6] = delButton;
        botonFunciones[7] = clrButton;
        botonFunciones[8] = negButton;

        for(int i = 0; i<9; i++){
                botonFunciones[i].addActionListener(this);
                botonFunciones[i].setFont(myFont);
                botonFunciones[i].setFocusable(false);
                botonFunciones[i].setBackground(Color.ORANGE);
                botonFunciones[i].setOpaque(true);
                botonFunciones[i].setBorderPainted(true);
        }


        //Con este for recorremos el arreglo creando los botones para los números

        for(int i = 0; i<10; i++){
            numeroBotones[i]= new JButton(String.valueOf(i));
            numeroBotones[i].addActionListener(this);
            numeroBotones[i].setFont(myFont);
            numeroBotones[i].setFocusable(false);
            numeroBotones[i].setBackground(Color.ORANGE);
            numeroBotones[i].setOpaque(true);
            numeroBotones[i].setBorderPainted(false);
        }

        negButton.setBounds(50,430,100,50);
        delButton.setBounds(150,430,100,50);
        clrButton.setBounds(250,430,100,50);
        


        //Ahora agregaremos nuestros componentes al nuestro contenedor o panel

        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));//Vamos a distribuir los componentes dentro del Panel como un Grid
        panel.setBackground(Color.BLACK);
       
        //Ahora que creamos nuestro contenedor, vamos a agregarle los botones al mismo
        //Primera fila
        panel.add(numeroBotones[1]);
        panel.add(numeroBotones[2]);
        panel.add(numeroBotones[3]);
        panel.add(addButton);

        //Segunda Fila
        panel.add(numeroBotones[4]);
        panel.add(numeroBotones[5]);
        panel.add(numeroBotones[6]);
        panel.add(subButton);

        //Tercera Fila
        panel.add(numeroBotones[7]);
        panel.add(numeroBotones[8]);
        panel.add(numeroBotones[9]);
        panel.add(mulButton);

        //Cuarta Fila
        panel.add(decButton);
        panel.add(numeroBotones[0]);
        panel.add(eqButton);
        panel.add(divButton);


        frame.setBackground(Color.BLACK);
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }



    public static void main (String[] args ){

        calculadora calc = new calculadora();
    }


    @Override
    //Esta funcion va a esuchar cada evento que se haga en la aplicación y aqui entonces es donde vamos a codificar las respuestas a esos eventos
    public void actionPerformed(ActionEvent e) {
       

        for(int i =0; i<10; i++){
            if(e.getSource() == numeroBotones[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == decButton){
            textField.setText(textField.getText().concat("."));
        }

        if(e.getSource() == addButton){
           num1= Double.parseDouble(textField.getText());
           simboloOperacion = '+';
           textField.setText("");

        }

        if(e.getSource() == subButton){
            num1= Double.parseDouble(textField.getText());
            simboloOperacion = '-';
            textField.setText("");
 
         }

         if(e.getSource() == mulButton){
            num1= Double.parseDouble(textField.getText());
            simboloOperacion = 'x';
            textField.setText("");
 
         }

         if(e.getSource() == divButton){
            num1= Double.parseDouble(textField.getText());
            simboloOperacion = '/';
            textField.setText("");
 
         }

         if(e.getSource() == eqButton){
            num2 = Double.parseDouble(textField.getText());

            switch (simboloOperacion) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case 'x':
                    resultado = num1 * num2;
                    break;

                case '/':
                    resultado = num1/num2;
                    break;
            }
            
            textField.setText(String.valueOf(resultado));
            num1=resultado;

         }

         if(e.getSource() == clrButton){
            textField.setText("");
 
         }

         if(e.getSource() == delButton){
            String string = textField.getText();
            textField.setText("");

            for(int i= 0; i<string.length()-1;i++){
                textField.setText(textField.getText()+string.charAt(i));
            }
 
         }

         if(e.getSource() == negButton){
            double temp = Double.parseDouble(textField.getText());
            temp = temp*-1;
            textField.setText(String.valueOf(temp));
 
         }

    }
}
