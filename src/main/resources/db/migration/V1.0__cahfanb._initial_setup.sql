create table afiliados
(
    id                bigserial  primary key,
    nombre             varchar(50) null,
    apellido            varchar(50) null,
    email               varchar(50) null,
    create_at           Date

);


create table roles
(
    id                bigserial
        primary key,
    nombre_rol             varchar(20) null

);
create table usuarios
(
    id                bigserial
        primary key,
    nombres             varchar(20) null,
    apellidos             varchar(20) null,
    nombre_usuario             varchar(20) null,
    clave             varchar(70) null,
    grupo             varchar(20) null,
    correo             varchar(50) null,
    fecha_creacion      Date null,
    fecha_modificacion  Date null,
    locked              boolean null,
    enabled             boolean null
);

create table usuarios_roles
(
    id                bigserial
        primary key,
    rol_id       bigint not null ,
    usuario_id bigint not null
);


