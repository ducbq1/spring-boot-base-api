
package vn.vietinbank.evolve.employee;

import vn.vietinbank.evolve.base.BaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.vietinbank.evolve.common.AuditLog;

import java.util.List;

@Repository
public class EmployeeRepository extends BaseRepository {

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @AuditLog(action = "FIND_ALL")
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee",
            (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("name")));
    }
}
