package com.example.springbootswagger.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "The employee first name" )
    private String firstName;

    @Schema(description = "The employee last name")
    private String lastName;

    @Schema(description = "The employee email id")
    private String emailId;

    @JsonProperty("employee_url")
    private String employeeUrl;
}
