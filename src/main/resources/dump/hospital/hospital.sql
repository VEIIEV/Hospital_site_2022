create table hospital.hospital
(
    id      bigint not null
        primary key,
    address varchar(255),
    mail    varchar(255),
    name    varchar(255),
    number  varchar(255)
);

alter table hospital.hospital
    owner to postgres;

