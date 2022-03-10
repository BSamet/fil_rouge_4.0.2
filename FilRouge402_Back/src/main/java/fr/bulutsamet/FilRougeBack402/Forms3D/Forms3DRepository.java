package fr.bulutsamet.FilRougeBack402.Forms3D;

import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Forms3DRepository extends JpaRepository<Forms3D, Integer> {
    Forms3D findById(int id);
}