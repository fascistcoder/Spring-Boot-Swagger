package com.example.springbootswagger.api.mapper;

import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
class EmployeeMapperTest {

    public static final String FIRSTNAME = "Pulkit";
    public static final String LASTNAME = "Aggarwal";
    public static final String EMAIL = "gargpulkit234@gmail.com";

    EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Test
    void employeeToEmployeeDTO() throws Exception{

        Employee employee = new Employee();
        employee.setFirstName(FIRSTNAME);
        employee.setLastName(LASTNAME);
        employee.setEmailId(EMAIL);

        EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDTO(employee);

        assertEquals(employee.getFirstName(), employeeDTO.getFirstName());
        assertEquals(employee.getLastName(), employeeDTO.getLastName());
        assertEquals(employee.getEmailId(), employeeDTO.getEmailId());
    }

    @Test
    void employeeDTOToEmployee() throws Exception{
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(FIRSTNAME);
        employeeDTO.setLastName(LASTNAME);
        employeeDTO.setEmailId(EMAIL);

        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);

        assertEquals(employeeDTO.getFirstName(), employee.getFirstName());
        assertEquals(employeeDTO.getLastName(), employee.getLastName());
        assertEquals(employeeDTO.getEmailId(), employee.getEmailId());
    }
}