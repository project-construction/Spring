package com.construction.constructionapi.Worker.Controller;

import com.construction.constructionapi.Worker.DTO.ResponseInfoDTO;
import com.construction.constructionapi.Worker.DTO.ResponseNameDTO;
import com.construction.constructionapi.Worker.DTO.ResponseScoreDTO;
import com.construction.constructionapi.Worker.Service.EmployeeMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class EmployeeController {
    //근로자 관리

    private final EmployeeMangerService employeeMangerService;

    @Autowired
    public EmployeeController(EmployeeMangerService employeeMangerService){
        this.employeeMangerService = employeeMangerService;
    }

    @GetMapping("/wait")
    public List<ResponseInfoDTO> awaitingApprovals(){
        return employeeMangerService.allGuestWorkers();
    }

    @PostMapping("/approval")
    public String approvalGuest(@RequestParam String email){
        employeeMangerService.approveMember(email);
        return "success";
    }

    @DeleteMapping("/refuse")
    public String refuseGuest(@RequestParam String email){
        employeeMangerService.refuseMember(email);
        return "success";
    }

    @PostMapping("/team")
    public List<ResponseNameDTO> AllWorkerNames(@RequestParam String teamName) {
        return employeeMangerService.getAllWorkerNamesInTeam(teamName);
    }

    //기본 정보
    @PostMapping("/info")
    public ResponseInfoDTO detailInfo(@RequestParam String email) {
        return employeeMangerService.workerInfo(email);
    }

    //검사 정보
    @PostMapping("/score")
    public List<ResponseScoreDTO> detailScore(@RequestParam String email) {
        return employeeMangerService.workerScore(email);
    }
}