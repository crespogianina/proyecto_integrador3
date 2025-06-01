package org.example.services;

import org.example.dao.GenericDAO;
import org.example.dao.PersonaDAOImpl;
import org.example.entities.Domicilio;
import org.example.entities.Persona;

import java.util.List;

public class PersonaServiceImpl implements GenericDAO<Persona> {

    private final PersonaDAOImpl personaDAO = new PersonaDAOImpl();
    private final DomicilioServiceImpl domicilioService = new DomicilioServiceImpl();

    @Override
    public void save(Persona persona) throws Exception {
        if (persona.getEdad() == null) {
            throw new IllegalArgumentException("La edad de la persona no puede ser nula");
        }
        if (persona.getNombre() == null) {
            throw new IllegalArgumentException("El nombre de la persona no puede ser nulo");
        }

        if (persona.getDomicilio() == null || persona.getDomicilio().getId() == null) {
            Domicilio domicilio = domicilioService.saveAndReturnDomicilio(persona.getDomicilio());
            persona.setDomicilio(domicilio);
        }

        personaDAO.save(persona);
    }

    @Override
    public Persona findById(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor 0");
        }

        Persona persona = personaDAO.findById(id);
        if (persona == null) {
            throw new Exception("No se encontró ninguna persona con ID " + id);
        }

        return persona;
    }

    @Override
    public List<Persona> findAll() throws Exception {
        List<Persona> personas = personaDAO.findAll();

        if (personas == null || personas.isEmpty()) {
            throw new Exception("No se encontraron personas");

        }
        return personas;
    }

    @Override
    public void update(Persona persona) throws Exception {
        if (persona.getId() == null || persona.getId() <= 0) {
            throw new IllegalArgumentException("No se puede actualizar una persona sin ID");
        }
        if (persona.getNombre() == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        if (persona.getEdad() == null) {
            throw new IllegalArgumentException("La edad no puede ser nula");
        }
        if (persona.getDomicilio() == null) {
            throw new IllegalArgumentException("El domicilio no puede ser nula");
        }

        personaDAO.update(persona);
    }

    @Override
    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor 0");
        }

        personaDAO.delete(id);
    }
}
