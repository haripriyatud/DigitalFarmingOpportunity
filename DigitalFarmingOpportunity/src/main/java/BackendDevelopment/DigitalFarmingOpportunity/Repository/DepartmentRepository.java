package BackendDevelopment.DigitalFarmingOpportunity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendDevelopment.DigitalFarmingOpportunity.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long> {

}
