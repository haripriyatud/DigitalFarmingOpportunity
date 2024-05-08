package BackendDevelopment.DigitalFarmingOpportunity.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import BackendDevelopment.DigitalFarmingOpportunity.models.Employee;
import BackendDevelopment.DigitalFarmingOpportunity.services.EmployeeService;

@ExtendWith(MockitoExtension.class)

public class EmployeeControllerTest {
	@Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;
    
    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        when(employeeService.createEmployee(employee)).thenReturn(employee);
        ResponseEntity<Employee> response = employeeController.createEmployee(employee);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }
    
    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> response = employeeController.getAllEmployees();
        assertEquals(employees.size(), response.size());
        assertEquals(employee1, response.get(0));
        assertEquals(employee2, response.get(1));
    }

    @Test
    void testGetParticularEmployee() {
        Long id = 1L;
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(id)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getParticularEmployee(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    void testGetParticularEmployee_NotFound() {
	    Long id = 1L;
        when(employeeService.getEmployeeById(id)).thenReturn(null);

        ResponseEntity<Employee> response = employeeController.getParticularEmployee(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteEmployee() {
        Long id = 1L;
        ResponseEntity<Void> response = employeeController.deleteEmployee(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(id);
    }
}
