package com.fis.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SubscriptionServiceApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

}
