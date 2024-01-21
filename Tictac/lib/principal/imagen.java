package Tictac.lib.principal;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class imagen {
     public static void main(String[] args) {
        JFrame jFrame = new JFrame("Image");
        jFrame.setLayout(new FlowLayout());
        jFrame.setPreferredSize(new Dimension(600, 1000));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        jFrame.add(new JLabel("Original Size"));
        jFrame.add(loadImage("Equis.png",
                false, 0, 0));

        jFrame.add(new JLabel("Resized"));
        jFrame.add(loadImage("image.png",
                true, 200, 200));
        jFrame.setVisible(true);

    }

    public static JLabel loadImage(String fileName,
                                   boolean isResized,
                                   int targetWidth,
                                   int targetHeight){
        BufferedImage image;
        JLabel imageContainer;

        try{
            image = ImageIO.read(new File("Equis.png"));

            if(isResized){
                image = resizeImage(image, targetWidth, targetHeight);
            }

            imageContainer = new JLabel(new ImageIcon(image));
            return imageContainer;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static BufferedImage resizeImage(BufferedImage image,
                                            int targetWidth,
                                            int targetHeight){
        BufferedImage resizedImg = new BufferedImage(targetWidth,
                targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImg.createGraphics();
        graphics2D.drawImage(image, 0, 0, targetWidth,
                targetHeight, null);
        graphics2D.dispose();
        return resizedImg;
    }

}
