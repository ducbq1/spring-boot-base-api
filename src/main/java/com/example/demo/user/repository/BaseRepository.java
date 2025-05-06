package com.example.demo.user.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BaseRepository {

    private final JdbcTemplate jdbcTemplate;

    public BaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <T> List<T> findAll(String sql, Class<T> clazz) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
    }

    public <T> T findOne(String sql, Object[] args, Class<T> clazz) {
        return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<>(clazz));
    }

    public int executeUpdate(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    // ✅ Thêm hàm insert và lấy ID được sinh ra
    public Number insertAndReturnKey(String sql, Object... params) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    for (int i = 0; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);
                    }
                    return ps;
                }
            },
            keyHolder
        );

        return keyHolder.getKey(); // Trả về ID mới được sinh ra
    }
}
