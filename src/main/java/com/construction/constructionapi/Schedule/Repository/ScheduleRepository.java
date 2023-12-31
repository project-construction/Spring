package com.construction.constructionapi.Schedule.Repository;

import com.construction.constructionapi.Schedule.Domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findById(long id);
    List<Schedule> findAllByDateOrderByTimeAsc(String date);
    void deleteById(long id);
}
