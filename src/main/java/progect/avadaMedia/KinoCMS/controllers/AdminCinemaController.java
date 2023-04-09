package progect.avadaMedia.KinoCMS.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import progect.avadaMedia.KinoCMS.models.Cinemas;
import org.springframework.ui.Model;
import progect.avadaMedia.KinoCMS.models.Hall;
import progect.avadaMedia.KinoCMS.models.SEOBlock;
import progect.avadaMedia.KinoCMS.repositories.CinemaRepository;
import progect.avadaMedia.KinoCMS.repositories.HallRepository;
import progect.avadaMedia.KinoCMS.services.CinemaService;
import progect.avadaMedia.KinoCMS.services.HallService;
import progect.avadaMedia.KinoCMS.services.SEOBlockService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AdminCinemaController {
    private final CinemaService cinemaService;
    private final SEOBlockService seoBlockService;

    private final CinemaRepository cinemaRepository;
    private final HallService hallService;

    @PostMapping("/admin/cinemas/add-cinema/")
    public String addCinemaStart(){
        return "admin/add-cinema";
    }

    @PostMapping("/admin/cinemas/add/")
    public String addCinemaEnd(@RequestParam("name")String name, @RequestParam("description")String description,
                               @RequestParam("image")MultipartFile image, @RequestParam("image1")MultipartFile image1,
                               @RequestParam("image2")MultipartFile image2, @RequestParam("image3")MultipartFile image3,
                               @RequestParam("image4")MultipartFile image4,
                               @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                               @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {
        Cinemas cinemas = new Cinemas();
        cinemas.setName(name);
        cinemas.setDescription(description);
        cinemas.setMainImage(image.getBytes());
        cinemas.setImage1(image1.getBytes());
        cinemas.setImage2(image2.getBytes());
        cinemas.setImage3(image3.getBytes());
        cinemas.setImage4(image4.getBytes());

        SEOBlock block = new SEOBlock();
        block.setTitle(title);
        block.setDepiction(depiction);
        block.setUrl(url);
        block.setKeywords(keywords);
        seoBlockService.save(block);
        cinemas.setSeoBlock(block);
        cinemaService.save(cinemas);

        return "redirect:/admin/cinemas/";
    }

    @PostMapping("/admin/cinemas/delete/{id}")
    public String deleteById(@PathVariable("id")Long id){
        cinemaService.deleteById(id);
        return "redirect:/admin/cinemas/";
    }

    @PostMapping("/admin/cinemas/edit/{id}")
    public String editStart(@PathVariable("id")Long id, Model model){
        model.addAttribute("cinema", cinemaService.getCinemaById(id));
        model.addAttribute("halls", cinemaService.getCinemaById(id).getHall());
        return "admin/edit-cinema";
    }

    @PostMapping("/admin/cinemas/edit/end/{id}")
    public String editEnd(@PathVariable("id")Long id,@RequestParam("name")String name, @RequestParam("description")String description,
                       @RequestParam("image")MultipartFile image, @RequestParam("image1")MultipartFile image1,
                       @RequestParam("image2")MultipartFile image2, @RequestParam("image3")MultipartFile image3,
                       @RequestParam("image4")MultipartFile image4,
                          @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                          @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {
        Cinemas cinemas = cinemaService.getCinemaById(id);
        cinemas.setName(name);
        cinemas.setDescription(description);
        if(!image.isEmpty())cinemas.setMainImage(image.getBytes());
        if(!image1.isEmpty())cinemas.setImage1(image1.getBytes());
        if(!image2.isEmpty())cinemas.setImage2(image2.getBytes());
        if(!image3.isEmpty())cinemas.setImage3(image3.getBytes());
        if(!image4.isEmpty())cinemas.setImage4(image4.getBytes());

        SEOBlock block = cinemas.getSeoBlock();
        if(!title.equals(""))block.setTitle(title);
        if(!depiction.equals(""))block.setDepiction(depiction);
        if(!url.equals(""))block.setUrl(url);
        if(!keywords.equals(""))block.setKeywords(keywords);
        seoBlockService.save(block);

        cinemaRepository.save(cinemas);
        return "redirect:/admin/cinemas/";
    }

    @GetMapping("/admin/cinemas/{id}/hall/add")
    public String addStart(Model model, @PathVariable("id")Long id) {
        model.addAttribute("cinema", cinemaService.getCinemaById(id));
        return "admin/add-hall";
    }

    @PostMapping("/admin/cinemas/{id}/hall/add")
    public String addend(@PathVariable("id")Long id, @RequestParam("name")String name, @RequestParam("description")String description,
                         @RequestParam("image")MultipartFile image, @RequestParam("image1")MultipartFile image1,
                         @RequestParam("image2")MultipartFile image2, @RequestParam("image3")MultipartFile image3,
                         @RequestParam("image4")MultipartFile image4,
                         @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                         @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setDescription(description);
        hall.setMainImage(image.getBytes());
        hall.setImage1(image1.getBytes());
        hall.setImage2(image2.getBytes());
        hall.setImage3(image3.getBytes());
        hall.setImage4(image4.getBytes());
        hall.setDate(Date.valueOf(LocalDate.now()));

        SEOBlock block = new SEOBlock();
        block.setTitle(title);
        block.setDepiction(depiction);
        block.setUrl(url);
        block.setKeywords(keywords);
        seoBlockService.save(block);
        hall.setSeoBlock(seoBlockService.getById(block.getId()));
        hallService.save(hall);

        Cinemas cinemas = cinemaService.getCinemaById(id);
        cinemas.getHall().add(hallService.getById(hall.getId()));
        cinemaService.save(cinemas);
        return "redirect:/admin/cinemas/";
    }

    @GetMapping("/admin/cinemas/{cinemaId}/hall/{hallId}/edit")
    public String editHallByIdStart(Model model, @PathVariable("cinemaId")Long cinemaId, @PathVariable("hallId")Long hallId){
        model.addAttribute("cinema", cinemaService.getCinemaById(cinemaId));
        model.addAttribute("hall", hallService.getById(hallId));
        return "admin/edit-hall";
    }

    @PostMapping("/admin/cinemas/{cinemaId}/hall/{hallId}/edit")
    public String editHallByIdStartEnd(@PathVariable("hallId")Long id, @RequestParam("name")String name, @RequestParam("description")String description,
                                    @RequestParam("image")MultipartFile image, @RequestParam("image1")MultipartFile image1,
                                    @RequestParam("image2")MultipartFile image2, @RequestParam("image3")MultipartFile image3,
                                    @RequestParam("image4")MultipartFile image4,
                                       @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                                       @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {
        Hall hall = hallService.getById(id);
        hall.setName(name);
        hall.setDescription(description);
        if(!image.isEmpty())hall.setMainImage(image.getBytes());
        if(!image1.isEmpty())hall.setImage1(image1.getBytes());
        if(!image2.isEmpty())hall.setImage2(image2.getBytes());
        if(!image3.isEmpty())hall.setImage3(image3.getBytes());
        if(!image4.isEmpty())hall.setImage4(image4.getBytes());

        SEOBlock block = hall.getSeoBlock();
        if(!title.equals(""))block.setTitle(title);
        if(!depiction.equals(""))block.setDepiction(depiction);
        if(!url.equals(""))block.setUrl(url);
        if(!keywords.equals(""))block.setKeywords(keywords);
        seoBlockService.save(block);

        hallService.save(hall);
        return "redirect:/admin/cinemas/";
    }
    @GetMapping("/admin/cinemas/{id}/hall/{idHall}/delete")
    public String deleteHall(@PathVariable("id")Long id, @PathVariable("idHall")Long idHall){
        cinemaService.getCinemaById(id).getHall().remove(hallService.getById(idHall));
        hallService.delete(hallService.getById(idHall));
        return "redirect:/admin/cinemas/";
    }
}
