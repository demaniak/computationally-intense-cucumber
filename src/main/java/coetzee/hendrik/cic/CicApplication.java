package coetzee.hendrik.cic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CicApplication.class, args);
	}
}
