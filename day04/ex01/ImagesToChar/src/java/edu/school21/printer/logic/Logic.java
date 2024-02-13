package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class Logic {
    public static void print_img(String path, char white, char black){
        try {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            char[][] charsFromImage = new char[image.getWidth()][image.getHeight()];

            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int color = image.getRGB(i, j);
                    if(color == Color.WHITE.getRGB()) {
                         charsFromImage[j][i] = white;
                    }
                    if(color == Color.BLACK.getRGB()) {
                        charsFromImage[j][i] = black;
                    }
                }
            }
            for (int i = 0; i < charsFromImage.length; i++)
            {
                for (int j = 0; j < charsFromImage[i].length; j++) {
                    System.out.print(charsFromImage[i][j]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Bad path");
        }
    }
}
