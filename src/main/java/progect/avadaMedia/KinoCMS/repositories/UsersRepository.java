package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByNickAndPassword(String nick, String password);
    List<Users> findAll();
    void deleteById(Long id);
    Optional<Users> findById(Long id);
}
