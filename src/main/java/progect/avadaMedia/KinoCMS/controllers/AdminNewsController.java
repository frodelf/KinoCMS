package progect.avadaMedia.KinoCMS.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import progect.avadaMedia.KinoCMS.models.News;
import progect.avadaMedia.KinoCMS.models.SEOBlock;
import progect.avadaMedia.KinoCMS.services.CinemaService;
import progect.avadaMedia.KinoCMS.services.MovieService;
import progect.avadaMedia.KinoCMS.services.NewsService;
import progect.avadaMedia.KinoCMS.services.SEOBlockService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AdminNewsController {
    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final NewsService newsService;
    private final SEOBlockService seoBlockService;

    @GetMapping("/admin/news/add/")
    public String addStart(Model model){
        return "admin/add-news";
    }

    @PostMapping("/admin/news/add/")
    public String addEnd(Model model, @RequestParam("name")String name, @RequestParam("description") String description,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                         @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {
        News news = new News();
        news.setName(name);
        news.setDescription(description);
        news.setImage(image.getBytes());
        news.setDate(Date.valueOf(LocalDate.now()));
        news.setAds(false);

        SEOBlock block = new SEOBlock();
        block.setTitle(title);
        block.setDepiction(depiction);
        block.setUrl(url);
        block.setKeywords(keywords);
        seoBlockService.save(block);
        news.setSeoBlock(block);
        newsService.save(news);
        return "redirect:/admin/news/";
    }

    @GetMapping("/news/{id}/delete/")
    public String delete(@PathVariable("id")Long id){
        newsService.removeById(id);
        return "redirect:/admin/news/";
    }

    @GetMapping("/news/{id}/edit/")
    public String editStart(@PathVariable("id")Long id, Model model){
        model.addAttribute("news", newsService.getById(id));
        return "admin/edit-news";
    }

    @PostMapping("/admin/news/edit/end/{id}")
    public String editEnd(@PathVariable("id")Long id, Model model, @RequestParam("name")String name,
                          @RequestParam("description")String description, @RequestParam("image")MultipartFile image,
                          @RequestParam("url")String url, @RequestParam("depiction")String depiction,
                          @RequestParam("title")String title, @RequestParam("keywords")String keywords) throws IOException {
        News news = newsService.getById(id);
        news.setName(name);
        news.setDescription(description);
        if(!image.isEmpty())news.setImage(image.getBytes());

        SEOBlock block = news.getSeoBlock();
        if(!title.equals(""))block.setTitle(title);
        if(!depiction.equals(""))block.setDepiction(depiction);
        if(!url.equals(""))block.setUrl(url);
        if(!keywords.equals(""))block.setKeywords(keywords);
        seoBlockService.save(block);

        newsService.save(news);
        return "redirect:/admin/news/";
    }
}
