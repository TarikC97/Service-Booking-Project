package com.tarikc.ServiceBookingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//exclude nedeed to avoide error of config
//(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class ServiceBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBookingSystemApplication.class, args);
	}

}
