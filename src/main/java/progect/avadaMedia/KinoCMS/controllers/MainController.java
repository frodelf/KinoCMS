package progect.avadaMedia.KinoCMS.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import progect.avadaMedia.KinoCMS.services.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ScheduleService scheduleService;
    private final ContactsService contactsService;
    private final HallService hallService;
    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final NewsService newsService;
    private final PagesService pagesService;

    @ModelAttribute("header")
    public String getHeader(Model model) {
        model.addAttribute("main_header", pagesService.getByID(1));
        model.addAttribute("pages_header", pagesService.getAll());
        model.addAttribute("contacts_header", contactsService.getAll());
        return "blocks/header";
    }

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("movieToday", movieService.getToday());
        model.addAttribute("soon", movieService.getSoon());
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("mainUp", movieService.getTodayAndSoon());
        model.addAttribute("mainSale", newsService.getAllNews());
        model.addAttribute("main", pagesService.getByID(1));
        model.addAttribute("pages", pagesService.getAll());
        return "user/index";
    }

    @GetMapping("/afisha/")
    public String afisha(Model model){
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("contacts", contactsService.getAll());
        return "user/afisha";
    }

    @GetMapping("/afisha/{id}")
    public String moviePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("movie", movieService.getMovieById(id));
        return "user/page-movie";
    }

    @GetMapping("/soon/")
    public String soon(Model model){
        model.addAttribute("movies", movieService.getSoon());
        model.addAttribute("ads", newsService.getAllAds());
        return "user/soon";
    }

    @GetMapping("/schedule/")
    public String schedule(Model model){
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("scheduleToday", scheduleService.getScheduleByDay("today"));
        model.addAttribute("scheduleTomorrow", scheduleService.getScheduleByDay("tomorrow"));

        return "user/schedule";
    }

    @GetMapping("/cinemas/")
    public String cinemas(Model model){
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("cinemas", cinemaService.getAll());
        return "user/cinemas";
    }

    @GetMapping("/cinemas/{id}")
    public String cinemasPage(Model model, @PathVariable("id")Long id){
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("cinemas", cinemaService.getCinemaById(id));
        return "user/page-cinema";
    }

    @GetMapping("/cinemas/{cinemaId}/hall/{hallId}")
    public String hall(Model model, @PathVariable("hallId")Long id){
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("hall", hallService.getById(id));
        return "user/page-hall";
    }

    @GetMapping("/news/")
    public String newsForSales(Model model){
        model.addAttribute("news", newsService.getAllNews());
        model.addAttribute("ads", newsService.getAllAds());
        return "user/news";
    }

    @GetMapping("/news/{id}")
    public String newsPage(@PathVariable("id")Long id, Model model){
        model.addAttribute("news", newsService.getById(id));
        model.addAttribute("ads", newsService.getAllAds());
        return "user/page-news";
    }

    @GetMapping("/pages/{id}/")
    public String getPages(@PathVariable("id")Long id, Model model){
        model.addAttribute("pages", pagesService.getByID(id));
        model.addAttribute("ads", newsService.getAllAds());
        return "user/pages";
    }

    @GetMapping("/pages/contacts/")
    public String getContacts(Model model){
        model.addAttribute("ads", newsService.getAllAds());
        model.addAttribute("contacts", contactsService.getAll());
        return "user/contacts";
    }
}
