package com.example.springbootswagger.controllers;

import com.example.springbootswagger.api.model.EmployeeDTO;
import com.example.springbootswagger.api.model.EmployeeListDTO;
import com.example.springbootswagger.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "View a list of available employees")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeListDTO getAllEmployees(){
        return new EmployeeListDTO(employeeService.getAllEmployees());
    }

    @Operation(summary = "Get an employee by Id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Add an employee")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createNewEmployee(employeeDTO);
    }

    @Operation(summary = "Update an employee")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveEmployeeByDto(id, employeeDTO);
    }

    @Operation(summary = "Delete an employee")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }
}
