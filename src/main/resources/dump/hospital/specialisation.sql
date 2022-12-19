create table hospital.specialisation
(
    id          bigint not null
        primary key,
    name        varchar(255),
    hospital_id bigint
        constraint fkfpntb1x7m7cteomm1plc7jofj
            references hospital.hospital
);

alter table hospital.specialisation
    owner to postgres;

