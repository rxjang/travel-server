INSERT INTO member (id, name, created_at) VALUES (default, 'test', CURRENT_TIMESTAMP());

INSERT INTO city (id, name, created_at) VALUES (default, 'Seoul', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Busan', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Gwangju', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Daegu', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Daejeon', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Gangneung', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Cheonan', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Jeju', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Shanhai', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'London', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'NewYork', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Guam', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Paris', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Bangkok', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Hanoi', CURRENT_TIMESTAMP());
INSERT INTO city (id, name, created_at) VALUES (default, 'Sydney', CURRENT_TIMESTAMP());

INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 1, 1, DATEADD('DAY', -1, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 16, 1, DATEADD('DAY', -3, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 14, 1, DATEADD('DAY', 1, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), CURRENT_TIMESTAMP());
INSERT INTO travel (id, city_id, member_id, start_date, end_date, created_at) VALUES (default, 3, 1, DATEADD('DAY', 3, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), CURRENT_TIMESTAMP());