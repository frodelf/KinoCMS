package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Movie;
import progect.avadaMedia.KinoCMS.repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService{
    private final MovieRepository movieRepository;

    public void save(Movie movie){
        movieRepository.save(movie);
    }

    public List<Movie> getToday(){
        return movieRepository.findByTodayTrue();
    }


    public List<Movie> getSoon(){
        return movieRepository.findBySoonTrue();
    }
    public List<Movie> getAll(){
        return movieRepository.findAll();
    }
    public List<Movie> getTodayAndSoon(){
        return movieRepository.findBySoonTrueOrTodayTrue();
    }
    public Optional<Movie> getMovieById(Long id){return movieRepository.findById(id);}
    public Movie getByName(String name){return movieRepository.findByName(name);}
    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }

}
