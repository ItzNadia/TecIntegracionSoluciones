package com.comunidad.modelo;

import java.sql.Statement;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comunidad.Conexion;

public class Persona {
    private int idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String edad;
    private String fotografia;
    private String lenguajeFav;
    private String vacuna;

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
            String edad, String fotografia, String lenguajeFav, String vacuna) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.edad = edad;
        this.fotografia = fotografia;
        this.lenguajeFav = lenguajeFav;
        this.vacuna = vacuna;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEdad() {
        return edad;
    }

    public String getFotografia() {
        return fotografia;
    }

    public String getLenguajeFav() {
        return lenguajeFav;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public void setLenguajeFav(String lenguajeFav) {
        this.lenguajeFav = lenguajeFav;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public static List<Persona> listaPersonas() {
        List<Persona> personas = new ArrayList<>();
        Statement statement;
        try {
            Connection c = Conexion.getConnectionPSQL();
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("select * from personas");
            Persona persona = null;
            while (rs.next()) {
                persona = new Persona(rs.getInt("idPersona"), rs.getString("nombres"), rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"), rs.getString("correo"), rs.getString("edad"),
                        rs.getString("fotografia"), rs.getString("lenguajeFavorito"), rs.getString("vacuna"));
                personas.add(persona);
            }
            rs.close();
            statement.close();
            c.close();
        } catch (SQLException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }

    @Override
    public String toString() {
        return "Persona1{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno +
                ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", edad=" + edad + ", fotografia="
                + fotografia + ", lenguajeFav=" + lenguajeFav +
                ", vacuna=" + vacuna + '}';
    }
}
