INSERT INTO t_user (username, password, nickname)
SELECT 'admin', '123456', '管理员'
WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'admin');
