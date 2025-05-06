
package com.example.demo.employee;

import com.example.demo.base.BaseController;
import com.example.demo.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseController<EmployeeService> {

    public EmployeeController(EmployeeService service) {
        super(service);
    }

    @GetMapping
    public ApiResponse<List<Employee>> getAll() {
        return ApiResponse.success(service.getAllEmployees());
    }
}
