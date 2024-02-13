package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

@Parameters(separators = "=")
public class Logic {

    @Parameter(names = "--white")
    private String white;
    @Parameter(names = "--black")
    private String black;

    public void run(){
        try {
            BufferedImage image = ImageIO.read(new File("./src/resources/it.bmp"));
            char[][] charsFromImage = new char[image.getWidth()][image.getHeight()];

            ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false).build();

            for (int i = 0; i < charsFromImage.length; i++) {
                for (int j = 0; j < charsFromImage[i].length; j++) {
                    int color = image.getRGB(j, i);
                    if (color == Color.WHITE.getRGB()) {
                        coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(white));
                        coloredPrinter.print(" ");
                    }
                    if(color == Color.BLACK.getRGB()) {
                        coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(black));
                        coloredPrinter.print(" ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Bad path");
        }
    }
}
