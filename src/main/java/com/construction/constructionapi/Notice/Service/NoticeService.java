package com.construction.constructionapi.Notice.Service;

import com.construction.constructionapi.Notice.DTO.NoticeDTO;
import com.construction.constructionapi.Notice.Domain.Notice;
import com.construction.constructionapi.Notice.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoticeService {

    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    public void addNotice(NoticeDTO noticeDTO){
        Notice newNotice = new Notice();

        newNotice.setTitle(noticeDTO.getTitle());
        newNotice.setContent(noticeDTO.getContent());
        newNotice.setUserID(noticeDTO.getUser_id());
        newNotice.setWrite_date(LocalDate.now());

        noticeRepository.save(newNotice);
    }

    public List<Notice> allNotice(){
        return noticeRepository.findAll();
    }

    public Notice noticeContent(int id){
        return noticeRepository.findById(id);
    }

    public List<Notice> titleNotice(String title){
        return noticeRepository.findAllByTitleContaining(title);
    }

    public List<Notice> contentNotice(String content){
        return noticeRepository.findAllByContentContaining(content);
    }

    public List<Notice> writerNotice(int id){
        return noticeRepository.findAllByUserID(id);
    }
}
