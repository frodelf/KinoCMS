package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import progect.avadaMedia.KinoCMS.models.Pages;

import java.util.List;
import java.util.Optional;

public interface PagesRepository extends JpaRepository<Pages, Long> {
    @Query("SELECT m FROM Pages m WHERE m.name = ?1")
    Pages findByName(String name);
    Optional<Pages> findById(Long id);
    List<Pages> findAll();
}
