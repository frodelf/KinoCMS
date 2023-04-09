package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Pages;
import progect.avadaMedia.KinoCMS.repositories.PagesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagesService {
    private final PagesRepository pagesRepository;

    public Pages getByID(long id){
        return pagesRepository.findById(id).get();
    }

    public Pages getByName(String name){
        return pagesRepository.findByName(name);
    }

    public void save(Pages pages){
        pagesRepository.save(pages);
    }

    public List<Pages> getAll(){
        return pagesRepository.findAll();
    }

    public void deleteById(Long id){
        pagesRepository.deleteById(id);
    }
}
