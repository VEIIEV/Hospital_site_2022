create table hospital.patient
(
    id          bigint not null
        primary key,
    mail        varchar(255),
    name        varchar(255),
    number      varchar(255),
    residence   varchar(255),
    surname     varchar(255),
    hospital_id bigint
        constraint fkfrtkp1fawf55kilsxb1uxpio0
            references hospital.hospital
);

alter table hospital.patient
    owner to postgres;

