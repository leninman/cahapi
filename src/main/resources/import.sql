insert into afiliados(nombre,apellido,email,create_at)values('LENIN','MANRIQUE','leninmanrique@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('LUIS','PEREZ','lperez@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ELENA','GONZALEZ','elenagonz@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ERNESTO','PERNIA','epernia@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ADA','LOPEZ','adal@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ROBERTO','HERNANDEZ','rhernadez@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('ANALIA','GUERRERO','analiaguer@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('SARA','AGUILAR','sarag@gmail.com',CURRENT_DATE);
insert into afiliados(nombre,apellido,email,create_at)values('EDUARDO','UZCATEGUI','euzcategui@gmail.com',CURRENT_DATE);
insert into grupos(groupname)values('Tropa profesional');
insert into users(enabled,password,username)values(true,'$2a$12$zKEd1Npmtpy9dCfTd9H0YuEJiW7PEUa7HkyYlpuUu5RsptQqEboPG','admin');
insert into users(enabled,password,username)values(true,'$2a$12$zKEd1Npmtpy9dCfTd9H0YuEJiW7PEUa7HkyYlpuUu5RsptQqEboPG','user');
insert into authorities(authority,user_id)values('admin',1);
insert into authorities(authority,user_id)values('user',2)

