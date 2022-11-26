CREATE SCHEMA travel_ex;
CREATE USER 'travel_test'@'%' identified BY 'travel_test';
GRANT ALL PRIVILEGES ON travel_ex.* TO 'travel_test'@'*';