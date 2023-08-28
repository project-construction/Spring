package com.construction.constructionapi.Employee.Service;

import com.construction.constructionapi.Employee.DTO.ResponseGuestDTO;
import com.construction.constructionapi.Member.Domain.Member;
import com.construction.constructionapi.Member.Model.Role;
import com.construction.constructionapi.Member.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeMangerService {

    private final MemberRepository memberRepository;

    @Autowired
    public EmployeeMangerService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<ResponseGuestDTO> allGuestWorkers(){
        List<Member> guestMembers = memberRepository.findAllByRole(Role.GUEST);
        return guestMembers.stream()
                .map(member -> {
                    ResponseGuestDTO guestDTO = new ResponseGuestDTO();
                    guestDTO.setEmail(member.getEmail());
                    guestDTO.setName(member.getName());
                    guestDTO.setTeam(member.getTeam());
                    guestDTO.setBirth(member.getBirth());
                    guestDTO.setGender(member.getGender());
                    guestDTO.setPhone(member.getPhone());
                    guestDTO.setAddress(member.getAddress());
                    return guestDTO;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public void approveMember(String email) {
        Member member = memberRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 사용자입니다."));

        member.setApproved(true);
        member.setRole(Role.USER); // 승인 후 권한 변경

        memberRepository.save(member);
    }

    public List<String> getAllWorkerNamesInTeam(String teamName){
        return memberRepository.findAllByTeamAndRole(teamName, Role.USER).stream()
                .map(Member::getName)
                .toList();
    }


}
