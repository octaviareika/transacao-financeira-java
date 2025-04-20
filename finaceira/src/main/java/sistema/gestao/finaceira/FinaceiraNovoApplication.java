package sistema.gestao.finaceira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties
@SpringBootApplication
public class FinaceiraNovoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinaceiraNovoApplication.class, args);
	}

}
