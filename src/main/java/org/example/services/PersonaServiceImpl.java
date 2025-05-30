package org.example.services;

import org.example.dao.DomicilioDAOImpl;
import org.example.dao.PersonaDAOImpl;
import org.example.entities.Persona;

import java.util.List;

public class PersonaServiceImpl implements GenericService<Persona> {

    private final PersonaDAOImpl personaDAO = new PersonaDAOImpl();
    private final DomicilioDAOImpl domicilioDAO = new DomicilioDAOImpl();


    @Override
    public void save(Persona persona) throws Exception {
        if (persona.getEdad() == null) {
            throw new IllegalArgumentException("La edad de la persona no puede ser nula");
        }
        if (persona.getNombre() == null) {
            throw new IllegalArgumentException("El nombre de la persona no puede ser nulo");
        }
        if (persona.getDomicilio().getId() == null) {
            throw new IllegalArgumentException("El domicilio de la persona no puede ser nulo");
        }

        personaDAO.save(persona);
    }

    @Override
    public Persona findById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Persona> findAll() throws Exception {
        return List.of();
    }

    @Override
    public void update(Persona persona) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }
}
