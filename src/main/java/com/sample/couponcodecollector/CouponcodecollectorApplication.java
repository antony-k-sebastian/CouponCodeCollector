package com.sample.couponcodecollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CouponcodecollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponcodecollectorApplication.class, args);
	}

}
