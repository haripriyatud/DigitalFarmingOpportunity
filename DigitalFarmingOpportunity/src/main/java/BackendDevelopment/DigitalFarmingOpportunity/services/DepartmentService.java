package BackendDevelopment.DigitalFarmingOpportunity.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendDevelopment.DigitalFarmingOpportunity.Repository.DepartmentRepository;
import BackendDevelopment.DigitalFarmingOpportunity.exceptionalhandler.ResourceNotFoundException;
import BackendDevelopment.DigitalFarmingOpportunity.models.Department;
import jakarta.validation.Valid;

@Service
public class DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public Department createDepartment(@Valid Department department) {
		logger.info("storing departments- service");
		return departmentRepository.save(department);
	}

	public List<Department> getAllDepartments() {
		logger.info("Fetching all departments- service");
		return departmentRepository.findAll();

	}

	public Department getDepartmentById(Long id) {
		logger.info("Fetching department by id- service");
		return departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
	}

	public void deleteDepartment(Long id) {
		logger.info("Deleting employee with id: {} - service", id);
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
		departmentRepository.delete(department);
	}

	public Department updateDepartment(Long id, @Valid Department departmentDetails) {
		logger.info("Updating employee with id: {} - service", id);
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
		department.setName(departmentDetails.getName());
		return departmentRepository.save(department);
	}

}
