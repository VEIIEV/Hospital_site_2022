create table hospital.diagnosis
(
    id                   bigint not null
        primary key,
    name                 varchar(255),
    prescribed_treatment varchar(255),
    patient_id           bigint
        constraint fkp8tgyroh9ehqikufxe905q0xs
            references hospital.patient,
    assign_date          varchar(255)
);

alter table hospital.diagnosis
    owner to postgres;

