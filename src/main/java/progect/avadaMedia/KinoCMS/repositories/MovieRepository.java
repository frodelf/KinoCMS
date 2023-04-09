package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import progect.avadaMedia.KinoCMS.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByTodayTrue();
    List<Movie> findBySoonTrue();
    List<Movie> findAll();
    List<Movie> findBySoonTrueOrTodayTrue();

    @Query("SELECT m FROM Movie m WHERE m.name = ?1")
    Movie findByName(String name);

    Optional<Movie> findById(Long id);

    void deleteById(Long id);
}
