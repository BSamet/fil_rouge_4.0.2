package fr.bulutsamet.FilRougeBack402;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2DComposite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class FilRougeBack402Application {

	public static void main(String[] args) {
		SpringApplication.run(FilRougeBack402Application.class, args);
	}
}
