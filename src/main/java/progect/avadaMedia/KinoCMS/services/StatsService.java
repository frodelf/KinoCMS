package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progect.avadaMedia.KinoCMS.models.Stats;
import progect.avadaMedia.KinoCMS.repositories.StatsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final StatsRepository statsRepository;
    public List<String> getAllMonth(){
        List<String> months = new ArrayList<>();
        for (Stats stats : statsRepository.findAll()) {
            months.add(stats.getMonth());
        }
        System.out.println(months);
        return months;
    }
    public List<Long> getAllValue(){
        List<Long> value = new ArrayList<>();
        for (Stats stats : statsRepository.findAll()) {
            value.add(stats.getValue());
        }
        System.out.println(value);
        return value;
    }
}
