package com.mycompany.project_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//Configuration class + component Scan class + Enable EnableAutoConfiguration class
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);//It starts an Embedded Tomcat server For us
	}

}
