package BackendDevelopment.DigitalFarmingOpportunity.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.SmartValidator;

import BackendDevelopment.DigitalFarmingOpportunity.Repository.DepartmentRepository;
import BackendDevelopment.DigitalFarmingOpportunity.exceptionalhandler.ResourceNotFoundException;
import BackendDevelopment.DigitalFarmingOpportunity.models.Department;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {
	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentService departmentService;

	@Mock
	private SmartValidator validator;

	@Test
	public void testGetAllDepartments() {
		List<Department> departments = Arrays.asList(new Department(), new Department());
		when(departmentRepository.findAll()).thenReturn(departments);

		List<Department> result = departmentService.getAllDepartments();

		assertEquals(2, result.size());
	}

	@Test
	public void testGetDepartmentById_ExistingId() {
		Long id = 1L;
		Department department = new Department();
		department.setId(id);
		when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

		Department result = departmentService.getDepartmentById(id);

		assertEquals(id, result.getId());
	}

	@Test
	public void testGetDepartmentById_NonExistingId() {
		Long id = 1L;
		when(departmentRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> departmentService.getDepartmentById(id));
	}

	@Test
	public void testDeleteDepartment_ExistingId() {
		Long id = 1L;
		Department department = new Department();
		department.setId(id);
		when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
		departmentService.deleteDepartment(id);

		verify(departmentRepository, times(1)).delete(department);
	}

	@Test
	public void testDeleteDepartment_NonExistingId() {
		Long id = 1L;
		when(departmentRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> departmentService.deleteDepartment(id));
		verify(departmentRepository, never()).delete(any());
	}

	@Test
	public void testCreateDepartment() {
		Department department = new Department();
		department.setId(1L);
		department.setName("Test Department");

		when(departmentRepository.save(department)).thenReturn(department);
		Department createdDepartment = departmentService.createDepartment(department);

		assertEquals(department.getId(), createdDepartment.getId());
		assertEquals(department.getName(), createdDepartment.getName());
		verify(departmentRepository, times(1)).save(department);
	}

}
