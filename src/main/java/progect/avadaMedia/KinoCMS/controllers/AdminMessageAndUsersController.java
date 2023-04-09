package progect.avadaMedia.KinoCMS.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import progect.avadaMedia.KinoCMS.models.Email;
import progect.avadaMedia.KinoCMS.models.Users;
import progect.avadaMedia.KinoCMS.repositories.UsersRepository;
import progect.avadaMedia.KinoCMS.services.EmailService;
import progect.avadaMedia.KinoCMS.services.EmailUtil;
import progect.avadaMedia.KinoCMS.services.UsersService;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMessageAndUsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final EmailUtil emailUtil;


    @GetMapping("/admin/message/")
    public String messageAdmin(Model model) {
        model.addAttribute("number_users", usersService.getAll().size());
        model.addAttribute("last_three", emailService.getLastThree());
        return "admin/message";
    }

    @GetMapping("/admin/users/{id}/")
    public String usersAdmin(@PathVariable("id")int id, Model model) {

        if (id < 1) {
            id = 1;
            return "redirect:/admin/users/1/";
        }
        Page<Users> users = usersRepository.findAll(PageRequest.of((id-1), 2));
        int max = (int) Math.ceil(usersService.getAll().size() / 2.0);

        System.out.println(max);
        if (id > max) {
            id = max;
            return "redirect:/admin/users/"+max+"/";
        }
        model.addAttribute("users", users);
        model.addAttribute("left", id-1);
        model.addAttribute("right", ++id);
        return "admin/users";
    }

    @GetMapping("/admin/users/{id}/edit/")
    public String editUsersStart(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersService.getById(id));
        return "admin/edit-users";
    }

    @PostMapping("/admin/users/{id}/edit/")
    public String editUsersEnd(@PathVariable("id") Long id, Model model,
                               @RequestParam("name") String name, @RequestParam("surname") String surname,
                               @RequestParam("nick") String nick, @RequestParam("email") String email,
                               @RequestParam("password") String password, @RequestParam(value = "language", required = false) String language,
                               @RequestParam(value = "sex", required = false) String sex, @RequestParam("phone") String phone,
                               @RequestParam("city") String city, @RequestParam("credit-cart") String cart, @RequestParam("birthday") Date birthday) {
        Users users = usersService.getById(id);
        users.setName(name);
        users.setSurname(surname);
        users.setNick(nick);
        users.setEmail(email);
        if (birthday != null) users.setBirthday(birthday);
        users.setPassword(password);
        if (language != null) users.setLanguage(language);
        if (sex != null) users.setSex(sex);
        users.setPhone(phone);
        users.setCity(city);
        users.setCreditCart(cart);
        usersService.save(users);
        return "redirect:/admin/users/";
    }

    @GetMapping("/admin/users/{id}/delete/")
    public String deleteUsers(@PathVariable("id") Long id) {
        usersService.deleteById(id);
        return "redirect:/admin/users/";
    }

    @GetMapping("/admin/message/users/")
    public String messageAdminUsers(Model model) {
        model.addAttribute("users", usersService.getAll());
        return "admin/select-user";
    }

    @PostMapping("/admin/message/select/")
    public String messageAdminUsersSelect(@RequestParam(value = "selectedUsers", required = false) List<Long> selectedUsers, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String usersIdJson = mapper.writeValueAsString(selectedUsers);
        model.addAttribute("users_id", usersIdJson);
        model.addAttribute("number_users", selectedUsers.size());
        model.addAttribute("last_three", emailService.getLastThree());
        return "admin/message";
    }

    @PostMapping("/admin/message/send/")
    public String messageSend(@RequestParam(value = "users_id", required = false) String usersIdStr, @RequestParam("message") MultipartFile text) throws IOException {
        Email emailO = new Email();
        emailO.setMessage(text.getBytes());
        String subject = text.getOriginalFilename();
        subject = subject.replace(".html", "");
        emailO.setSubject(subject);
        emailService.save(emailO);
        System.out.println("cvbnm,kmlllllll"+ usersIdStr);
        System.out.println(usersIdStr!=null);
        System.out.println(usersIdStr!=null && !usersIdStr.equals(""));
        if (!usersIdStr.equals("")) {
            ObjectMapper mapper = new ObjectMapper();
            List<Long> usersId = mapper.readValue(usersIdStr, new TypeReference<>() {
            });
            ArrayList<Users> users = new ArrayList<>();
            for (Long aLong : usersId) {
                users.add(usersService.getById(aLong));
            }
            ArrayList<String> emails = new ArrayList<>();
            for (Users user : users) {
                emails.add(user.getEmail());
            }
            for (String email : emails) {

                emailUtil.sendEmail("20denisderkach04@gmail.com", email, text.getName(), text);
            }
        }
        else {
            ArrayList<Users> users = (ArrayList<Users>) usersService.getAll();
            for (Users user : users) {
                emailUtil.sendEmail("20denisderkach04@gmail.com", user.getEmail(), text.getName(), text);
            }
        }
        return "redirect:/admin/message/";
    }

    @GetMapping("/admin/message/send/{id}")
    public String messageSendById(@PathVariable("id")Long id, @RequestParam(value = "users_id", required = false) String usersIdStr) throws IOException {
        Email letter = emailService.getEmailById(id);
        String subject = letter.getSubject();
        MultipartFile message = letter.getMessages();
        if (usersIdStr!=null && !usersIdStr.equals("")) {
            ObjectMapper mapper = new ObjectMapper();
            List<Long> usersId = mapper.readValue(usersIdStr, new TypeReference<>() {
            });
            ArrayList<Users> users = new ArrayList<>();
            for (Long aLong : usersId) {
                users.add(usersService.getById(aLong));
            }
            ArrayList<String> emails = new ArrayList<>();
            for (Users user : users) {
                emails.add(user.getEmail());
            }
            for (String email : emails) {

                emailUtil.sendEmail("20denisderkach04@gmail.com", email, subject, message);
            }
        }
        else {
            ArrayList<Users> users = (ArrayList<Users>) usersService.getAll();
            for (Users user : users) {
                emailUtil.sendEmail("20denisderkach04@gmail.com", user.getEmail(), subject, message);
            }
        }
        return "redirect:/admin/message/";
    }
}
