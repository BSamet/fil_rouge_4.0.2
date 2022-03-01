package fr.bulutsamet.FilRougeBack402.Services.Forms2DService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Forms2DDao extends JpaRepository<Forms2D, Integer> {
    Forms2D findById(int id);
}
