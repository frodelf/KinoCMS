package progect.avadaMedia.KinoCMS.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progect.avadaMedia.KinoCMS.models.Users;
import progect.avadaMedia.KinoCMS.services.ScheduleService;
import progect.avadaMedia.KinoCMS.services.UsersService;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class LogController {
    private final ScheduleService scheduleService;
    private final UsersService usersService;

    @GetMapping("/login/")
    public String login(Model model){

        return "user/login";
    }

    @GetMapping("/login/registration/")
    public String registration(Model model){

        return "user/registration";
    }

    @PostMapping("/login/in/")
    public String logIn(@RequestParam("nickname")String nick, @RequestParam("password")String password){
        Users users = usersService.getUserByNickAndPassword(nick, password);
        if (users.getRole().equals("user")) return "redirect:/";
        if(users.getRole().equals("admin"))return "redirect:/admin/";
        return "user/index";
    }
    

    @PostMapping("/login/registration/")
    public String registration(@RequestParam("name") String name, @RequestParam("surname") String surname,
                               @RequestParam("nick") String nick, @RequestParam("email") String email,
                               @RequestParam("password") String password, @RequestParam("language") String language,
                               @RequestParam("sex") String sex, @RequestParam("phone") String phone,
                               @RequestParam("city") String city, @RequestParam("credit-cart") String cart, @RequestParam("birthday")Date birthday){

        Users user = new Users();
        user.setName(name);
        user.setSurname(surname);
        user.setNick(nick);
        user.setEmail(email);
        user.setPassword(password);
        user.setLanguage(language);
        user.setSex(sex);
        user.setPhone(phone);
        user.setBirthday(birthday);
        user.setCity(city);
        user.setCreditCart(cart);
        user.setDate(Date.valueOf(LocalDate.now()));
        usersService.save(user);
        return "redirect:/";
    }
}
