create schema if not exists tenant1;


create table if not exists tenant1.audit_revision_entity
(
    id         int4 not null,
    timestamp  int8 not null,
    ip_address varchar(255),
    username   varchar(255),
    primary key (id)
);
create table if not exists tenant1.patients
(
    id              uuid not null,
    activation_date timestamp,
    name            varchar(45),
    primary key (id)
);

create table if not exists tenant1.patients_aud
(
    id              uuid not null,
    rev             int4 not null,
    revtype         int2,
    activation_date timestamp,
    name            varchar(255),
    primary key (id, rev)
);

create table if not exists tenant1.results
(
    id                 uuid not null,
    creation_date_time timestamp,
    result             varchar(255),
    fk_sample          uuid not null,
    fk_test            uuid not null,
    primary key (id)
);

create table if not exists tenant1.results_aud
(
    id                 uuid not null,
    rev                int4 not null,
    revtype            int2,
    creation_date_time timestamp,
    result             varchar(255),
    fk_sample          uuid,
    fk_test            uuid,
    primary key (id, rev)
);

create table if not exists tenant1.samples
(
    id         uuid not null,
    barcode    varchar(45),
    fk_patient uuid not null,
    primary key (id)
);

create table if not exists tenant1.samples_aud
(
    id         uuid not null,
    rev        int4 not null,
    revtype    int2,
    barcode    varchar(255),
    fk_patient uuid,
    primary key (id, rev)
);

create table if not exists tenant1.tests
(
    id              uuid         not null,
    activation_date timestamp,
    code            varchar(15),
    loinc_code      varchar(15),
    short_name      varchar(255) not null,
    primary key (id)
);

create table if not exists tenant1.tests_aud
(
    id              uuid not null,
    rev             int4 not null,
    revtype         int2,
    activation_date timestamp,
    code            varchar(255),
    loinc_code      varchar(255),
    short_name      varchar(255),
    primary key (id, rev)
);

create table if not exists tenant1.users
(
    id            uuid         not null,
    user_login    varchar(10)  not null,
    user_name     varchar(255) not null,
    user_role     varchar(255) not null,
    user_surname  varchar(255) not null,
    primary key (id)
);

create table if not exists tenant1.users_aud
(
    id            uuid not null,
    rev           int4 not null,
    revtype       int2,
    user_login    varchar(10),
    user_name     varchar(255),
    user_role     varchar(255),
    user_surname  varchar(255),
    primary key (id, rev)
);

alter table if exists tenant1.tests
    drop constraint if exists UKed1p6b07h6woxy8i6e6aepwe4;

alter table if exists tenant1.tests
    add constraint UKed1p6b07h6woxy8i6e6aepwe4 unique (code);


alter table if exists tenant1.tests
drop constraint if exists UKdp8d54abdc90kq7w3dd52h9st;

alter table if exists tenant1.tests
    add constraint UKdp8d54abdc90kq7w3dd52h9st unique (loinc_code);


alter table if exists tenant1.users
drop constraint if exists UK_rumqsyi9rgrm5xg6j7nuo9923;

alter table if exists tenant1.users
    add constraint UK_rumqsyi9rgrm5xg6j7nuo9923 unique (user_login);


alter table if exists tenant1.patients_aud
drop constraint if exists FKfv28ywwspoxnfpl0d9te6seg8;

alter table if exists tenant1.patients_aud
    add constraint FKfv28ywwspoxnfpl0d9te6seg8
    foreign key (rev)
    references tenant1.audit_revision_entity;


alter table if exists tenant1.results
drop constraint if exists FKsenl861m6qsp2uiaiyi2y76is;

alter table if exists tenant1.results
    add constraint FKsenl861m6qsp2uiaiyi2y76is
    foreign key (fk_sample)
    references tenant1.samples;


alter table if exists tenant1.results
drop constraint if exists FKcwf0umaa3q48pum0ct02cp952;

alter table if exists tenant1.results
    add constraint FKcwf0umaa3q48pum0ct02cp952
    foreign key (fk_test)
    references tenant1.tests;


alter table if exists tenant1.results_aud
drop constraint if exists FKr31ru05cj97rgxe06096ecojc;

alter table if exists tenant1.results_aud
    add constraint FKr31ru05cj97rgxe06096ecojc
    foreign key (rev)
    references tenant1.audit_revision_entity;


alter table if exists tenant1.samples
drop constraint if exists FKod5cbokli6tjea1gbw02fo90g;

alter table if exists tenant1.samples
    add constraint FKod5cbokli6tjea1gbw02fo90g
    foreign key (fk_patient)
    references tenant1.patients;

alter table if exists tenant1.samples_aud
drop constraint if exists FKblvdhcl1pkqo3v0ofhm1fpikt;

alter table if exists tenant1.samples_aud
    add constraint FKblvdhcl1pkqo3v0ofhm1fpikt
    foreign key (rev)
    references tenant1.audit_revision_entity;


alter table if exists tenant1.tests_aud
drop constraint if exists FK5tfv61n1g18fnsoil76ul0iw;

alter table if exists tenant1.tests_aud
    add constraint FK5tfv61n1g18fnsoil76ul0iw
    foreign key (rev)
    references tenant1.audit_revision_entity;

alter table if exists tenant1.users_aud
drop constraint if exists FKld7cdnhid45yc6535cecshyop;

alter table if exists tenant1.users_aud
    add constraint FKld7cdnhid45yc6535cecshyop
    foreign key (rev)
    references tenant1.audit_revision_entity;






