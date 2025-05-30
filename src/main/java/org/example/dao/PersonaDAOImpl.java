package org.example.dao;

import org.example.entities.Persona;

import java.util.List;

public class PersonaDAOImpl implements GenericDAO<Persona> {

    @Override
    public void save(Persona persona) throws Exception {


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
