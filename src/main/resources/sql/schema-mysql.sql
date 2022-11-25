ALTER TABLE city_view_history DROP FOREIGN KEY FK_CITY_VIEW_HISTORY_MEMBER_ID;
ALTER TABLE city_view_history DROP FOREIGN KEY FK_CITY_VIEW_HISTORY_CITY_ID;
ALTER TABLE travel DROP FOREIGN KEY FK_TRAVEL_CITY_ID;
ALTER TABLE travel DROP FOREIGN KEY FK_TRAVEL_MEMBER_ID;

DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS city_view_history;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS travel;

CREATE TABLE city (
    id bigint NOT NULL auto_increment,
    name varchar(255) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6),
    PRIMARY KEY (id)
) engine=InnoDB;
CREATE TABLE city_view_history (
    id bigint NOT NULL auto_increment,
    city_id bigint NOT NULL, 
    member_id bigint NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6),
    PRIMARY KEY (id)
) engine=InnoDB;
CREATE TABLE member (
    id bigint NOT NULL auto_increment,
    name varchar(255) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6),
    PRIMARY KEY (id)
) engine=InnoDB;
CREATE TABLE travel (
    id bigint NOT NULL auto_increment,
    city_id bigint NOT NULL,
    member_id bigint NOT NULL,
    start_date datetime(6) NOT NULL,
    end_date datetime(6) NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6),
    PRIMARY KEY (id)
) engine=InnoDB;
ALTER TABLE city_view_history ADD CONSTRAINT FK_CITY_VIEW_HISTORY_MEMBER_ID FOREIGN KEY (city_id) REFERENCES city (id);
ALTER TABLE city_view_history ADD CONSTRAINT FK_CITY_VIEW_HISTORY_CITY_ID FOREIGN KEY (member_id) REFERENCES member (id);
ALTER TABLE travel ADD CONSTRAINT FK_TRAVEL_CITY_ID FOREIGN KEY (city_id) REFERENCES city (id);
ALTER TABLE travel ADD CONSTRAINT FK_TRAVEL_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);