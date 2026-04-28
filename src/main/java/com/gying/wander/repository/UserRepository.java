package com.gying.wander.repository;

import com.gying.wander.model.entity.UserEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity findByUsername(String username) {
        String sql = "select id, username, password, nickname from t_user where username = ?";
        List<UserEntity> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserEntity>(UserEntity.class), username);
        return users.isEmpty() ? null : users.get(0);
    }
}
