INSERT INTO member (id, name, created_at) VALUES (default, 'test', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Seoul', CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 1, 1, DATEADD('DAY', -1, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), CURRENT_TIMESTAMP());