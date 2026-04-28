package com.gying.wander.repository;

import com.gying.wander.model.entity.AnnouncementEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementRepository {
    private final JdbcTemplate jdbcTemplate;

    public AnnouncementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AnnouncementEntity> listActive() {
        String sql = "select id, title, content, active, play_seconds, sort_no, create_time from t_announcement where active = 1 order by sort_no asc, id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<AnnouncementEntity>(AnnouncementEntity.class));
    }

    public List<AnnouncementEntity> listAll() {
        String sql = "select id, title, content, active, play_seconds, sort_no, create_time from t_announcement order by sort_no asc, id desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<AnnouncementEntity>(AnnouncementEntity.class));
    }

    public int create(String title, String content, Integer active, Integer playSeconds, Integer sortNo) {
        String sql = "insert into t_announcement (title, content, active, play_seconds, sort_no) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, title, content, active, playSeconds, sortNo);
    }
}
