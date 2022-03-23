package fr.bulutsamet.FilRougeBack402.SceneConcept;

import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3DComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Scene3dConceptRepository extends JpaRepository<Forms3DComposite, Integer> {
    Forms3DComposite findById(int id);
}
