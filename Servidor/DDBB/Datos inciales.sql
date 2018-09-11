INSERT INTO perfiles (nombre, descripcion) VALUES ('Cuidador', 'Persona que se dedica a la atención de un dependiente');

INSERT INTO personas (dni, nombre, apellidos, fecha_nacimiento) VALUES ('58963322d', 'Xavo','el_amo',04-11-1996);

INSERT INTO Cuidadores(id_persona) VALUES(1);

INSERT INTO Usuarios (username, password, id_cuidador,E_perfil) VALUES ('skaterpro360', 'nomirar',1,1);

INSERT INTO Dependientes (alias, id_persona, id_cuidador) Values ('abu',1,1);

INSERT INTO SPDs VALUES ();

INSERT INTO Tratamientos (id_dependientes, idspd) VALUES (1,1);

INSERT INTO Medicamentos (nombre) VALUES ('LSD');

INSERT INTO Cargas (id_spd, id_medicamento) VALUES (1,1);

INSERT INTO Dosis (hora_inicio, veces_dia, id_medicamento, id_tratamiento) VALUES (12:00, 5, 1, 1);