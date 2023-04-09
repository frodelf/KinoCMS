package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Contacts;
import progect.avadaMedia.KinoCMS.repositories.ContactsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsRepository contactsRepository;

    public List<Contacts> getAll(){
        return contactsRepository.findAll();
    }

    public Contacts getById(long id){
        return contactsRepository.findById(id).get();
    }

    public void delete(long id){
        contactsRepository.deleteById(id);
    }

    public void save(Contacts contacts){contactsRepository.save(contacts);}
}
