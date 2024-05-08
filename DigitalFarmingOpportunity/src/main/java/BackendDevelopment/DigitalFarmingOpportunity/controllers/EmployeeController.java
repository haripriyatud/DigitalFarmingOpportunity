package BackendDevelopment.DigitalFarmingOpportunity.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import BackendDevelopment.DigitalFarmingOpportunity.models.Employee;
import BackendDevelopment.DigitalFarmingOpportunity.services.EmployeeService;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	private final EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		logger.info("Creating employee- controller: {}", employee);
		Employee createdEmployee = employeeService.createEmployee(employee);
		logger.info("Department:" + employee.getDepartment());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		logger.info("Fetching all employees");
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getParticularEmployee(@PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		logger.info("Getting employee by id- controller: {}", employee);

		if (employee != null) {
			logger.info("employee found");

			return ResponseEntity.ok(employee);
		} else {
			logger.info("employee not found");

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		logger.info("Deleting employee- controller: {}", +id);
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
