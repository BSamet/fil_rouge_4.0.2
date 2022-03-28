package fr.bulutsamet.FilRougeBack402;

import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DRepository;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Circle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Rectangle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Triangle;
import fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DRepository;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3DComposite;
import fr.bulutsamet.FilRougeBack402.SceneConcept.Scene3dConceptRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class FilRougeBack402Application {

	public static void main(String[] args) {
		SpringApplication.run(FilRougeBack402Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(Forms2DRepository forms2DRepository, Forms3DRepository forms3DRepository, Scene3dConceptRepository scene3dConceptRepository) {
		return (args) -> {
			// Initialiser les formes

				// Créer une scene
			Forms3DComposite myScene = new Forms3DComposite();
			myScene.setName("My First Scene");
				// Formes 2D
			Rectangle rectangle = new Rectangle(10, 15, "Rectangle 1");
			Triangle triangle = new Triangle(20, 40, "Triangle 1");
			Circle circle = new Circle(18, "Circle 1");
				// Formes 3D
			Forms3D rectangle3d = new Forms3D(rectangle, 10, 0);
			Forms3D triangle3d = new Forms3D(triangle, 30, 1);
			Forms3D circle3d = new Forms3D(circle, 20, 1);

			// Ajouter les formes
			forms3DRepository.save(rectangle3d);
			forms3DRepository.save(triangle3d);
			forms3DRepository.save(circle3d);
			scene3dConceptRepository.save(myScene);

			myScene.add3dForm(triangle3d);
			myScene.add3dForm(circle3d);
			scene3dConceptRepository.save(myScene);
		};
	}
}