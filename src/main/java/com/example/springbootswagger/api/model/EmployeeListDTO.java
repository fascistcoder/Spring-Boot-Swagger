package com.example.springbootswagger.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListDTO {
    List<EmployeeDTO> employees;
}
