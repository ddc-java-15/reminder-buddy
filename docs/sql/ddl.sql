CREATE TABLE IF NOT EXISTS `user`
(
    `user_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth_key` TEXT,
    `age`       INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_user_oauth_key` ON `user` (`oauth_key`);

CREATE TABLE IF NOT EXISTS `notification`
(
    `notification_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `card_id`         INTEGER                           NOT NULL,
    `frequency`       INTEGER,
    `soundbite`       TEXT,
    `hint`            TEXT,
    FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`) ON UPDATE NO ACTION ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS `index_notification_card_id` ON `notification` (`card_id`);

CREATE TABLE IF NOT EXISTS `card`
(
    `card_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `user_id`     INTEGER                           NOT NULL,
    `created`     INTEGER                           NOT NULL,
    `information` TEXT,
    `date`        INTEGER                           NOT NULL,
    `location`    TEXT,
    `sound_bite`  TEXT,
    `hint`        TEXT,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_card_user_id` ON `card` (`user_id`);

CREATE TABLE IF NOT EXISTS `response`
(
    `response_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `user_response`   TEXT,
    `correct`         INTEGER                           NOT NULL,
    `next_reminder`   INTEGER,
    `card_id`         INTEGER                           NOT NULL,
    `notification_id` INTEGER,
    FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`) ON UPDATE NO ACTION ON DELETE RESTRICT,
    FOREIGN KEY (`notification_id`) REFERENCES `notification` (`notification_id`) ON UPDATE NO ACTION ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS `index_response_card_id` ON `response` (`card_id`);

CREATE INDEX IF NOT EXISTS `index_response_notification_id` ON `response` (`notification_id`);

CREATE VIEW `response_summary` AS
SELECT u.user_id, r.correct, COUNT(*) AS count
FROM response AS r
         JOIN card AS c ON c.card_id = r.card_id
         JOIN user AS u ON u.user_id = c.user_id
GROUP BY u.user_id, r.correct;

