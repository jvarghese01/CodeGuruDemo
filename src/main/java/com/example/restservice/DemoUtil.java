package com.example.restservice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class DemoUtil {
    
    public static File convertImagetoGrayScale(InputStream input, File output){


        



        //convert an image to grayscale
        BufferedImage bi;
        try {


            //read image from InputStream
            //ImageIO.read(input);


            bi = ImageIO.read(input);
        
            int width = bi.getWidth();
            int height = bi.getHeight();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = bi.getRGB(i, j);
                    int r = (rgb >> 16) & 0xff;
                    int g = (rgb >> 8) & 0xff;
                    int b = (rgb) & 0xff;
                    int gray = (r + g + b) / 3;
                    rgb = (gray << 16) | (gray << 8) | gray;
                    bi.setRGB(i, j, rgb);
                }
            }
            ImageIO.write(bi, "jpg", output);
            System.out.println("Image converted successfully");
            return output;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        

    }

}
