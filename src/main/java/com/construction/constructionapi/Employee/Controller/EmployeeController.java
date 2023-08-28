package com.construction.constructionapi.Employee.Controller;

import com.construction.constructionapi.Employee.DTO.ResponseGuestDTO;
import com.construction.constructionapi.Employee.Service.EmployeeMangerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "https://web-template-3prof2llkxuyz4l.sel4.cloudtype.app")
public class EmployeeController {
    //근로자 관리

    private final EmployeeMangerService employeeMangerService;

    @Autowired
    public EmployeeController(EmployeeMangerService employeeMangerService){
        this.employeeMangerService = employeeMangerService;
    }

    @GetMapping("/wait")
    public List<ResponseGuestDTO> awaitingApprovals(){
        return employeeMangerService.allGuestWorkers();
    }

    @PostMapping("/approval")
    public String approvalGuest(@RequestParam String email){
        employeeMangerService.approveMember(email);
        return "success";
    }

}