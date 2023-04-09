package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.Cinemas;
import progect.avadaMedia.KinoCMS.models.Hall;

import java.util.List;
import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findById(Long id);

    @Override
    void deleteById(Long aLong);
}
