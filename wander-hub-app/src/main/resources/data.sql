INSERT INTO t_user (username, password, nickname, role_code, permissions)
SELECT 'admin', '123456', '管理员', 'ADMIN', 'USER_MANAGE,AI_CHAT,ANNOUNCEMENT_MANAGE,CHANGELOG_MANAGE'
WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'admin');

INSERT INTO t_user (username, password, nickname, role_code, permissions)
SELECT 'editor', '123456', '编辑用户', 'USER', 'AI_CHAT'
WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'editor');

INSERT INTO t_user (username, password, nickname, role_code, permissions)
SELECT 'viewer', '123456', '访客用户', 'VIEWER', 'AI_CHAT'
WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'viewer');

INSERT INTO t_announcement (title, content, active, pinned, play_seconds, expire_time, sort_no)
SELECT '欢迎使用 Wander', '系统已支持角色权限、公告轮播、改动记录。', 1, 1, 6, DATE_ADD(NOW(), INTERVAL 365 DAY), 10
WHERE NOT EXISTS (SELECT 1 FROM t_announcement WHERE title = '欢迎使用 Wander');

INSERT INTO t_change_log (version_no, module_name, change_point, change_file, git_commit)
SELECT 'v1.1.0', 'wander-hub-auth', '新增角色和权限字段', 'wander-hub-app/src/main/resources/schema.sql', 'seed'
WHERE NOT EXISTS (SELECT 1 FROM t_change_log WHERE version_no = 'v1.1.0' AND module_name = 'wander-hub-auth');
