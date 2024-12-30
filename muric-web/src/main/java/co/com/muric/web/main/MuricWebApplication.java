package co.com.muric.web.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@SpringBootApplication
@ComponentScan("co.com.muric")
public class MuricWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MuricWebApplication.class, args);
	}
}
