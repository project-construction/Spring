package com.construction.constructionapi.Attendance.Service;

import com.construction.constructionapi.Attendance.Domain.AttendCode;
import com.construction.constructionapi.Attendance.Repository.CodeRepository;
import com.construction.constructionapi.SpringSecurity.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.construction.constructionapi.SpringSecurity.Model.Role.USER;


@Service
public class CodeService {

    private CodeRepository codeRepository;
    private MemberRepository memberRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository, MemberRepository memberRepository) {
        this.codeRepository = codeRepository;
        this.memberRepository = memberRepository;
    }

    public boolean setCode(String email, int code){
        if(memberRepository.findByEmail(email).getRole()==USER) return false;

        String team = memberRepository.findByEmail(email).getTeam();
        List<AttendCode> attendCodes = codeRepository.findByTeam(team);
        AttendCode attendCode = new AttendCode();

        for (int i = 0; i < attendCodes.size(); i++) {
            if(attendCodes.get(i).getDate().substring(0,10).equals(LocalDate.now().toString())){
                attendCode = attendCodes.get(i);
            }
        }

        attendCode.setDate(LocalDateTime.now().toString());
        attendCode.setTeam(team);
        attendCode.setCode(code);

        codeRepository.save(attendCode);
        return true;
    }

    public int getCode(String email) {
        String team = memberRepository.findByEmail(email).getTeam();
        List<AttendCode> attendCodes = codeRepository.findByTeam(team);

        for (int i = 0; i < attendCodes.size(); i++) {
            if(attendCodes.get(i).getDate().substring(0,10).equals(LocalDate.now().toString())){
                return attendCodes.get(i).getCode();
            }
        }

        return Integer.MIN_VALUE;
    }
}
