CREATE TABLE blog
(
    id           INT AUTO_INCREMENT,
    blog_user_id INT          NULL,
    title        VARCHAR(30)  NULL,
    at_index     BOOLEAN      NULL,
    description  VARCHAR(100) NULL,
    content      TEXT         NULL,
    update_at    TIMESTAMP    NOT NULL DEFAULT NOW(),
    create_at    TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT blog_pk
        PRIMARY KEY (id)
);

CREATE TABLE blog_user
(
    id         INT AUTO_INCREMENT,
    username   VARCHAR(15)  NULL,
    password   VARCHAR(40)  NULL,
    avatar     VARCHAR(200) NULL,
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT blog_user_pk
        PRIMARY KEY (id)
);

