package com.example.restservice;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.codeguruprofilerjavaagent.Profiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;








@SpringBootApplication
@EnableScheduling
public class RestServiceApplication {

    public static void main(String[] args) {
    	
    	
       Profiler.builder()
        .profilingGroupName("testForTFC")
        .awsCredentialsProvider(ProfileCredentialsProvider.create("acct2"))
        .withHeapSummary(true)
        .build()
        .start();
        
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
