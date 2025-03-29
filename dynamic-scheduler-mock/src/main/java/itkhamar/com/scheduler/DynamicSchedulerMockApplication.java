package itkhamar.com.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DynamicSchedulerMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicSchedulerMockApplication.class, args);
	}

}
