package my.raptive.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RestApp {

	public static void main(String[] args) {
		SpringApplication.run(RestApp.class, args);
	}

}
