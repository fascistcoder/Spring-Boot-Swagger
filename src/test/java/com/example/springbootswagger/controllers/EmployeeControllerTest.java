package com.example.springbootswagger.controllers;

import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.example.springbootswagger.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 14/03/22
 */
class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    List<EmployeeDTO> employeeList(EmployeeDTO employee1, EmployeeDTO employee2){

        employee1.setFirstName("Michael");
        employee1.setLastName("Cummins");
        employee1.setEmailId("michael1243@gmail.com");
        employee1.setEmployeeUrl(EmployeeController.BASE_URL + "/1");


        employee2.setFirstName("Steve");
        employee2.setLastName("Smith");
        employee2.setEmailId("steve1243@gmail.com");
        employee2.setEmployeeUrl(EmployeeController.BASE_URL + "/2");

       return Arrays.asList(employee1, employee2);
    }

    EmployeeDTO employee(EmployeeDTO employee1){
        employee1.setFirstName("Michael");
        employee1.setLastName("Cummins");
        employee1.setEmailId("michael1243@gmail.com");
        employee1.setEmployeeUrl(EmployeeController.BASE_URL + "/1");

        return employee1;
    }

    @Test
    void getAllEmployees() throws Exception {
        EmployeeDTO employee1 = new EmployeeDTO();

        EmployeeDTO employee2 = new EmployeeDTO();

        when(employeeService.getAllEmployees()).thenReturn(employeeList(employee1, employee2));

        mockMvc.perform(get(EmployeeController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees", hasSize(2)));
    }


    @Test
    void getEmployeeById() throws Exception{

        EmployeeDTO employee1 = new EmployeeDTO();

        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee(employee1));

        mockMvc.perform(get(EmployeeController.BASE_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")));
    }

    @Test
    void createNewEmployee() throws Exception{
        EmployeeDTO employee1 = new EmployeeDTO();

        EmployeeDTO returnDTO = new EmployeeDTO();
        returnDTO.setFirstName(employee(employee1).getFirstName());
        returnDTO.setLastName(employee(employee1).getLastName());
        returnDTO.setEmailId(employee(employee1).getEmailId());
        returnDTO.setEmployeeUrl(EmployeeController.BASE_URL + "/1");

        when(employeeService.createNewEmployee(employee1)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post(EmployeeController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")))
                .andExpect(jsonPath("$.employee_url", equalTo(EmployeeController.BASE_URL + "/1")));
    }

    @Test
    void updateEmployee() throws Exception{
        EmployeeDTO employee1 = new EmployeeDTO();

        EmployeeDTO returnDTO = new EmployeeDTO();
        returnDTO.setFirstName(employee(employee1).getFirstName());
        returnDTO.setLastName(employee(employee1).getLastName());
        returnDTO.setEmailId(employee(employee1).getEmailId());
        returnDTO.setEmployeeUrl(EmployeeController.BASE_URL + "/1");

        when(employeeService.saveEmployeeByDto(anyLong(), any(EmployeeDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(EmployeeController.BASE_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")))
                .andExpect(jsonPath("$.employee_url", equalTo(EmployeeController.BASE_URL + "/1")));
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete(EmployeeController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(employeeService).deleteEmployeeById(anyLong());
    }
}