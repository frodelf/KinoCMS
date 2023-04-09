package progect.avadaMedia.KinoCMS.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import progect.avadaMedia.KinoCMS.models.Schedule;
import progect.avadaMedia.KinoCMS.repositories.ScheduleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getScheduleByDay(@PathVariable String day){
        List<Schedule> res = scheduleRepository.findByDay(day);
        return res;
    }

    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

}
