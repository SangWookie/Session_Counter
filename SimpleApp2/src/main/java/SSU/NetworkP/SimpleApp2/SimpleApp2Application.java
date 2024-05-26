package SSU.NetworkP.SimpleApp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SimpleApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApp2Application.class, args);
	}

}
