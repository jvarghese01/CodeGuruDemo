package com.example.restservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoTask {

    private static final Logger log = LoggerFactory.getLogger(DemoTask.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Value("classpath:t.jpg")
    Resource resourceFile;
    
	@Scheduled(fixedRate = 5000)
	public void scheduledTasks() throws IOException {

        //run threads using ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executor.execute(new DemoThread());
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        log.info("Finished all threads");



		/*log.info("The time is now {}", dateFormat.format(new Date()));
        
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
        */
	}
}
