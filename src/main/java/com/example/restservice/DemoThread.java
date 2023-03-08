package com.example.restservice;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class DemoThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(DemoTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Override
    public void run() {
        log.info("The time is now {} - Thread {}", dateFormat.format(new Date()), Thread.currentThread().getName());
        process();
    }
    
    public void process() {


        try{
            //reference resource file
            Resource resource = new ClassPathResource("t.jpg");
            InputStream is = resource.getInputStream();

            //create a temp file
            File tempFile = File.createTempFile("temp", ".jpg");

            DemoUtil.convertImagetoGrayScale(is, tempFile);
            log.info("outputPath: "+tempFile.getAbsolutePath());
            DemoUtil.calculateSHA512(tempFile);
            tempFile.delete();

            DemoUtil.calculate(100000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
