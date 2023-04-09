package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.Stats;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {
    List<Stats> findAll();
}
