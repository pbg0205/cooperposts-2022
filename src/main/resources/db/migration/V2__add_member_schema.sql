-- 2023.10.03

CREATE TABLE tb_member
(
    id       VARCHAR(36)  NOT NULL,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_tb_member PRIMARY KEY (id)
);
