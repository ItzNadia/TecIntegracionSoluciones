package com.comunidad;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App 
{
    public static void main( String[] args )
    {
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        init();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            
            
            return new ModelAndView(model, "/main.vm");
        }, new VelocityTemplateEngine());
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
