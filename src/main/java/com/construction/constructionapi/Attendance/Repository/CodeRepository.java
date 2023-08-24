package com.construction.constructionapi.Attendance.Repository;

import com.construction.constructionapi.Attendance.Domain.AttendCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<AttendCode, Long> {
    List<AttendCode> findByTeam(String team);
}
