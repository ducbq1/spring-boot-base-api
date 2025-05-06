
package com.example.demo.employee;

import com.example.demo.base.BaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository extends BaseRepository {

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee",
            (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("name")));
    }
}
