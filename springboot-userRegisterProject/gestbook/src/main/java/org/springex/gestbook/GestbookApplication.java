package org.springex.gestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestbookApplication.class, args);
	}

}
