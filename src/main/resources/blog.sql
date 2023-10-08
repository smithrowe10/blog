DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user`
(
    `id`         bigint(20)                                                   NOT NULL AUTO_INCREMENT,
    `phone`      varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `username`   varchar(64)  DEFAULT NULL,
    `password`   varchar(64)  DEFAULT NULL,
    `avatar`     varchar(255) DEFAULT NULL,
    `email`      varchar(64)  DEFAULT NULL,
    `status`     int(5)                                                       NOT NULL,
    `created`    datetime     DEFAULT NULL,
    `last_login` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniqe_key_phone` (`phone`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `m_blog`;
CREATE TABLE `m_blog`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `user_id`     bigint(20)   NOT NULL,
    `title`       varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `content`     longtext,
    `created`     datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `status`      tinyint(4) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `blog`.`m_user` (`id`, `username`, `phone`, `avatar`, `email`, `password`, `status`, `created`,
                             `last_login`)
VALUES ('1', 'ming', '13734650579',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        NULL, 'e10adc3949ba59abbe56e057f20f883e', '0', '2023-10-04 11:52:01', NULL);