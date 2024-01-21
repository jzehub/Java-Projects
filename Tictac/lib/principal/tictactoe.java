package Tictac.lib.principal;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.*;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class tictactoe extends JPanel implements ActionListener {

    //Variables lógicas

    boolean jugadorX = true; 
    boolean findelJuego= false;
    int ganador = -1;
    int jugador1Gana=0, jugador2Gana=0;
    int [][] tablero = new int[3][3];

    //Variables de diseño

    int lineaHorizontal=5;
    int lineaVertical=270;
    int x = 15, y = 100;// Posicion de la primera línea
    int offset=95;//tamaño del cuadrado
    int a =0;
    int b = 5;
    int selX=0,  selY=0; //Esto guarda la posicon en que el mouse hace click;

    //Colores

    Color turtle= new Color(0x80bdab);
    Color naranja = new Color(	0xfdcb9e);
    Color blanco = new Color (0xf7f7f7);
    Color gray= new Color (0x3f3f44);

    //Componentes
     JButton botonJugarOtraVez;


    public tictactoe(){
        //Creamos un objeto de la clase Dimension, este objeto sera la ventana del juego y le damos los valores de su tamaño
        Dimension size = new Dimension(420,300);
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

        botonJugarOtraVez.setVisible(false);
        
        addMouseListener(new XOListener());
    }

    public static void main (String[] args){

        //Creamos un objeto JFrame que sera nuestra ventana y le damos un nombre
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.getContentPane();

        //Ahora creamos un objeto de nuestra clase main que es la que contiene nuestra ventana de TicTactoe y la agregamos el JFrame que contiene los elementos para diseñar
        tictactoe tPanel = new tictactoe();
        frame.add(tPanel);

        //Estas son configuraciones básicas para nuestra ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Nos permite cerrar la aplicacion luego que el usuario cierre la ventana
        frame.setResizable(false); //Nos permite negar la capacidad del usuario para redimensionar la ventana
        frame.pack();
        frame.setVisible(true);//Muestra la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        resetGame();
    }


    //Vamos a crear getters y setters para nuestras variables

    public JButton getJButton(){
        return botonJugarOtraVez;
    }

    public void setjugador1Gana(int a){
        jugador1Gana = a;
    }
    public void setjugador2Gana(int a){
        jugador2Gana = a;
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

        //Ahora vamos a ocultar el boton para que no aparezca mientras la partida sigue en curso
        getJButton().setVisible(false);
    }

    public void  paintComponent(Graphics page){
        super.paintComponent(page); //Este era el maldito EROR

        drawBoard(page);
        drawUI(page);
        drawGame(page);
    }

    //Este método nos permite dibujar el tablero
    public void drawBoard(Graphics page){
        //Nos sirve para ajustar el color del fondo
        setBackground(turtle);

        page.setColor(gray);
        //Nos permite especificar un punto especifico de la pantalla donde queremos que este de eso color
        page.fillRoundRect(x, y, lineaVertical, lineaHorizontal, 5, 30);//Con esto estamos dibujando las líneas de el tablero especificando cosas como posicion en x, posicion en y, ancho, alto, radio.
        page.fillRoundRect(x, y + offset, lineaVertical, lineaHorizontal, 5, 30);//Creamos otra línea de similares caracterísitcas solo que en este caso le sumamos la variable offset que contiene la distancia de separación que debe haber entre líneas
        //Ahora creamos las líneas verticales invirtiendo los valores de posición
        page.fillRoundRect(y,x, lineaHorizontal, lineaVertical, 5, 30);
        page.fillRoundRect(y + offset,x, lineaHorizontal, lineaVertical, 5, 30);

    }   

    //Este método nos permitira diseñar el menu

    public void drawUI(Graphics page){
        
        //Colores y tipografía
        page.setColor(gray);
        page.fillRect(300, 0, 120, 300);//Esto nos permite crear un rectángulo, y luego le especificamos su posición y tamaño
        Font font = new Font("Helvetica", Font.PLAIN,20);
        page.setFont(font);

        //Contador de victorias (gráficamente)

        page.setColor(blanco);
        page.drawString("Win Count", 320, 30);//Nos permite escribir lo que queremos que se muestre en pantalla, al igual que nos pide su posición
        page.drawString("X: " + jugador1Gana, 332,70);
        page.drawString("O: " + jugador2Gana, 332, 105);

        /*  //Imprimiremos nuestro icono de X

                ImageIcon xIcon = new ImageIcon("orangex.png");
                Image xImg = xIcon.getImage();
                page.drawImage(xImg, 329, 47,null);
            
            

            //Imprimiremos el 0
            page.setColor(blanco);
            page.fillOval(328, 80, 30, 30);
            page.setColor(gray);
            page.fillOval(334,85, 19, 19);
        */
        ImageIcon xIcon = new ImageIcon("orangex.png");
        Image xImg = xIcon.getImage();

        //Ahora mostraremos el turno o el ganador del juego si es el caso

        page.setColor(blanco);

        Font font1 = new Font("Serif",Font.ITALIC,18);
        page.setFont(font1);

        if (findelJuego) {
            //Mostrar ganador

            if (ganador==1) {
                page.drawString("El ganador es: ", 310, 150);
                page.drawImage(xImg, 335, 160, null);

            } else if (ganador==2) {

                page.drawString("El ganador es: ", 310, 150);
                page.setColor(blanco);
                page.fillOval(332, 160, 50, 50);
                page.setColor(gray);
                page.fillOval(342,170, 30, 30);

            }else if (ganador==3){
                page.drawString("Es un Empate!!",330,178);
            }

        }else{
            //Mostrar turno

            Font font2 = new Font("Serif", Font.ITALIC,15);
            page.setFont(font2);
            page.drawString("Es el turno de: ", 310, 140);

            if (jugadorX) {
                page.drawString("X", 350, 170);
            } else {
                page.drawString("O", 350, 170);
            }
        }

        //Imprimir o dibujar Imagen

        page.setColor(blanco);
        Font font3 = new Font("Serif", Font.ITALIC,8);
        page.setFont(font3);
        page.drawString("Debería haber una imagen aqui", 310, 190);

        try {
            Image cookie = Toolkit.getDefaultToolkit().getImage("hola.png");
            page.drawImage(cookie, 345, 235,30,30,this);
        } catch (Exception e) {
           System.out.println("El error es: " + e);
        }


    }


    public void drawGame(Graphics page){
        tablero[0][0]=1;
        tablero[0][1]=2;
        for(int i = 0; i<3; i++){
            
            for ( int j = 0; j<3;j++){
                if (tablero[i][j]==0) {
                    //no modificamos nada se deja igual
                    System.out.println("op1");
                }else if (tablero[i][j]==1){

                   

                    page.setColor(gray);
                    Font font4 = new Font("Serif", Font.ITALIC,50);
                    page.setFont(font4);
                    page.drawString("X", 33 + offset *i, 70 + offset * j);

                     //Esta pendejada no funciona y no se porqueeeee
                    System.out.println("op2");
                    ImageIcon xIcon= new ImageIcon("Equis.png");
                    Image xImg = xIcon.getImage();
                    page.drawImage(xImg, 30+offset*1, 30+offset *j,null);

                    page.drawString("X", i, j);
                }else if (tablero[i][j]==2){
                    System.out.println("op3");
                    page.setColor(blanco);
                    page.fillOval(30 + offset*i, 30 + offset *j, 50, 50);
                    page.setColor(turtle);
                    page.fillOval(40 + offset * i, 40 + offset * j, 30, 30);
                }
            }
        }
    }

    public class XOListener implements MouseListener {

        //Estos son los métodos que contiene la clase MouseListener

        @Override
        public void mouseClicked(MouseEvent event) {
            // TODO Auto-generated method stub

            selX = -1;
            selY = -1;
            if (findelJuego == false) {
                a = event.getX();
                b = event.getY();
                System.out.println("Clicker => x: " + a +  "y" + b);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
        }

        
        
    }

}
