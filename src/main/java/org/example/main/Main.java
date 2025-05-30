package org.example.main;

import org.example.config.DatabaseConnection;
import org.example.entities.Domicilio;
import org.example.entities.Persona;
import org.example.services.DomicilioServiceImpl;
import org.example.services.PersonaServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.example.entities.Domicilio;
import org.example.entities.Persona;
import org.example.services.DomicilioServiceImpl;
import org.example.services.PersonaServiceImpl;

public class Main {
    public static void main(String[] args) {

        try {
            // Crear servicios
            DomicilioServiceImpl domicilioService = new DomicilioServiceImpl();
            PersonaServiceImpl personaService = new PersonaServiceImpl();

            // Crear un domicilio
            Domicilio domicilio = new Domicilio("Evergreen Terrace", 742, "Springfield");

            // Guardar domicilio en BD
            domicilioService.save(domicilio);

            System.out.println("Domicilio guardado con éxito.");

            // Supongamos que el domicilio quedó con ID 1, para simplificar el ejemplo
            // En una implementación real deberías obtener el ID generado en save.

            // Crear persona con ese domicilio
            Persona persona = new Persona("Homero Simpson", 39);
            persona.setDomicilio(domicilio);

            // Guardar persona en BD
            personaService.save(persona);

            System.out.println("Persona guardada con éxito.");

            // Buscar persona por id
            Persona personaBuscada = personaService.findById(1);
            if (personaBuscada != null) {
                System.out.println("Persona encontrada: " + personaBuscada.getNombre()
                        + ", Edad: " + personaBuscada.getEdad()
                        + ", Calle: " + personaBuscada.getDomicilio().getCalle());
            } else {
                System.out.println("No se encontró la persona.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}