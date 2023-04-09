package progect.avadaMedia.KinoCMS.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import progect.avadaMedia.KinoCMS.models.Movie;
import progect.avadaMedia.KinoCMS.models.SEOBlock;
import progect.avadaMedia.KinoCMS.repositories.MovieRepository;
import progect.avadaMedia.KinoCMS.services.MovieService;
import progect.avadaMedia.KinoCMS.services.SEOBlockService;
import progect.avadaMedia.KinoCMS.services.ScheduleService;
import progect.avadaMedia.KinoCMS.services.UsersService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminAfishaController {

    private final ScheduleService scheduleService;

    private final UsersService usersService;
    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final SEOBlockService seoBlockService;



    @PostMapping("/admin/afisha/add-movie/")
    public String addMovie(Model model){
        return "admin/add-movie";
    }

    @PostMapping("/admin/afisha/today/")
    public String addMovieToday(@RequestParam("name") String name) {
        Movie movie = movieService.getByName(name);
        movie.setToday(true);
        movieService.save(movie);
        return "redirect:/admin/afisha/";
    }

    @PostMapping("/admin/afisha/soon/")
    public String addMovieSoon(@RequestParam("name") String name) {
        Movie movie = movieService.getByName(name);
        movie.setSoon(true);
        movieService.save(movie);
        return "redirect:/admin/afisha/";
    }



    @PostMapping("/admin/afisha/add/")
    public String addMovie(@RequestParam("name")String name, @RequestParam("description") String description,
                           @RequestParam("image")MultipartFile image, @RequestParam("image1")MultipartFile image1,
                           @RequestParam("image2")MultipartFile image2, @RequestParam("image3")MultipartFile image3,
                           @RequestParam("image4")MultipartFile image4, @RequestParam("link")String link,
                           @RequestParam(value = "3D",  required=false)Boolean three, @RequestParam(value = "2D", required=false)Boolean two,
                           @RequestParam(value = "IMAX", required=false)Boolean imax,
                           @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                           @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {



        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setMainImage(image.getBytes());
        movie.setImage1(image1.getBytes());
        movie.setImage2(image2.getBytes());
        movie.setImage3(image3.getBytes());
        movie.setImage4(image4.getBytes());

        movie.setTwo(two != null ? two.booleanValue() : false);
        movie.setThree(three != null ? three.booleanValue() : false);
        movie.setIMAX(imax != null ? imax.booleanValue() : false);

        SEOBlock block = new SEOBlock();
        block.setTitle(title);
        block.setDepiction(depiction);
        block.setUrl(url);
        block.setKeywords(keywords);
        seoBlockService.save(block);
        movie.setSeoBlock(block);
        movieService.save(movie);

        return "redirect:/admin/afisha/";
    }

    @PostMapping("/admin/afisha/delete/today/{id}")
    public String removeToday(Model model, @PathVariable("id") Long id){
        Optional<Movie> movie = movieService.getMovieById(id);
        movie.get().setToday(false);
        movieService.save(movie.get());
        return "redirect:/admin/afisha/";
    }

    @PostMapping("/admin/afisha/delete/soon/{id}")
    public String removeSoon(Model model, @PathVariable("id") Long id){
        Optional<Movie> movie = movieService.getMovieById(id);
        movie.get().setSoon(false);
        movieService.save(movie.get());
        return "redirect:/admin/afisha/";
    }

    @PostMapping("/admin/afisha/edit/{id}")
    public String editById(Model model, @PathVariable("id") Long id){
        Movie movie = movieService.getMovieById(id).get();
        model.addAttribute("movie", movie);
        return "admin/edit-movie";
    }

    @PostMapping("/admin/afisha/edit/end/{id}")
    public String edit(@PathVariable("id")Long id, @RequestParam("name")String name, @RequestParam("description") String description,
                       @RequestParam("image")MultipartFile image, @RequestParam("image1")MultipartFile image1,
                       @RequestParam("image2")MultipartFile image2, @RequestParam("image3")MultipartFile image3,
                       @RequestParam("image4")MultipartFile image4, @RequestParam("link")String link,
                       @RequestParam(value = "3D",  required=false)Boolean three, @RequestParam(value = "2D", required=false)Boolean two,
                       @RequestParam(value = "IMAX", required=false)Boolean imax,
                       @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                       @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException{
        Movie movie = movieService.getMovieById(id).get();
        movie.setName(name);
        movie.setDescription(description);
        if(!image.isEmpty())movie.setMainImage(image.getBytes());
        if(!image1.isEmpty())movie.setImage1(image1.getBytes());
        if(!image2.isEmpty())movie.setImage2(image2.getBytes());
        if(!image3.isEmpty())movie.setImage3(image3.getBytes());
        if(!image4.isEmpty())movie.setImage4(image4.getBytes());
        movie.setLink(link);
        movie.setTwo(two != null ? two.booleanValue() : false);
        movie.setThree(three != null ? three.booleanValue() : false);
        movie.setIMAX(imax != null ? imax.booleanValue() : false);


        SEOBlock block = movie.getSeoBlock();
        if(!url.equals(""))block.setUrl(url);
        if(!depiction.equals(""))block.setDepiction(depiction);
        if(!keywords.equals(""))block.setKeywords(keywords);
        if(!title.equals(""))block.setTitle(title);

        seoBlockService.save(block);
        movieService.save(movie);
        return "redirect:/admin/afisha/";
    }

    @PostMapping("/admin/afisha/delete/{id}")
    public String delete(Model model, @PathVariable("id")Long id){
        movieService.deleteById(id);
        return "redirect:/admin/afisha/";
    }
}
