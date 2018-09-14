INSERT INTO perfiles (nombre, descripcion) VALUES ('Cuidador', 'Persona que ');

INSERT INTO personas (dni, nombre, apellidos, fecha_nacimiento) VALUES ('58963322D', 'Xavi','el_amo', STR_TO_DATE( '04-11-1996', '%d-%m-%Y' ));

INSERT INTO cuidadores(persona) VALUES(1);

INSERT INTO usuarios (username, password, cuidador_id,E_perfil) VALUES ('skaterpro360', 'nomirar',1,1);

INSERT INTO dependientes (alias, persona, cuidador) Values ('abu',1,1);

INSERT INTO spds VALUES ();

INSERT INTO tratamientos (dependiente, spd) VALUES (1,1);

INSERT INTO medicamentos (nombre) VALUES ('LSD');

INSERT INTO cargas (spd, medicamento, cantidad) VALUES (1,1,3);

INSERT INTO dosis (hora_inicio, veces_dia, medicamento, tratamiento) VALUES (TIME("12:00:00"), 5, 1, 1);
