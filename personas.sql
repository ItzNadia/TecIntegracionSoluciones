CREATE TABLE Personas (
  idPersona SERIAL NOT NULL,
  nombres text NOT NULL,
  apellidoPaterno text NOT NULL,
  apellidoMaterno text NOT NULL,
  correo text NOT NULL,
  edad text NOT NULL,
  fotografia text NOT NULL,
  lenguajeFavorito text NOT NULL,
  vacuna text NOT NULL,
  PRIMARY KEY (idPersona)
);