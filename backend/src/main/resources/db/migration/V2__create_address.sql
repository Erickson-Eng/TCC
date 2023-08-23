CREATE TABLE if not exists public.locale
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    street     VARCHAR(255)                            NOT NULL,
    complement VARCHAR(255),
    number     VARCHAR(255)                            NOT NULL,
    city       VARCHAR(255)                            NOT NULL,
    state      VARCHAR(255)                            NOT NULL,
    zip_code   VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_locale PRIMARY KEY (id)
);