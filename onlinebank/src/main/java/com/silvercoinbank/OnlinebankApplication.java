package com.silvercoinbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class OnlinebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankApplication.class, args);
		
		//get Hibernate version
		System.out.println("Hibernate version:"+org.hibernate.Version.getVersionString()); 
		//get Spring version
		System.out.println("Spring version:"+SpringVersion.getVersion());
	}

}
