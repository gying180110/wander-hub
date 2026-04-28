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
        String sql = "select id, username, password, nickname, role_code, permissions from t_user where username = ?";
        List<UserEntity> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserEntity>(UserEntity.class), username);
        return users.isEmpty() ? null : users.get(0);
    }

    public UserEntity findById(Long userId) {
        String sql = "select id, username, password, nickname, role_code, permissions from t_user where id = ?";
        List<UserEntity> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserEntity>(UserEntity.class), userId);
        return users.isEmpty() ? null : users.get(0);
    }

    public List<UserEntity> listAll() {
        String sql = "select id, username, password, nickname, role_code, permissions from t_user order by id asc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserEntity>(UserEntity.class));
    }

    public int createUser(String username, String password, String nickname, String roleCode, String permissions) {
        String sql = "insert into t_user (username, password, nickname, role_code, permissions) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, username, password, nickname, roleCode, permissions);
    }
}
