package progect.avadaMedia.KinoCMS.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import progect.avadaMedia.KinoCMS.models.Contacts;
import progect.avadaMedia.KinoCMS.models.Pages;
import progect.avadaMedia.KinoCMS.models.SEOBlock;
import progect.avadaMedia.KinoCMS.services.ContactsService;
import progect.avadaMedia.KinoCMS.services.PagesService;
import progect.avadaMedia.KinoCMS.services.SEOBlockService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AdminPagesController {
    private final PagesService pagesService;
    private final SEOBlockService seoBlockService;

    private final ContactsService contactsService;

    @GetMapping("/admin/pages/")
    public String pagesAdmin(Model model){
        model.addAttribute("main", pagesService.getByID(1));
        model.addAttribute("pages", pagesService.getAll());
        model.addAttribute("contacts", contactsService.getAll());
        return "admin/pages";
    }

    @GetMapping("/admin/pages/main/add/")
    public String addMainStart() {

        return "admin/add-main-pages";
    }

    @PostMapping("/admin/pages/main/add/")
    public String addMainEnd(@RequestParam("number1") String number1, @RequestParam("number2") String number2,
                             @RequestParam("seotext") String text,
                             @RequestParam("url") String url, @RequestParam("depiction") String depiction,
                             @RequestParam("title") String title, @RequestParam("keywords") String keywords) throws IOException {
        Pages pages = new Pages();
        String name = number1 + "\t" + number2;
        pages.setName(name);
        pages.setDescription(text);
        pages.setDate(Date.valueOf(LocalDate.now()));

        SEOBlock block = new SEOBlock();
        block.setTitle(title);
        block.setDepiction(depiction);
        block.setKeywords(keywords);
        block.setUrl(url);

        pages.setSeoBlock(block);

        seoBlockService.save(block);
        pagesService.save(pages);
        return "redirect:/admin/pages/";
    }

    @GetMapping("/admin/pages/1/edit/")
    public String editMainPageStart(Model model) {
        model.addAttribute("main", pagesService.getByID(1));
        return "admin/edit-main-pages";
    }

    @PostMapping("/admin/pages/1/edit/")
    public String editMainPageEnd(@RequestParam("number1") String number1, @RequestParam("number2") String number2,
                                  @RequestParam("seotext") String text,
                                  @RequestParam("url") String url, @RequestParam("depiction") String depiction,
                                  @RequestParam("title") String title, @RequestParam("keywords") String keywords){
        Pages pages = pagesService.getByID(1);
        if(!number1.equals("") && !number2.equals("")){
            String name = number1+"\t"+number2;
            pages.setName(name);
        }
        if(!text.equals(""))pages.setDescription(text);

        SEOBlock block = pages.getSeoBlock();
        if(!title.equals(""))block.setTitle(title);
        if(!depiction.equals(""))block.setDepiction(depiction);
        if(!url.equals(""))block.setUrl(url);
        if(!keywords.equals(""))block.setKeywords(keywords);

        seoBlockService.save(block);
        pagesService.save(pages);
        return "redirect:/admin/pages/";
    }

    @GetMapping("/admin/pages/{id}/delete/")
    public String deletePage(@PathVariable("id")Long id){
        pagesService.deleteById(id);
        return "redirect:/admin/pages/";
    }

    @GetMapping("/admin/pages/add/")
    public String addStart(Model model) {

        return "admin/add-pages";
    }

    @PostMapping("/admin/pages/add/")
    public String addEnd(@RequestParam("name") String name, @RequestParam("description") String description,
                         @RequestParam("image") MultipartFile image, @RequestParam("image1") MultipartFile image1,
                         @RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3,
                         @RequestParam("image4") MultipartFile image4,
                         @RequestParam("url") String url, @RequestParam("depiction") String depiction,
                         @RequestParam("title") String title, @RequestParam("keywords") String keywords) throws IOException {
        Pages pages = new Pages();
        pages.setName(name);
        pages.setDescription(description);
        pages.setDate(Date.valueOf(LocalDate.now()));
        pages.setImage(image.getBytes());
        pages.setImage1(image1.getBytes());
        pages.setImage2(image2.getBytes());
        pages.setImage3(image3.getBytes());
        pages.setImage4(image4.getBytes());

        SEOBlock block = new SEOBlock();
        block.setTitle(title);
        block.setDepiction(depiction);
        block.setKeywords(keywords);
        block.setUrl(url);

        pages.setSeoBlock(block);

        seoBlockService.save(block);
        pagesService.save(pages);
        return "redirect:/admin/pages/";
    }

    @GetMapping("/admin/pages/{id}/edit/")
    public String editPageStart(@PathVariable("id")Long id, Model model){
        model.addAttribute("pages", pagesService.getByID(id));
        return "admin/edit-page";
    }

    @PostMapping("/admin/pages/{id}/edit/")
    public String editPageEnd(@PathVariable("id")Long id,@RequestParam("name") String name, @RequestParam("description") String description,
                              @RequestParam("image") MultipartFile image, @RequestParam("image1") MultipartFile image1,
                              @RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3,
                              @RequestParam("image4") MultipartFile image4,
                              @RequestParam("url") String url, @RequestParam("depiction") String depiction,
                              @RequestParam("title") String title, @RequestParam("keywords") String keywords) throws IOException {
        Pages pages = pagesService.getByID(id);
        pages.setName(name);
        pages.setDescription(description);
        if(!image.isEmpty())pages.setImage(image.getBytes());
        if(!image1.isEmpty())pages.setImage1(image1.getBytes());
        if(!image2.isEmpty())pages.setImage2(image2.getBytes());
        if(!image3.isEmpty())pages.setImage3(image3.getBytes());
        if(!image4.isEmpty())pages.setImage4(image4.getBytes());

        SEOBlock block = pages.getSeoBlock();
        if(!url.equals(""))block.setUrl(url);
        if(!depiction.equals(""))block.setDepiction(depiction);
        if(!keywords.equals(""))block.setKeywords(keywords);
        if(!title.equals(""))block.setTitle(title);

        seoBlockService.save(block);
        pagesService.save(pages);
        return "redirect:/admin/pages/";
    }

    @GetMapping("/admin/pages/contacts/edit/")
    public String contacts(Model model){
        model.addAttribute("contacts", contactsService.getAll());
        model.addAttribute("main", pagesService.getByID(1));
        return "admin/contacts";
    }

    @PostMapping("/admin/pages/contacts/edit/{id}/")
    public String contacts(@PathVariable("id")Long id, @RequestParam("name")String name, @RequestParam("address")String address,
                           @RequestParam("x")double x, @RequestParam("y")double y, @RequestParam("image")MultipartFile image) throws IOException {
        Contacts contacts = contactsService.getById(id);
        contacts.setName(name);
        contacts.setX(x);
        contacts.setY(y);
        contacts.setAddress(address);
        if(!image.isEmpty())contacts.setImage(image.getBytes());
        contactsService.save(contacts);
        return "redirect:/admin/pages/contacts/edit/";
    }

    @GetMapping("/admin/pages/contacts/add/")
    public String addContacts(){
        contactsService.save(new Contacts());
        return "redirect:/admin/pages/contacts/edit/";
    }

    @GetMapping("/admin/pages/contacts/delete/{id}")
    public String deleteContact(@PathVariable("id")Long id){
        contactsService.delete(id);
        return "redirect:/admin/pages/contacts/edit/";
    }

    @PostMapping("/admin/pages/contacts/save/{id}/seo-block/")
    public String saveSeoBlock(@PathVariable("id")Long id, @RequestParam("url")String url, @RequestParam("title")String title,
                               @RequestParam("keywords")String keywords, @RequestParam("depiction")String depiction){
        Pages pages = pagesService.getByID(id);
        SEOBlock seoBlock = pages.getSeoBlock();
        seoBlock.setUrl(url);
        seoBlock.setTitle(title);
        seoBlock.setKeywords(keywords);
        seoBlock.setDepiction(depiction);
        seoBlockService.save(seoBlock);
        pagesService.save(pages);
        return "redirect:/admin/pages/contacts/edit/";
    }
}