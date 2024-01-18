package Tictac.lib.principal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class main extends JPanel implements ActionListener {

    //Variables lógicas

    boolean jugadorX; 
    boolean findelJuego= false;
    int ganador = -1;
    int jugador1Gana=0, jugador2Gana=0;
    int [][] tablero = new int[3][3];

    //Variables de diseño

    int lineaHorizontal=5;
    int lineaVertical=270;
    int x = 15, y = 270;// Posicion de la primera línea
    int offset=95;//tamaño del cuadrado
    int a =0;
    int b = 5;
    int selX=0,  selY=0; //Esto guarda la posicon en que el mouse hace click;

    //Colores

    Color verdePastelNeon= new Color(25,254,119);
    Color rosadoPastelNeon = new Color(	255,179,194);
    Color blanco = new Color (255,255,255);
    Color fondoTeal= new Color (0,128,128);

    //Componentes
     JButton botonJugarOtraVez;


    public main(){
        //Creamos un objeto de la clase Dimension, este objeto sera la ventana del juego y le damos los valores de su tamaño
        Dimension size = new Dimension(500,400);
        //Nos permite darle los valores de tamaño preferibles a la ventana
        setPreferredSize(size);
        //Nos permite darle un valor maximo a la ventana
        setMaximumSize(size);
        //Nos permite darle un valor minimo a la ventan
        setMinimumSize(size);

        //Al ser el minimo y el maximo el mismo tamaño quiere decir que la ventana no puede redimensionarse

        //Creamos el boton de jugar otra vez
        botonJugarOtraVez = new JButton("Jugar Otra vez");
        //agregamos un evento para que el boton realice una accion cada vez que el usuario de click en el
        botonJugarOtraVez.addActionListener(this);
    }

    public static void main (String[] args){

        //Creamos un objeto JFrame que sera nuestra ventana y le damos un nombre
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.getContentPane();

        //Ahora creamos un objeto de nuestra clase main que es la que contiene nuestra ventana de TicTactoe y la agregamos el JFrame que contiene los elementos para diseñar
        main tPanel = new main();
        frame.add(tPanel);

        //Estas son configuraciones básicas para nuestra ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Nos permite cerrar la aplicacion luego que el usuario cierre la ventana
        frame.setResizable(false); //Nos permite negar la capacidad del usuario para redimensionar la ventana
        frame.pack();
        frame.setVisible(true);//Muestra la ventana
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        resetGame();
    }


    //Vamos a crear getters y setters para nuestras variables

    public JButton getJButton(){
        return botonJugarOtraVez;
    }

    public void resetGame(){
        jugadorX=true;
        ganador=-1;
        findelJuego=false;

        //Vamos a darle el valor de 0 o también puede interpretarse como "casilla vacía" a todo el tablero
        for(int i=0;i<3; i++ ){
            for(int j=0; j<3; j++){
                tablero[i][j]=0;
            }
        }
    }
}
