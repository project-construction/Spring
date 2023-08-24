package com.construction.constructionapi.Notice.Repository;

import com.construction.constructionapi.Notice.Domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    void deleteById(int id);
    Notice findById(int id);
    List<Notice> findAllByUserID(String id);
    List<Notice> findAllByTitleContaining(String title);
    List<Notice> findAllByContentContaining(String content);

}
