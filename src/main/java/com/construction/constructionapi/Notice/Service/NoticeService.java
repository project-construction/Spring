package com.construction.constructionapi.Notice.Service;

import com.construction.constructionapi.Notice.DTO.NoticeUpdateDTO;
import com.construction.constructionapi.Notice.DTO.NoticeWriteDTO;
import com.construction.constructionapi.Notice.Domain.Notice;
import com.construction.constructionapi.Notice.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class NoticeService {

    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    // 공지사항 추가
    public void addNotice(String email, NoticeWriteDTO noticeWriteDTO){
        Notice newNotice = new Notice();

        newNotice.setUserID(email);
        newNotice.setTitle(noticeWriteDTO.getTitle());
        newNotice.setContent(noticeWriteDTO.getContent());
        newNotice.setWrite_date(LocalDate.now());

        noticeRepository.save(newNotice);
    }

    // 공지사항 수정
    public void updateNotice(NoticeUpdateDTO noticeUpdateDTO){
        Notice newNotice = new Notice();

        newNotice.setId(noticeUpdateDTO.getId());
        newNotice.setUserID(noticeRepository.findById(noticeUpdateDTO.getId()).getUserID());
        newNotice.setTitle(noticeUpdateDTO.getTitle());
        newNotice.setContent(noticeUpdateDTO.getContent());
        newNotice.setWrite_date(noticeRepository.findById(noticeUpdateDTO.getId()).getWrite_date());

        noticeRepository.save(newNotice);
    }

    // 공지사항 전체 조회
    public List<Notice> allNotice(){
        return noticeRepository.findAllByOrderByIdDesc();
    }

    // 공지사항 상세 내용 조회
    public Notice noticeContent(int id){
        return noticeRepository.findById(id);
    }

    // 공지사항 제목 검색
    public List<Notice> titleNotice(String title){ return noticeRepository.findAllByTitleContainingOrderByIdDesc(title); }

    // 공지사항 작성자 검색
    public List<Notice> writerNotice(String id){
        return noticeRepository.findAllByUserIDOrderByIdDesc(id);
    }

    // 공지사항 학제
    public void deleteNotice(int id) { noticeRepository.deleteById(id);}
}
