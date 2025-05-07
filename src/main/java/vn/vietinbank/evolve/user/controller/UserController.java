package vn.vietinbank.evolve.user.controller;


import vn.vietinbank.evolve.dto.response.ApiResponse;
import vn.vietinbank.evolve.user.domain.User;
import vn.vietinbank.evolve.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> createUser(@PathVariable String userId) {
        userService.createUser(userId);
        return ResponseEntity.ok(ApiResponse.success("User created: " + userId));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok("Updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity patch(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        userService.pathUser(id, updates);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted");
    }
}
