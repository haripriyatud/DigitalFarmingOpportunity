
package BackendDevelopment.DigitalFarmingOpportunity.services;

import BackendDevelopment.DigitalFarmingOpportunity.Repository.EmployeeRepository;
import BackendDevelopment.DigitalFarmingOpportunity.models.Department;
import BackendDevelopment.DigitalFarmingOpportunity.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        Department department = new Department();
        department.setId(12L);
        employee.setName("Test Employee");
        employee.setDepartment(department);
        Employee createdEmployee = employeeService.createEmployee(employee);
        assertNotNull(createdEmployee.getId());
        assertEquals("Test Employee", createdEmployee.getName());
        assertEquals(department.getId(), createdEmployee.getDepartment().getId());
    }
    
    @Test
    public void testFindAllEmployees() {
        Department department = new Department();
        department.setId(14L);
        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setDepartment(department);
        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        employee2.setDepartment(department);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertEquals(2, allEmployees.size());
    }
}
