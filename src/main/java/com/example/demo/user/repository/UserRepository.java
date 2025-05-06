package com.example.demo.user.repository;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.mapper.DynamicRowMapper;
import com.example.demo.user.repository.mapper.GenericRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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
