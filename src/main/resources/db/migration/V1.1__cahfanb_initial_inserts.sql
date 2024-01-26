/* Populate tables */
INSERT INTO roles (nombre_rol) VALUES ('ROLE_ADMIN'),
                                      ('ROLE_USER');
INSERT INTO usuarios(nombres,apellidos,nombre_usuario,clave,grupo,correo,fecha_creacion,fecha_modificacion,locked,enabled)values('','','admin','$2a$10$xGyyOE1.My66TPFJ9gB1tehP8uBX0syImfAX0WxKLVxGRDPZfX4mm','','',CURRENT_DATE,CURRENT_DATE,false,true);
INSERT INTO usuarios_roles(rol_id,usuario_id)values(1,1);
INSERT INTO usuarios_roles(rol_id,usuario_id)values(2,1);

insert into afiliados(nombre,apellido,email,create_at)values('LENIN','MANRIQUE','leninmanrique@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('LUIS','PEREZ','lperez@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ELENA','GONZALEZ','elenagonz@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ERNESTO','PERNIA','epernia@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ADA','LOPEZ','adal@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ROBERTO','HERNANDEZ','rhernadez@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ANALIA','GUERRERO','analiaguer@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('SARA','AGUILAR','sarag@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('EDUARDO','UZCATEGUI','euzcategui@gmail.com',CURRENT_DATE);
