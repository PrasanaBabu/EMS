package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeRequestDto;
import com.example.employeemanagement.dto.EmployeeResponseDto;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeApi {

    private final EmployeeService employeeService;

    public EmployeeApi(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(){

        List<EmployeeResponseDto> allEmployees = employeeService.getAllEmployees();
        if(allEmployees.isEmpty()){
            return new ResponseEntity<>(allEmployees, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(allEmployees, HttpStatus.OK);
        }
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmpById(@PathVariable("id")long id){
        try {
            EmployeeResponseDto employeeById = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employeeById, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(new EmployeeResponseDto(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("employees")
    public ResponseEntity<EmployeeResponseDto> addNewEmployee(@Validated @RequestBody EmployeeRequestDto employeeRequestDto){
        EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(employeeRequestDto);

        if (employeeResponseDto!= null){
            return new ResponseEntity<>(employeeResponseDto, HttpStatus.CREATED);
        }
        else {
            return null;
        }
    }

}
