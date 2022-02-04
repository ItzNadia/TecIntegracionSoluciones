package com.comunidad;

import static spark.Spark.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comunidad.modelo.Persona;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        init();
        
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Persona> persona = Persona.listaPersonas();
            model.put("personas", persona);
            return new ModelAndView(model, "/main.vm");
        }, new VelocityTemplateEngine());
        
        get("/registro", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/registro.vm");
        }, new VelocityTemplateEngine());

        post("/registrar", (req, res) -> {
            String nombre = req.queryParams("nombre");
            String apellidoPaterno = req.queryParams("apellidoPaterno");
            String apellidoMaterno = req.queryParams("apellidoMaterno");
            String correo = req.queryParams("correo");
            String edad = req.queryParams("edad");
            String lenguajeFav = req.queryParams("lenguajeFavorito");
            String vacuna = req.queryParams("vacuna");
            String fotografia = "";

            Persona persona = new Persona();

            persona.setNombre(nombre);
            persona.setApellidoPaterno(apellidoPaterno);
            persona.setApellidoMaterno(apellidoMaterno);
            persona.setCorreo(correo);
            persona.setEdad(edad);
            persona.setLenguajeFav(lenguajeFav);
            persona.setVacuna(vacuna);
            persona.setFotografia(fotografia);

            //Persona.registrarPersona(persona);

            return nombre;
        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }

}