create schema if not exists tenant2;


create table if not exists tenant2.audit_revision_entity
(
    id         int4 not null,
    timestamp  int8 not null,
    ip_address varchar(255),
    username   varchar(255),
    primary key (id)
);
create table if not exists tenant2.patients
(
    id              uuid not null,
    activation_date timestamp,
    name            varchar(45),
    primary key (id)
);

create table if not exists tenant2.patients_aud
(
    id              uuid not null,
    rev             int4 not null,
    revtype         int2,
    activation_date timestamp,
    name            varchar(255),
    primary key (id, rev)
);

create table if not exists tenant2.results
(
    id                 uuid not null,
    creation_date_time timestamp,
    result             varchar(255),
    fk_sample          uuid not null,
    fk_test            uuid not null,
    primary key (id)
);

create table if not exists tenant2.results_aud
(
    id                 uuid not null,
    rev                int4 not null,
    revtype            int2,
    creation_date_time timestamp,
    result             varchar(255),
    fk_sample          uuid,
    fk_test            uuid,
    primary key (id, rev)
);

create table if not exists tenant2.samples
(
    id         uuid not null,
    barcode    varchar(45),
    fk_patient uuid not null,
    primary key (id)
);

create table if not exists tenant2.samples_aud
(
    id         uuid not null,
    rev        int4 not null,
    revtype    int2,
    barcode    varchar(255),
    fk_patient uuid,
    primary key (id, rev)
);

create table if not exists tenant2.tests
(
    id              uuid         not null,
    activation_date timestamp,
    code            varchar(15),
    loinc_code      varchar(15),
    short_name      varchar(255) not null,
    primary key (id)
);

create table if not exists tenant2.tests_aud
(
    id              uuid not null,
    rev             int4 not null,
    revtype         int2,
    activation_date timestamp,
    code            varchar(255),
    loinc_code      varchar(255),
    short_name      varchar(255),
    primary key (id, rev)
);

create table if not exists tenant2.users
(
    id            uuid         not null,
    user_login    varchar(10)  not null,
    user_name     varchar(255) not null,
    user_role     varchar(255) not null,
    user_surname  varchar(255) not null,
    primary key (id)
);

create table if not exists tenant2.users_aud
(
    id            uuid not null,
    rev           int4 not null,
    revtype       int2,
    user_login    varchar(10),
    user_name     varchar(255),
    user_role     varchar(255),
    user_surname  varchar(255),
    primary key (id, rev)
);

alter table if exists tenant2.tests
drop constraint if exists UKed1p6b07h6woxy8i6e6aepwe4;

alter table if exists tenant2.tests
    add constraint UKed1p6b07h6woxy8i6e6aepwe4 unique (code);


alter table if exists tenant2.tests
drop constraint if exists UKdp8d54abdc90kq7w3dd52h9st;

alter table if exists tenant2.tests
    add constraint UKdp8d54abdc90kq7w3dd52h9st unique (loinc_code);


alter table if exists tenant2.users
drop constraint if exists UK_rumqsyi9rgrm5xg6j7nuo9923;

alter table if exists tenant2.users
    add constraint UK_rumqsyi9rgrm5xg6j7nuo9923 unique (user_login);


alter table if exists tenant2.patients_aud
drop constraint if exists FKfv28ywwspoxnfpl0d9te6seg8;

alter table if exists tenant2.patients_aud
    add constraint FKfv28ywwspoxnfpl0d9te6seg8
    foreign key (rev)
    references tenant2.audit_revision_entity;


alter table if exists tenant2.results
drop constraint if exists FKsenl861m6qsp2uiaiyi2y76is;

alter table if exists tenant2.results
    add constraint FKsenl861m6qsp2uiaiyi2y76is
    foreign key (fk_sample)
    references tenant2.samples;


alter table if exists tenant2.results
drop constraint if exists FKcwf0umaa3q48pum0ct02cp952;

alter table if exists tenant2.results
    add constraint FKcwf0umaa3q48pum0ct02cp952
    foreign key (fk_test)
    references tenant2.tests;


alter table if exists tenant2.results_aud
drop constraint if exists FKr31ru05cj97rgxe06096ecojc;

alter table if exists tenant2.results_aud
    add constraint FKr31ru05cj97rgxe06096ecojc
    foreign key (rev)
    references tenant2.audit_revision_entity;


alter table if exists tenant2.samples
drop constraint if exists FKod5cbokli6tjea1gbw02fo90g;

alter table if exists tenant2.samples
    add constraint FKod5cbokli6tjea1gbw02fo90g
    foreign key (fk_patient)
    references tenant2.patients;


alter table if exists tenant2.samples_aud
drop constraint if exists FKblvdhcl1pkqo3v0ofhm1fpikt;

alter table if exists tenant2.samples_aud
    add constraint FKblvdhcl1pkqo3v0ofhm1fpikt
    foreign key (rev)
    references tenant2.audit_revision_entity;


alter table if exists tenant2.tests_aud
drop constraint if exists FK5tfv61n1g18fnsoil76ul0iw;

alter table if exists tenant2.tests_aud
    add constraint FK5tfv61n1g18fnsoil76ul0iw
    foreign key (rev)
    references tenant2.audit_revision_entity;


alter table if exists tenant2.users_aud
drop constraint if exists FKld7cdnhid45yc6535cecshyop;

alter table if exists tenant2.users_aud
    add constraint FKld7cdnhid45yc6535cecshyop
    foreign key (rev)
    references tenant2.audit_revision_entity;