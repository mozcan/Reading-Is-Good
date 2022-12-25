package com.mozcan.readingIsGood;

import com.mozcan.readingIsGood.config.SpringFoxConfig;
import com.mozcan.readingIsGood.config.SpringSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SpringSecurityConfig.class, SpringFoxConfig.class})
public class ReadingIsGoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingIsGoodApplication.class, args);
	}

}
