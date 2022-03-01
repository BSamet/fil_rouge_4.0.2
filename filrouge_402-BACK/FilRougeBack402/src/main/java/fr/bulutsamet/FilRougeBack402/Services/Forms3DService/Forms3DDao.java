package fr.bulutsamet.FilRougeBack402.Services.Forms3DService;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Forms3DDao extends JpaRepository<Forms3D, Integer> {
    Forms3D findById(int id);
}
