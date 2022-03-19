package com.example.springbootswagger.services;

import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.domain.Employee;

import java.util.List;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO saveEmployeeByDto(Long id, EmployeeDTO employeeDTO);
}
