package BackendDevelopment.DigitalFarmingOpportunity.controllers;

import BackendDevelopment.DigitalFarmingOpportunity.models.Employee;
import BackendDevelopment.DigitalFarmingOpportunity.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testCreateEmployeeSuccess() throws Exception {
        Employee employee = new Employee();
        when(employeeService.createEmployee(employee)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                		+ "    \"name\":\"employeename\",\n"
                		+ "    \"department\":{\n"
                		+ "    \"id\":1\n"
                		+ "    \n"
                		+ "}\n"
                		+ "    \n"
                		+ "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    
    @Test
    void testCreateEmployeeFailure() throws Exception {
        Employee employee = new Employee();
        when(employeeService.createEmployee(employee)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    
    @Test
    void testGetAllEmployees() throws Exception {
        // Arrange
        Employee employee = new Employee();
        when(employeeService.getAllEmployees()).thenReturn(Collections.singletonList(employee));
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetParticularEmployee() throws Exception {
        Long id = 1L;
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(id)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void testGetParticularEmployee_NotFound() throws Exception {
        // Arrange
        Long id = 1L;
        when(employeeService.getEmployeeById(id)).thenReturn(null);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    @Test
    void testDeleteEmployee() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
