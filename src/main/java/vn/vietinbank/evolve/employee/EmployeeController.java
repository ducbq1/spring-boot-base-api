
package vn.vietinbank.evolve.employee;

import vn.vietinbank.evolve.base.BaseController;
import vn.vietinbank.evolve.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseController<EmployeeService> {

    public EmployeeController(EmployeeService service) {
        super(service);
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách ví dụ", description = "Lấy danh sách ví dụ từ server")
    public ApiResponse<List<Employee>> getAll() {
        return ApiResponse.success(service.getAllEmployees());
    }
}
