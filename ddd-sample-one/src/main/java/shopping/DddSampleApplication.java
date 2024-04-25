package shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "shopping")
@EnableScheduling
public class DddSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddSampleApplication.class, args);
	}

}
