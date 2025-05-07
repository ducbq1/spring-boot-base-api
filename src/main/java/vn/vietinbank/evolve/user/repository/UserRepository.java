package vn.vietinbank.evolve.user.repository;

import vn.vietinbank.evolve.user.domain.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    List<Map<String, Object>> getDynamicResults(String sql);

    List<User> getUsers();

    List<User> findAll();

    User findById(Long id);

    int insert(User user);

    int update(User user);

    int patch(Long id, Map<String, Object> updates);

    int delete(Long id);
}
