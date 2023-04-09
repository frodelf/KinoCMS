package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.SEOBlock;
import progect.avadaMedia.KinoCMS.repositories.SEOBlockRepository;
@Service
@RequiredArgsConstructor
public class SEOBlockService {
    private final SEOBlockRepository seoBlockRepository;
    public void save(SEOBlock block){seoBlockRepository.save(block);}
    public SEOBlock getById(long id){
        return seoBlockRepository.findById(id).get();
    }
}
