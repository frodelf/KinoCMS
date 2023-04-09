package progect.avadaMedia.KinoCMS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progect.avadaMedia.KinoCMS.models.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByIsAdsIsFalse();
    List<News> findAllByIsAdsIsTrue();

    @Override
    void deleteById(Long aLong);
    Optional<News> findById(Long id);

    List<News> findAll();
}
