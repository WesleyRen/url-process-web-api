package my.raptive.urlProcessService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UrlProcessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlProcessServiceApplication.class, args);
	}

}
