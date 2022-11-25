INSERT INTO member (id, name, created_at) VALUES (default, 'test', NOW());

INSERT INTO city (id, name, created_at) VALUES (default, 'Seoul', DATE_SUB(NOW(), INTERVAL 10 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Busan', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Gwangju', DATE_SUB(NOW(), INTERVAL 10 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Daegu', NOW()); -- 4
INSERT INTO city (id, name, created_at) VALUES (default, 'Daejeon', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Gangneung', NOW()); -- 6
INSERT INTO city (id, name, created_at) VALUES (default, 'Cheonan', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Jeju', NOW());  -- 8
INSERT INTO city (id, name, created_at) VALUES (default, 'Shanhai', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'London', NOW());    -- 10
INSERT INTO city (id, name, created_at) VALUES (default, 'NewYork', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Guam', NOW());  -- 12
INSERT INTO city (id, name, created_at) VALUES (default, 'Paris', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Bangkok', DATE_SUB(NOW(), INTERVAL 10 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Hanoi', DATE_SUB(NOW(), INTERVAL 10 DAY));
INSERT INTO city (id, name, created_at) VALUES (default, 'Sydney', DATE_SUB(NOW(), INTERVAL 10 DAY));

INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 16, 1, DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY), NOW());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 1, 1, DATE_SUB(NOW(), INTERVAL 4 DAY), DATE_ADD(NOW(), INTERVAL 2 DAY), NOW());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 2, 1, DATE_ADD(NOW(), INTERVAL 1 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), NOW());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 3, 1, DATE_ADD(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), NOW());

INSERT INTO city_view_history (id, city_id, member_id, created_at) VALUES (default, 16, 1, DATE_SUB(NOW(), INTERVAL 7 DAY));
INSERT INTO city_view_history (id, city_id, member_id, created_at) VALUES (default, 1, 1, DATE_SUB(NOW(), INTERVAL 6 DAY));
INSERT INTO city_view_history (id, city_id, member_id, created_at) VALUES (default, 11, 1, NOW());