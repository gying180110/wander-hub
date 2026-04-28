package com.gying.wander.repository;

import com.gying.wander.model.entity.NoteEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public NoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Long userId, String title, String content) {
        String sql = "insert into t_note (user_id, title, content) values (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, title, content);
    }

    public List<NoteEntity> listByUserId(Long userId) {
        String sql = "select id, user_id, title, content, create_time, update_time from t_note where user_id = ? order by id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<NoteEntity>(NoteEntity.class), userId);
    }
}
