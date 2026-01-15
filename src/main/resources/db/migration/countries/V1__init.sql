create table if not exists "countries"

(
    code CHAR(2) primary key,
    name varchar(255) not null
);

alter table "countries"
    owner to postgres;

INSERT INTO countries (code, name) VALUES
('FJ', 'Fiji'),
('IN', 'India'),
('BD', 'Bangladesh'),
('BT', 'Bhutan'),
('NP', 'Nepal'),
('AM', 'Armenia'),
('SE', 'Sweden');