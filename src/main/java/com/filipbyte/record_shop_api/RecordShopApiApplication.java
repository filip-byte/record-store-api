package com.filipbyte.record_shop_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RecordShopApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(RecordShopApiApplication.class, args);


	}


}
