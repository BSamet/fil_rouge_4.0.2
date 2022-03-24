package fr.bulutsamet.FilRougeBack402.Forms2D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Forms2DRepository extends JpaRepository<Forms2D, Integer>  {
}
