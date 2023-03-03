package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequestDto;
import com.example.employeemanagement.dto.EmployeeResponseDto;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo repo;
    @Override
    public List<EmployeeResponseDto> getAllEmployees(){
        List<Employee> allEmployees = repo.findAll();
        List<EmployeeResponseDto> employeeResponseDtoList =
                new ArrayList<>();

        employeeResponseDtoList =
                allEmployees
                .stream()
                .map((employee) -> {
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            employeeResponseDto.setSalary(employee.getSalary());
            employeeResponseDto.setDesignation(employee.getDesignation());
            employeeResponseDto.setDepartment(employee.getDepartment());
            employeeResponseDto.setEmpId(employee.getEmployeeId());
            employeeResponseDto.setLastName(employee.getLastName());
            employeeResponseDto.setFirstName(employee.getFirstName());
            return employeeResponseDto;
        })
                .collect(Collectors.toList());
        return employeeResponseDtoList;
    }

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = new Employee(
                employeeRequestDto.getEmpId(),
                employeeRequestDto.getFirstName(),
                employeeRequestDto.getLastName(),
                employeeRequestDto.getDepartment(),
                employeeRequestDto.getDesignation(),
                employeeRequestDto.getSalary()
        );

        Employee savedEmp = repo.save(employee);

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(employee);
//        employeeResponseDto.setFirstName(savedEmp.getFirstName());
//        employeeResponseDto.setSalary(savedEmp.getSalary());
//        employeeResponseDto.setDesignation(savedEmp.getDesignation());
//        employeeResponseDto.setLastName(savedEmp.getLastName());
//        employeeResponseDto.setDepartment(savedEmp.getDepartment());
//        employeeResponseDto.setEmpId(savedEmp.getEmployeeId());
//

        return employeeResponseDto;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(long id) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = repo.findById(id);

        if (optionalEmployee.isPresent()){
            Employee employeeGot = optionalEmployee.get();
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(employeeGot);
            return employeeResponseDto;
        }
        else {
            throw new EmployeeNotFoundException("Emp Id : " + id + " not found in db");
        }
    }

    @Override
    public EmployeeResponseDto updateEmployee(long id, EmployeeRequestDto employeeRequestDto){

        Optional<Employee> employeeOptional = repo.findById(id);
        if (employeeOptional.isPresent()){
            Employee employeeGot = employeeOptional.get();
            employeeGot.setDepartment(employeeRequestDto.getDepartment());
            employeeGot.setDesignation(employeeRequestDto.getDesignation());
            employeeGot.setSalary(employeeRequestDto.getSalary());
            employeeGot.setLastName(employeeRequestDto.getLastName());
            employeeGot.setFirstName(employeeRequestDto.getFirstName());

            repo.flush();

            return new EmployeeResponseDto(employeeGot);
        }
        else {
           return this.addEmployee(employeeRequestDto);
        }
    }

    @Override
    public EmployeeResponseDto deleteEmployee(long id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = repo.findById(id);
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            repo.delete(employee);
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(employee);

            return employeeResponseDto;
        }
        else {
            throw new EmployeeNotFoundException("Emp Id : " + id + " not found in db");
        }
    }
}
