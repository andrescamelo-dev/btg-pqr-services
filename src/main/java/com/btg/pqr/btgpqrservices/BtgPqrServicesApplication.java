package com.btg.pqr.btgpqrservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BtgPqrServicesApplication {
	public static final String PATH_SERVICE = "/pqr/v1.0.0";
	public static void main(String[] args) {
		SpringApplication.run(BtgPqrServicesApplication.class, args);
	}

}
