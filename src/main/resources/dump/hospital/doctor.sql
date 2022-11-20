create table hospital.doctor
(
    id                bigint not null
        primary key,
    mail              varchar(255),
    name              varchar(255),
    number            varchar(255),
    seniority         varchar(255),
    surname           varchar(255),
    specialisation_id bigint
        constraint fkchl95te4sploin0p9dyogfref
            references hospital.specialisation
);

alter table hospital.doctor
    owner to postgres;

