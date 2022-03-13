package com.example.springbootswagger.bootstrap;

import com.example.springbootswagger.domain.Employee;
import com.example.springbootswagger.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/03/22
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private EmployeeRepository employeeRepository;

    public Bootstrap(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEmployees();
    }

    private void loadEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Pulkit");
        employee1.setLastName("Aggarwal");
        employee1.setEmailId("gargpulkit234@gmail.com");
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Mark");
        employee2.setLastName("Wood");
        employee2.setEmailId("markwood69@gmail.com");
        employeeRepository.save(employee2);

        System.out.println("Data Loaded = " + employeeRepository.count());
    }
}
