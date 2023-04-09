package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Email;
import progect.avadaMedia.KinoCMS.repositories.EmailRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;

    public void save(Email email){
        emailRepository.save(email);
    }
    public List<Email> getLastThree(){
        List<Email> emails = new ArrayList<>();
        int max = emailRepository.findAll().size();
        for (int i = 1; i < 4; i++) {
            emails.add(emailRepository.findAll().get(max-i));
        }
        return emails;
    }

    public Email getEmailById(long id){
        return emailRepository.findById(id).get();
    }
}
