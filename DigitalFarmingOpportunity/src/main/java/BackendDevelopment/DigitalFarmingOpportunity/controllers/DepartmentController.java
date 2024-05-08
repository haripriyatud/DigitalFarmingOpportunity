package BackendDevelopment.DigitalFarmingOpportunity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import BackendDevelopment.DigitalFarmingOpportunity.models.Department;
import BackendDevelopment.DigitalFarmingOpportunity.services.DepartmentService;
import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DepartmentController {

	private final DepartmentService departmentService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping("/departments")
	public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
		logger.info("Creating department- controller: {}", department);
		Department createdDepartment = departmentService.createDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
	}

	@GetMapping("/departments")
	public List<Department> getAllDepartments() {
		logger.info("Fetching all departments- controller");
		return departmentService.getAllDepartments();
	}

	@DeleteMapping("/department/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		logger.info("Fetching all departments- controller");

		departmentService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/department/{id}")
	private ResponseEntity<Department> getParticularDepartment(@PathVariable("id") Long id) {
		logger.info("Getting employee by id- controller: {}");
		Department department = departmentService.getDepartmentById(id);
		if (department != null) {
			logger.info("department found");
			return ResponseEntity.ok(department);
		} else {
			logger.info("department not found");
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/department/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
			@Valid @RequestBody Department departmentDetails) {
		logger.info("Updating employee by id- controller: {}");
		Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
		return ResponseEntity.ok(updatedDepartment);
	}

}
