package BackendDevelopment.DigitalFarmingOpportunity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendDevelopment.DigitalFarmingOpportunity.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
