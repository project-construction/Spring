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

    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleDTO scheduleDTO){
        scheduleService.addSchedule(scheduleDTO);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/find/{date}")
    public ResponseEntity<List<Schedule>> findSchedule(@PathVariable String date){
        return ResponseEntity.ok().body(scheduleService.findSchedule(date));
    }



}
