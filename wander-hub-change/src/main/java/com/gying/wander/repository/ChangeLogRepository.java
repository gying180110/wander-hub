package com.gying.wander.repository;

import com.gying.wander.model.entity.ChangeLogEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChangeLogRepository {
    private final JdbcTemplate jdbcTemplate;

    public ChangeLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ChangeLogEntity> listLatest() {
        String sql = "select id, version_no, module_name, change_point, change_file, git_commit, create_time from t_change_log order by id desc limit 100";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ChangeLogEntity>(ChangeLogEntity.class));
    }

    public int create(String versionNo, String moduleName, String changePoint, String changeFile, String gitCommit) {
        String sql = "insert into t_change_log (version_no, module_name, change_point, change_file, git_commit) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, versionNo, moduleName, changePoint, changeFile, gitCommit);
    }
}
