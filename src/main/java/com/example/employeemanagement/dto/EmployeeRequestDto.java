package com.example.employeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRequestDto {
    private long empId;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;
}
