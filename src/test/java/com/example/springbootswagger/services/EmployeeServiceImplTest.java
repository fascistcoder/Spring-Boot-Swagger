package com.example.springbootswagger.services;

import com.example.springbootswagger.api.mapper.EmployeeMapper;
import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.domain.Employee;
import com.example.springbootswagger.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 14/03/22
 */
class EmployeeServiceImplTest {


    public static final String FIRSTNAME_1 = "Michael";
    public static final String LASTNAME_1 = "Cummins";
    public static final String EMAIL_1 = "michael1243@gmail.com";
    public static final long ID_1 = 1L;
    public static final String FIRSTNAME_2 = "Steve";
    public static final String LASTNAME_2 = "Smith";
    public static final String EMAIL_2 = "steve1243@gmail.com";
    public static final long ID_2 = 2L;

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
        employee1.setFirstName(FIRSTNAME_1);
        employee1.setLastName(LASTNAME_1);
        employee1.setEmailId(EMAIL_1);
        employee1.setId(ID_1);
        return employee1;
    }

    private Employee getEmployee2(){
        Employee employee2 = new Employee();
        employee2.setFirstName(FIRSTNAME_2);
        employee2.setLastName(LASTNAME_2);
        employee2.setEmailId(EMAIL_2);
        employee2.setId(ID_2);
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
        //given
        Employee employee = getEmployee1();

        //mockito BDD syntax
        given(employeeRepository.findById(anyLong())).willReturn(Optional.of(employee));

        //when
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(1L);

        //then
        then(employeeRepository).should(times(1)).findById(anyLong());

        //JUnit Assert that with matchers
        assertThat(employeeDTO.getFirstName(), is(equalTo(FIRSTNAME_1)));
        assertThat(employeeDTO.getLastName(), is(equalTo(LASTNAME_1)));
        assertThat(employeeDTO.getEmailId(), is(equalTo(EMAIL_1)));
    }

    @Test
    void createNewEmployee() {
        //given
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(FIRSTNAME_1);
        employeeDTO.setLastName(LASTNAME_1);
        employeeDTO.setEmailId(EMAIL_1);

        Employee employee = getEmployee1();

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        //when
        EmployeeDTO savedEmployeeDTO = employeeService.createNewEmployee(employeeDTO);

        //then
        // 'should' defaults to times = 1
        then(employeeRepository).should().save(any(Employee.class));
        assertThat(savedEmployeeDTO.getEmployeeUrl(), containsString("1"));
    }

    @Test
    void saveEmployeeByDto() {
        //given
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(FIRSTNAME_1);
        employeeDTO.setLastName(LASTNAME_1);
        employeeDTO.setEmailId(EMAIL_1);


        Employee employee = getEmployee1();

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        //when
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployeeByDto(ID_1, employeeDTO);

        //then
        // 'should' defaults to times = 1
        then(employeeRepository).should().save(any(Employee.class));
        assertThat(savedEmployeeDTO.getEmployeeUrl(), containsString("1"));
    }

    @Test
    void deleteEmployeeById() {
        employeeService.deleteEmployeeById(1L);
        then(employeeRepository).should().deleteById(anyLong());
    }
}