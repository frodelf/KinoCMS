package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Cinemas;
import progect.avadaMedia.KinoCMS.repositories.CinemaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;

    public Cinemas getCinemaById(Long id){
        return cinemaRepository.findById(id).get();
    }

    public Cinemas getCinemaByName(String name){
        return cinemaRepository.findByName(name);
    }

    public void save(Cinemas cinemas){
        cinemaRepository.save(cinemas);
    }

    public List<Cinemas> getAll(){
        return cinemaRepository.findAll();
    }

    public void deleteById(Long id){
        cinemaRepository.deleteById(id);
    }
}
