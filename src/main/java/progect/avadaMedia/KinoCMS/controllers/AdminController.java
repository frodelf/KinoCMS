package progect.avadaMedia.KinoCMS.controllers;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import progect.avadaMedia.KinoCMS.services.*;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final PagesService pagesService;
    private final ScheduleService scheduleService;

    private final UsersService usersService;
    private final MovieService movieService;
    private final StatsService statsService;
    private final CinemaService cinemaService;
    private final NewsService newsService;

    @GetMapping("/admin/")
    public String admin(Model model){
        model.addAttribute("woman", usersService.numberWomen());
        model.addAttribute("man", usersService.numberMan());
        model.addAttribute("allUser", usersService.getAll().size());
        model.addAttribute("allMovie", movieService.getAll().size());
        model.addAttribute("allCinema", cinemaService.getAll().size());
        model.addAttribute("allNews", newsService.getAll().size());
        model.addAttribute("months", new Gson().toJson(statsService.getAllMonth()));
        model.addAttribute("value", statsService.getAllValue());
        return "admin/index";
    }


    @GetMapping("/admin/banners/")
    public String bannersAdmin(Model model){

        return "admin/banners";
    }

    @GetMapping("/admin/afisha/")
    public String afishaAdmin(Model model){
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("soon", movieService.getSoon());
        model.addAttribute("today", movieService.getToday());
        return "admin/afisha";
    }

    @GetMapping("/admin/movie/{id}")
    public String pageForMovie(Model model){
        return "admin/index";
    }

    @GetMapping("/admin/cinemas/")
    public String cinemasAdmin(Model model){
        model.addAttribute("cinemas", cinemaService.getAll());
        return "admin/cinemas";
    }

    @GetMapping("/admin/news/")
    public String soonAdmin(Model model){
        model.addAttribute("news", newsService.getAllNews());
        return "admin/news";
    }
    @GetMapping("/admin/error/")
    public String error(Model model){
        return "admin/error";
    }
}
