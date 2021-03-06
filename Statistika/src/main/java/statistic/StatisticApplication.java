package statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"statistic", "statistic.web.controller"})
//@Import({SecurityConfig.class})
public class StatisticApplication {


	public static void main(String[] args) {
		 SpringApplication.run(StatisticApplication.class, args);
	}
}
