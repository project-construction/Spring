package com.construction.constructionapi.Schedule.Service;

import com.construction.constructionapi.Schedule.DTO.ContentDTO;
import com.construction.constructionapi.Schedule.DTO.ScheduleDTO;
import com.construction.constructionapi.Schedule.Domain.Schedule;
import com.construction.constructionapi.Schedule.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 등록
    public void addSchedule(ScheduleDTO scheduleDTO){
        for(ContentDTO contentDTO : scheduleDTO.getSchedules()){
            Schedule newSchedule = new Schedule();
            newSchedule.setDate(scheduleDTO.getDate());
            newSchedule.setTime(contentDTO.getTime());
            newSchedule.setContent(contentDTO.getContent());

            scheduleRepository.save(newSchedule);
        }
    }

    // 일정 조회
    public List<Schedule> findSchedule(String date){
        return scheduleRepository.findAllByDateOrderByTimeAsc(date);
    }

}
