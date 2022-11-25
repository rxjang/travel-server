INSERT INTO member (id, name, created_at) VALUES (default, 'test', CURRENT_TIMESTAMP());

INSERT INTO city (id, name, created_at) VALUES (default, 'Seoul', DATEADD('DAY', -10, CURRENT_TIMESTAMP())); -- 1
INSERT INTO city (id, name, created_at) VALUES (default, 'Busan', DATEADD('DAY', -2, CURRENT_TIMESTAMP()));   -- 2
INSERT INTO city (id, name, created_at) VALUES (default, 'Gwangju', DATEADD('DAY', -10, CURRENT_TIMESTAMP())); -- 3
INSERT INTO city (id, name, created_at) VALUES (default, 'Daegu', CURRENT_TIMESTAMP()); -- 4
INSERT INTO city (id, name, created_at) VALUES (default, 'Daejeon', DATEADD('DAY', -2, CURRENT_TIMESTAMP()));  -- 5
INSERT INTO city (id, name, created_at) VALUES (default, 'Gangneung', CURRENT_TIMESTAMP()); -- 6
INSERT INTO city (id, name, created_at) VALUES (default, 'Cheonan', DATEADD('DAY', -2, CURRENT_TIMESTAMP()));   -- 7
INSERT INTO city (id, name, created_at) VALUES (default, 'Jeju', CURRENT_TIMESTAMP());  -- 8
INSERT INTO city (id, name, created_at) VALUES (default, 'Shanhai', DATEADD('DAY', -2, CURRENT_TIMESTAMP()));   -- 9
INSERT INTO city (id, name, created_at) VALUES (default, 'London', CURRENT_TIMESTAMP());    -- 10
INSERT INTO city (id, name, created_at) VALUES (default, 'NewYork', DATEADD('DAY', -2, CURRENT_TIMESTAMP()));   -- 11
INSERT INTO city (id, name, created_at) VALUES (default, 'Guam', CURRENT_TIMESTAMP());  -- 12
INSERT INTO city (id, name, created_at) VALUES (default, 'Paris', DATEADD('DAY', -2, CURRENT_TIMESTAMP())); -- 13
INSERT INTO city (id, name, created_at) VALUES (default, 'Bangkok', DATEADD('DAY', -10, CURRENT_TIMESTAMP()));   -- 14
INSERT INTO city (id, name, created_at) VALUES (default, 'Hanoi', DATEADD('DAY', -10, CURRENT_TIMESTAMP())); -- 15
INSERT INTO city (id, name, created_at) VALUES (default, 'Sydney', DATEADD('DAY', -10, CURRENT_TIMESTAMP()));    -- 16

INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 16, 1, DATEADD('DAY', -10, CURRENT_DATE), DATEADD('DAY', -3, CURRENT_DATE), CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 1, 1, DATEADD('DAY', -4, CURRENT_DATE), DATEADD('DAY', 2, CURRENT_DATE), CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 2, 1, DATEADD('DAY', 1, CURRENT_DATE), DATEADD('DAY', 3, CURRENT_DATE), CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 3, 1, DATEADD('DAY', 3, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), CURRENT_TIMESTAMP());

INSERT INTO city_view_history (id, city_id, member_id, created_at) VALUES (default, 16, 1, DATEADD('DAY', -7, CURRENT_TIMESTAMP()));
INSERT INTO city_view_history (id, city_id, member_id, created_at) VALUES (default, 1, 1, DATEADD('DAY', -6, CURRENT_TIMESTAMP()));
INSERT INTO city_view_history (id, city_id, member_id, created_at) VALUES (default, 11, 1, CURRENT_TIMESTAMP());
