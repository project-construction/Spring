package com.construction.constructionapi.Notice.Repository;

import com.construction.constructionapi.Notice.Domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Notice findById(int id);
    List<Notice> findAllByUserID(int id);
    List<Notice> findAllByTitleContaining(String title);
    List<Notice> findAllByContentContaining(String content);
}
