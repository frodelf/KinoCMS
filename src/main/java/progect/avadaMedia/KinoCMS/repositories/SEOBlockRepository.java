package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.SEOBlock;

import java.util.Optional;

public interface SEOBlockRepository extends JpaRepository<SEOBlock, Long> {
    @Override
    Optional<SEOBlock> findById(Long aLong);
}
