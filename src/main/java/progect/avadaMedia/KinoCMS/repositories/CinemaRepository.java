package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import progect.avadaMedia.KinoCMS.models.Cinemas;
import progect.avadaMedia.KinoCMS.models.Movie;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository extends JpaRepository<Cinemas, Long> {
    Optional<Cinemas> findById(Long id);

    List<Cinemas> findAll();

    @Override
    void deleteById(Long Long);

    @Query("SELECT m FROM Cinemas m WHERE m.name = ?1")
    Cinemas findByName(String name);
}
