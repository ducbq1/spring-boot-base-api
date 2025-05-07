
package vn.vietinbank.evolve.employee;

import vn.vietinbank.evolve.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends BaseService<EmployeeRepository> {

    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }

    public java.util.List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
