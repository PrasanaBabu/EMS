package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EmployeeResponseDto {
    private long empId;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;

    public EmployeeResponseDto(Employee employee){
        this.setEmpId(employee.getEmployeeId());
        this.setFirstName(employee.getFirstName());
        this.setLastName(employee.getLastName());
        this.setDesignation(employee.getDesignation());
        this.setDepartment(employee.getDepartment());
        this.setSalary(employee.getSalary());
    }
}
