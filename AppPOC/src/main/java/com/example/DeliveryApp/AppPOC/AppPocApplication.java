package com.example.DeliveryApp.AppPOC;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.DeliveryApp.AppPOC.Util.UtilityClass;

@SpringBootApplication
public class AppPocApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AppPocApplication.class);
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AppPocApplication.class, args);
		UtilityClass.createPDFSample();

	}
}
