package com.example.restservice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class DemoUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoTask.class);

    public static File convertImagetoGrayScale(InputStream input, File output){
        //convert an image to grayscale
        BufferedImage bi;
        try {
            //read image from InputStream
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
            logger.info("Image converted successfully");
            return output;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void calculate(int x){
        double y = (double)x;
        for (int a=0;a<x;a++){
            double z = Math.tan(y)*Math.tan(y+1);
        }
        logger.info("Calculation completed");
    }

    //generate a QR code for a given URL
    public static File generateQRCode(String url, File output){
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", output.toPath());
            logger.info("QR code generated successfully");
            return output;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void makeRestCall(String url){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        
        restTemplate.getForObject(url, String.class);
        logger.info("Rest call completed");
        
    }

    //calculate SHA-512 for a given file
    public static String calculateSHA512(File input){
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                //get bytest from input
                byte[] inputB = Files.readAllBytes(Paths.get(input.getPath()));

                md.update(inputB);

                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                logger.info(sb.toString());
                logger.info("SHA-512 calculated successfully");
                return sb.toString();
            }
             catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
    }
    
    String username = "abcdef";
    String password = "1234567";

    private static String AWS_ACCESS_KEY = "AKIAJLGJBXH4XXXXXXXX";
    private static String AWS_SECRET_ACCESS_KEY = "bwbEsUrEkbZfcHKO3MzSyZ+vFiYaTflqXXXXXXXX"; 
    

}




    




