package com.example.springbootswagger.domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "The database generated employee ID")
    private Long id;

    @Schema(description = "The employee first name" )
    private String firstName;

    @Schema(description = "The employee last name")
    private String lastName;

    @Schema(description = "The employee email id")
    private String emailId;
}
