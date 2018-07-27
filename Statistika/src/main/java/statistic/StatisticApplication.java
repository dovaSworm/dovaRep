package statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"statistic", "statistic.web.controller"})
public class StatisticApplication {


	public static void main(String[] args) {
		 SpringApplication.run(StatisticApplication.class, args);
	}
}
