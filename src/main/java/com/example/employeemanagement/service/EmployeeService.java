package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequestDto;
import com.example.employeemanagement.dto.EmployeeResponseDto;
import com.example.employeemanagement.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto getEmployeeById(long id) throws EmployeeNotFoundException;

    EmployeeResponseDto updateEmployee(long id, EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto deleteEmployee(long id) throws EmployeeNotFoundException;
}
