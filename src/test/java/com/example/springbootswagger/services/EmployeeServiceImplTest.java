package com.example.springbootswagger.services;

import com.example.springbootswagger.api.mapper.EmployeeMapper;
import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.controllers.EmployeeController;
import com.example.springbootswagger.domain.Employee;
import com.example.springbootswagger.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 14/03/22
 */
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeService = new EmployeeServiceImpl(EmployeeMapper.INSTANCE, employeeRepository);
    }

    private Employee getEmployee1(){
        Employee employee1 = new Employee();
        employee1.setFirstName("Michael");
        employee1.setLastName("Cummins");
        employee1.setEmailId("michael1243@gmail.com");
        return employee1;
    }

    private Employee getEmployee2(){
        Employee employee2 = new Employee();
        employee2.setFirstName("Steve");
        employee2.setLastName("Smith");
        employee2.setEmailId("steve1243@gmail.com");
        return employee2;
    }

    @Test
    void getAllEmployees() {
        //when
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(getEmployee1(),getEmployee2()));

        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();

        //then
        assertEquals(2, employeeDTOList.size());
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void createNewEmployee() {
    }

    @Test
    void saveEmployeeByDto() {
    }

    @Test
    void deleteEmployeeById() {
    }
}