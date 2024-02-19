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
import javax.swing.ImageIcon;


public class tictactoe extends JPanel implements ActionListener {

    //Variables lógicas

    boolean jugadorX = true;  //Esta variable no indica si el turno le pertenece al jugador X , si es falso indica que el turno es de las O
    boolean findelJuego= false; //Con esta determinamos si el juego ha acabado, nos sirve para poder mostrar quien gana
    int ganador = -1; // Almacena quien es quien gana la partida (1 si es X, 2 si son las O y 3 si es empate)
    int jugador1Gana = 0, jugador2Gana=0; //Esto es un contador que nos permite almacenar las victorias de cada bando
    int [][] tablero = new int[3][3]; //Este es nuestro tablero, donde cada casilla tendrá un valor dependiendo si es X,0 o esta libre la casilla



    //Variables de diseño

    int lineaHorizontal=5; //Almacena el ancho de la línea que divide el tablero
    int lineaVertical=270; //Almacena el largo de la línea que divide el tablero
    int x = 15, y = 100;// Posicion de la primera línea
    int offset=95;//Espacio que existe entre una línea y otra
    int a; //Almacena la posición en el eje X del usuario
    int b; //Almacena la posición en el eje Y del usuario
    int selX=0;//Almacena en que parte del tablero hizo click el usuario en el eje X
    int selY=0; //Almacena en que parte del tablero hizo click el usuario en el eje Y

    //Colores

    Color turtle= new Color(0x80bdab);
    Color naranja = new Color(	0xfdcb9e);
    Color blanco = new Color (0xf7f7f7);
    Color gray= new Color (0x3f3f44);

    //Componentes
    JButton jButton; //Creamos este boton que nos permitirá realizar una acción cuando este sea presionado



    //Esta clase va a almacenar los componentes y los ajustes que tendrá el juego
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

        
       jButton = new JButton("Jugar otra vez");//Ahora instanciamos nuestro botón y le damos un nombre

       jButton.addActionListener(this);//Al botón le añadimos un método que nos permite "escuchar" cuando el usuario haga click en el botón
       add(jButton);
       jButton.setVisible(false);//Con esto ocultamos el botón con el método setVisible
      
