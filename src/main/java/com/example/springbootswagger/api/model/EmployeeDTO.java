package com.example.springbootswagger.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String firstName;

    private String lastName;

    private String emailId;

    @JsonProperty("employee_url")
    private String employeeUrl;
}
