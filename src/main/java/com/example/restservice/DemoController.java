package com.example.restservice;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Demo greeting(@RequestParam(value = "name", defaultValue = "Demo") String name) throws IOException {

		
		File file = new File("/Users/javarghe/Downloads/t.jpg");
		DemoUtil.convertImagetoGrayScale(file, new File("/Users/javarghe/Downloads/t1.jpg"));
		return new Demo(counter.incrementAndGet(), String.format(template, name));
	}
}
