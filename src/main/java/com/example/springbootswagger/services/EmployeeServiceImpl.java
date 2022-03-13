package com.example.springbootswagger.services;

import com.example.springbootswagger.api.mapper.EmployeeMapper;
import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.controllers.EmployeeController;
import com.example.springbootswagger.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    private String getEmployeeUrl(Long id) {
        return EmployeeController.BASE_URL + "/" + id;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDTO(employee);
                    employeeDTO.setEmployeeUrl(getEmployeeUrl(employee.getId()));
                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }
}
