package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.News;
import progect.avadaMedia.KinoCMS.repositories.NewsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    public void save(News news){
        newsRepository.save(news);
    }
    public List<News> getAllNews(){return newsRepository.findAllByIsAdsIsFalse();}
    public List<News> getAll(){return newsRepository.findAllByIsAdsIsFalse();}
    public List<News> getAllAds(){return newsRepository.findAllByIsAdsIsTrue();}
    public void removeById(long id){newsRepository.deleteById(id);}
    public News getById(Long id){return newsRepository.findById(id).get();}


}
