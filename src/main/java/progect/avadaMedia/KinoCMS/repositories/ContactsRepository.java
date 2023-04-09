package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.Contacts;

import java.util.List;
import java.util.Optional;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findAll();
    Optional<Contacts> findById(Long id);
    void deleteById(Long id);
}
