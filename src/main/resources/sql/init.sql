CREATE TABLE member
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    account    VARCHAR(100) NOT NULL,
    password   VARCHAR(100) NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE activity
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL,
    reward_point INT          NOT NULL,
    start_time   DATETIME     NOT NULL,
    end_time     DATETIME     NOT NULL
);

CREATE TABLE point_record
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id   BIGINT                   NOT NULL,
    activity_id BIGINT                   NOT NULL,
    type        ENUM ('ADD', 'SUBTRACT') NOT NULL,
    points      INT                      NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (activity_id) REFERENCES activity (id)
);



CREATE TABLE activity_log
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id   BIGINT NOT NULL,
    activity_id BIGINT NOT NULL,
    joined_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (activity_id) REFERENCES activity (id)
);

CREATE TABLE verification_code
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    code       VARCHAR(255),
    created_at DATETIME(6),
    email      VARCHAR(255)
);
