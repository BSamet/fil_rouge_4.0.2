package fr.bulutsamet.FilRougeBack402;

import fr.bulutsamet.FilRougeBack402.Model.Forms2D.Circle;
import fr.bulutsamet.FilRougeBack402.Model.Forms2D.FormsComposite;
import fr.bulutsamet.FilRougeBack402.Model.Forms2D.Rectangle;
import fr.bulutsamet.FilRougeBack402.Model.Forms2D.Triangle;
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

		// Créer une instance d'un rectangle
		Rectangle r1 = new Rectangle(6,12, "Rectangle 1");
		System.out.println(r1);
		Rectangle r2 = new Rectangle(8,79, "Rectangle 2");
		System.out.println(r2);
		//

		// Créer une instance d'un cercle
		Circle c1 = new Circle(90, "Cercle 1");
		System.out.println(c1);
		//

		// Créer une instance d'un triangle
		Triangle t1 = new Triangle(10,7, "Triangle 1");
		System.out.println(t1);
		//

		FormsComposite manageForms = new FormsComposite();
		for (int i = 0; i < 201; i++){
			Rectangle rectangleFlorent = new Rectangle(5, 5, "Rectangle FLorent" + i);
			manageForms.addForm(rectangleFlorent);
		}
		System.out.println(manageForms);
	}

}
