package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Cinemas;
import progect.avadaMedia.KinoCMS.models.Hall;
import progect.avadaMedia.KinoCMS.repositories.HallRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;

    public Hall getById(Long id){
        return hallRepository.findById(id).get();
    }


    public void save(Hall hall){
        hallRepository.save(hall);
    }
    public void delete(Hall hall){
        hallRepository.deleteById(hall.getId());
    }
}
