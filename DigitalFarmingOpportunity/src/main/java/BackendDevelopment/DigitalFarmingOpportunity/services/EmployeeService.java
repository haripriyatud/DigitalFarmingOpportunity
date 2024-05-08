package BackendDevelopment.DigitalFarmingOpportunity.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendDevelopment.DigitalFarmingOpportunity.Repository.EmployeeRepository;
import BackendDevelopment.DigitalFarmingOpportunity.exceptionalhandler.ResourceNotFoundException;
import BackendDevelopment.DigitalFarmingOpportunity.models.Department;
import BackendDevelopment.DigitalFarmingOpportunity.models.Employee;
import jakarta.validation.Valid;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	public Employee createEmployee(@Valid Employee employee) {
		logger.info("Creating employee- service: {}", employee);

		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		logger.info("Fetching all employees- service");
		return employeeRepository.findAll();
	}

	public void deleteEmployee(Long id) {
		logger.info("Deleting employee with id: {} - service", id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		employeeRepository.delete(employee);
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	}

	public Employee updateEmployee(Long id, @Valid Employee employeeDetails) {
		logger.info("Updating employee with id: {}, Details: {}- service", id, employeeDetails);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		employee.setName(employeeDetails.getName());
		employee.setDepartment(new Department());
		return employeeRepository.save(employee);
	}

}
