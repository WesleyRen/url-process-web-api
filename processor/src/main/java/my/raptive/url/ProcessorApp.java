package my.raptive.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ProcessorApp {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ProcessorApp.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}
