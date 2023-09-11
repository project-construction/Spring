package com.construction.constructionapi.Schedule.Controller;

import com.construction.constructionapi.Schedule.DTO.ScheduleDTO;
import com.construction.constructionapi.Schedule.Domain.Schedule;
import com.construction.constructionapi.Schedule.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 일정 추가
    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleDTO scheduleDTO){
        scheduleService.addSchedule(scheduleDTO);
        return ResponseEntity.ok().body("success");
    }

    // 일정 불러오기
    @GetMapping("/find/{date}")
    public ResponseEntity<List<Schedule>> findSchedule(@PathVariable String date){
        return ResponseEntity.ok().body(scheduleService.findSchedule(date));
    }

    // 일정 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().body("success");
    }

}
