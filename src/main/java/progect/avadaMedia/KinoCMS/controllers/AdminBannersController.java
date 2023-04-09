package progect.avadaMedia.KinoCMS.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import progect.avadaMedia.KinoCMS.models.News;
import progect.avadaMedia.KinoCMS.services.NewsService;
import progect.avadaMedia.KinoCMS.services.ScheduleService;
import progect.avadaMedia.KinoCMS.services.UsersService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminBannersController {

    private final ScheduleService scheduleService;

    private final UsersService usersService;
    private final NewsService newsService;


    @PostMapping("/admin/banners/add/")
    public String add(@RequestParam("image") MultipartFile image) throws IOException {
        News news = new News();
        news.setImage(image.getBytes());
        news.setAds(true);
        newsService.save(news);
        return "redirect:/admin/banners/";
    }
}