       addMouseListener(new XOListener()); //Esto escucha los eventos del ratón, implementando la clases XOListener que contiene métodos a las acciones que hace el mouse
        
        
    }

    //Con esta función vamos a "reiniciar el juego"

    public void resetGame(){    
        //Esta función se encarga de "limpiar" el tablero al igual que reiniciar las variables a su default
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


    //El método paintComponentes nos sirve para customizar el JPanel donde nuestro código se muestra y se le pasa el objeto de tipo Graphics
    public void  paintComponent(Graphics page){
        //Le pasamos el parametro al la clase superior que nos ayudara a modificar los componentes
        super.paintComponent(page); 

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

        

        //Ahora mostraremos el turno o el ganador del juego si es el caso

        page.setColor(blanco);
        Font font1 = new Font("Serif",Font.ITALIC,18);
        page.setFont(font1);

        if (findelJuego) {
            //Mostrar ganador

            if (ganador==1) {
                page.drawString("El ganador es X: ", 290, 150);
                //page.drawImage(xImg, 335, 160, null);

            } else if (ganador==2) {

                page.drawString("El ganador es: ", 310, 150);
                page.setColor(blanco);
                page.fillOval(332, 160, 50, 50);
                page.setColor(gray);
                page.fillOval(342,170, 30, 30);

            }else if (ganador==3){
                page.drawString("Es un Empate!!",310,178);
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
        page.drawString("Debería haber una imagen aqui", 310, 220);

        try {
            Image cookie = Toolkit.getDefaultToolkit().getImage("hola.png");
            page.drawImage(cookie, 345, 235,30,30,this);
        } catch (Exception e) {
           System.out.println("El error es: " + e);
        }


    }

    public void drawGame(Graphics page){
        for(int i = 0; i<3; i++){
            
            for ( int j = 0; j<3;j++){
                if (tablero[i][j]==0) {
                    //no modificamos nada se deja igual
                 
                }else if (tablero[i][j]==1){

                   

                    page.setColor(gray);
                    Font font4 = new Font("Serif", Font.ITALIC,50);
                    page.setFont(font4);
                    page.drawString("X", 33 + offset *i, 70 + offset * j);

                     //Esta pendejada no funciona y no se porqueeeee
                    ImageIcon xIcon= new ImageIcon("Equis.png");
                    Image xImg = xIcon.getImage();
                    page.drawImage(xImg, 30+offset*1, 30+offset *j,null);

                    page.drawString("X", i, j);
                }else if (tablero[i][j]==2){
                  
                    page.setColor(blanco);
                    page.fillOval(30 + offset*i, 30 + offset *j, 50, 50);
                    page.setColor(turtle);
                    page.fillOval(40 + offset * i, 40 + offset * j, 30, 30);
                }
            }
        }
        repaint();
    }



    public void verificarGanador(){

        if (findelJuego==true) {
            System.out.println("Juego terminado");
            return;
        }

        //Verificaremos si el jugador gano con un línea vertical

        int temp = -1; //Temporal lo que va a hacer es guardar si el valor en una posición específica del tablero es X o O

        if((tablero[0][0] == tablero[0][1]) && (tablero[0][1] == tablero[0][2]) && (tablero[0][0]!=0)){

            temp= tablero[0][0];
            System.out.println("Vertical1");
            System.out.println(temp);

        }else if ((tablero[1][0] == tablero[1][1]) &&(tablero[1][1] == tablero[1][2]) && (tablero[1][0]!=0) ){

            temp = tablero[1][0];
            System.out.println("Vertical2");
            System.out.println(temp);

        }else if ((tablero[2][0] == tablero[2][1])&& (tablero[2][1] == tablero[2][2]) &&(tablero[2][0] != 0)){

            temp = tablero[2][0];
            System.out.println("Vertical3");
            System.out.println(temp);

        }//Horizontal
        else if((tablero[0][0]== tablero [1][0])&&(tablero[1][0] == tablero[2][0]) && (tablero[0][0] != 0)){

            temp = tablero[0][0];
            System.out.println("Horizontal1");
            System.out.println(temp);

        }else if ((tablero[0][1] == tablero [1][1]) && (tablero [1][1]== tablero[2][1]) && (tablero[0][1]!=0)){

            temp = tablero [0][1];
            System.out.println("Horizontal2");
            System.out.println(temp);

        }else if ((tablero[0][2] == tablero[1][2])&& (tablero[1][2] == tablero [2][2])&&(tablero[0][2]!=0)){

            temp = tablero[0][2];
            System.out.println("Horizontal3");
            System.out.println(temp);

        }//Diagonal

        else if ((tablero[0][0]==tablero[1][1])&&(tablero[1][1]==tablero[2][2])&&(tablero[0][0]!=0)){

            temp = tablero[0][0];
            System.out.println("Diagonal 1");
            System.out.println(temp);

        }else if ((tablero[2][0]== tablero[1][1]) && (tablero[1][1]==tablero[0][2])&&(tablero[2][0]!=0)){
            temp= tablero[2][0];
            System.out.println("Diagonal2");
            System.out.println(temp);

        }else{
            //ahora verificaremos que no haya espacios vaciós o que haya un empate

            boolean sinTerminar = false;
            for(int i = 0; i<3;i++){
                for(int j= 0; j<3;j++){
                        if(tablero[i][j]==0){
                            sinTerminar = true;
                            break;
                        }
                }
            }

            if (sinTerminar == false){
                temp=3;
            }

        }

        if (temp>0) {
            ganador=temp;
            if(ganador == 1){
                jugador1Gana++;
                System.out.println("Ganan las X");
            }else if ( ganador == 2){
                jugador2Gana++;
                System.out.println("Ganan los O");
            }else if ( ganador == 3){
                System.out.println("Es un empate");
                
            }

            findelJuego=true;
            getJButton().setVisible(true);

            

        }

    }

    public JButton getJButton(){
        return jButton;
    }

    public void setjugador1Gana(int a){
        jugador1Gana = a;
    }
    public void setjugador2Gana(int a){
        jugador2Gana = a;
    }


    public static void main (String[] args){

        //Creamos un objeto JFrame que sera nuestra ventana y le damos un nombre
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.getContentPane();

        //Ahora creamos un objeto de nuestra clase main que es la que contiene nuestra ventana de TicTactoe y la agregamos el JFrame que contiene los elementos para diseñar
        tictactoe tPanel = new tictactoe();
        frame.add(tPanel);

        //Ahora vamos a crear un archivo tipo txt para almacenar al ganador
        frame.addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){

                try {
                    File file = new File("score.txt");
                    Scanner sc = new Scanner(file);
                    tPanel.setjugador1Gana(Integer.parseInt(sc.nextLine()));
                    tPanel.setjugador2Gana(Integer.parseInt(sc.nextLine()));
                    sc.close();
                }catch(IOException io){
                    File file = new File("score.txt");
                }

            }

            public void windowClosed(WindowEvent e){

                try{
                    PrintWriter pw = new PrintWriter("score.txt");
                    pw.write(tPanel.jugador1Gana + "\n");
                    pw.write(tPanel.jugador2Gana + "\n");
                    pw.close();
                }catch(FileNotFoundException fe){

                }

            }


        });

        //Estas son configuraciones básicas para nuestra ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Nos permite cerrar la aplicacion luego que el usuario cierre la ventana
        frame.setResizable(false); //Nos permite negar la capacidad del usuario para redimensionar la ventana
        frame.pack();
        frame.setVisible(true);//Muestra la ventana
    }

    public class XOListener implements MouseListener {

        //Estos son los métodos que contiene la clase MouseListener 

        public void mouseClicked(MouseEvent event) {
            // TODO Auto-generated method stub

            selX = -1;
            selY = -1;
            if (findelJuego == false) {

                //Esto nos permite registrar la posición tanto en x como en Y donde el mouse ha hecho click
                a = event.getX();
                b = event.getY();
                System.out.println("Clicker => x: " + a +  " y: " + b);

                //Ahora definiremos un rango de valores en x y Y que nos servira para determinar donde el usuario quiere poner su X o O

                if (a>12 && a<99) {
                    selX=0; //Esto indica que si el usuario hizo click en una posición de la pantalla entre 12 y 99, registrara el click y entonces el valor de selX sera 0 indicando que esta "llena" esa posición

                } else if(a>103 && a<195){
                    selX=1;
                }else if (a>200 && a<287){
                    selX=2;
                }else{selX=-1;}


                if (b>12 && b<99) {
                    selY=0;
                } else if(b>103 && b<195) {
                    selY=1;
                }else if (b>200 && b<287){

                    selY=2;
                }else {selY=-1;}

                //Ahora nos aseguraremos que ese sea un espacio donde se pueda jugar

                if(selX != - 1 && selY!= -1){

                    //Es valido jugar

                    if (tablero[selX][selY] == 0) {
                        if (jugadorX) {
                            tablero[selX][selY]=1;
                            jugadorX=false;
                        }else{
                            tablero[selX][selY]=2;
                            jugadorX=true;
                        }

                       
                        System.out.println("Click => x: " + a + ", y: " + b + " board : (" + x  +", " + y + ")");
                        verificarGanador();
                        
                    }

                }else{
                    System.out.println("Click inválido");
                }
            }
        }

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {}

        
        
    }

    public void actionPerformed(ActionEvent e) {
        resetGame();
    }

   




}
