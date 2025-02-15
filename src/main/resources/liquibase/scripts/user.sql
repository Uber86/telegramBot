-- liquibase formatted sql

-- changeset ssostrovsky:1
CREATE TABLE notification_task (
    id SERIAL PRIMARY KEY,
    chat_id varchar(255),
    notification TEXT,
    data_time TIMESTAMP
)