package com.example.springbootswagger.services;

import com.example.springbootswagger.api.mapper.EmployeeMapper;
import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.controllers.EmployeeController;
import com.example.springbootswagger.domain.Employee;
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

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::employeeToEmployeeDTO)
                .map(employeeDTO -> {
                    employeeDTO.setEmployeeUrl(getEmployeeUrl(id));
                    return employeeDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        return saveAndReturnByDTO(employeeMapper.employeeDTOToEmployee(employeeDTO));
    }

    @Override
    public EmployeeDTO saveEmployeeByDto(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employee.setId(id);

        return saveAndReturnByDTO(employee);
    }

    private EmployeeDTO saveAndReturnByDTO(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDTO(savedEmployee);

        employeeDTO.setEmployeeUrl(getEmployeeUrl(savedEmployee.getId()));

        return employeeDTO;
    }
}
