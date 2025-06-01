package org.example.main;

import org.example.entities.Domicilio;
import org.example.entities.Persona;
import org.example.services.DomicilioServiceImpl;
import org.example.services.PersonaServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            // Crear servicios
            DomicilioServiceImpl domicilioService = new DomicilioServiceImpl();
            PersonaServiceImpl personaService = new PersonaServiceImpl();

            // Crear un domicilio
            Domicilio domicilio = new Domicilio("Evergreen Terrace", 742, "Springfield");
            domicilioService.save(domicilio);
            System.out.println("Domicilio guardado con éxito.");

            // Crear persona con ese domicilio
            Persona persona = new Persona("Homero Simpson", 39);
            persona.setDomicilio(domicilio);
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

            // Listar todas las personas
            List<Persona> personas = personaService.findAll();
            if (personas != null && !personas.isEmpty()) {

                System.out.println("\nListado de personas:");
                for (Persona p : personas) {
                    System.out.println(p.getId() + ": " + p.getNombre() + ", Edad: " + p.getEdad());
                }
            } else {
                System.out.println("No se encontraron personas");
            }

            if (personaBuscada != null) {
                // Actualizar persona (ejemplo: cambiar edad)
                personaBuscada.setEdad(40);
                personaService.update(personaBuscada);
                System.out.println("\nPersona actualizada. Edad cambiada a: " + personaBuscada.getEdad());
            }

            // Listar nuevamente para ver el cambio
            personas = personaService.findAll();
            System.out.println("\nListado actualizado de personas:");
            for (Persona p : personas) {
                System.out.println(p.getId() + ": " + p.getNombre() + ", Edad: " + p.getEdad());
            }

            // Borrar persona
            if (personaBuscada != null) {
                personaService.delete(personaBuscada.getId());
                System.out.println("\nPersona borrada con id: " + personaBuscada.getId());
            }

            // Listar para confirmar borrado
            personas = personaService.findAll();
            System.out.println("\nListado final de personas:");
            for (Persona p : personas) {
                System.out.println(p.getId() + ": " + p.getNombre());
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error:");
            e.printStackTrace(System.out);
        }

    }
}
