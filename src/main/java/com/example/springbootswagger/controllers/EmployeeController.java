package com.example.springbootswagger.controllers;

import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.api.model.EmployeeListDTO;
import com.example.springbootswagger.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
@RestController
@RequestMapping(EmployeeController.BASE_URL)
public class EmployeeController {

    public static final String BASE_URL = "/api/employees";
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeListDTO getAllEmployees(){
        return new EmployeeListDTO(employeeService.getAllEmployees());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createNewEmployee(employeeDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveEmployeeByDto(id, employeeDTO);
    }
}
