package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Users;
import progect.avadaMedia.KinoCMS.repositories.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public void save(Users users){
        usersRepository.save(users);
    }

    public Users getUserByNickAndPassword(String nick, String password){
        return usersRepository.findByNickAndPassword(nick, password);
    }
    public List<Users> getAll(){
        return usersRepository.findAll();
    }
    public void deleteById(long id){
        usersRepository.deleteById(id);
    }
    public Users getById(long id){
        return usersRepository.findById(id).get();
    }
    public int numberWomen(){
        int number = 0;
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            if(user.getSex().equals("female"))number++;
        }
        return number;
    }
    public int numberMan(){
        int number = 0;
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            if(user.getSex().equals("male"))number++;
        }
        return number;
    }
}
