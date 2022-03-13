package com.example.springbootswagger.repositories;

import com.example.springbootswagger.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
