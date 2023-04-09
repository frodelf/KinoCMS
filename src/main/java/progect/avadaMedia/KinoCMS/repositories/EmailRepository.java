package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.Email;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findAll();
    Optional<Email> findById(Long id);
}
