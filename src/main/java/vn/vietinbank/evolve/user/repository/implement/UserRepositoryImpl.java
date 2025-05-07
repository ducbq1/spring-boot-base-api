package vn.vietinbank.evolve.user.repository.implement;

import vn.vietinbank.evolve.user.domain.User;
import vn.vietinbank.evolve.user.repository.UserRepository;
import vn.vietinbank.evolve.user.repository.mapper.DynamicRowMapper;
import vn.vietinbank.evolve.user.repository.mapper.GenericRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getDynamicResults(String sql) {
        return jdbcTemplate.query(sql, new DynamicRowMapper());
    }

    public List<User> getUsers() {
        String sql = "SELECT id, name, email FROM users";
        return jdbcTemplate.query(sql, new GenericRowMapper<>(User.class));
    }

    public List<User> findAll() {
        String sql = "SELECT id, name, email FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(Long id) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public int insert(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }

    public int update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId());
    }

    public int patch(Long id, Map<String, Object> updates) {
        StringBuilder sql = new StringBuilder("UPDATE users SET ");
        List<Object> params = new ArrayList<>();
        updates.forEach((k, v) -> {
            sql.append(k).append(" = ?, ");
            params.add(v);
        });
        sql.setLength(sql.length() - 2); // remove last comma
        sql.append(" WHERE id = ?");
        params.add(id);
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
