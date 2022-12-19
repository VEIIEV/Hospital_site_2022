create table hospital.reception_hour
(
    id        bigint not null
        primary key,
    date_time timestamp,
    status    integer,
    doctor_id bigint
        constraint fkkr2tm7u5hkuqj3r7vme6ibm22
            references hospital.doctor
);

alter table hospital.reception_hour
    owner to postgres;

