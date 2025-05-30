package org.example.services;

import org.example.dao.DomicilioDAOImpl;
import org.example.dao.GenericDAO;
import org.example.dao.PersonaDAOImpl;
import org.example.entities.Domicilio;

import java.util.List;

public class DomicilioServiceImpl implements GenericDAO<Domicilio> {

    private final DomicilioDAOImpl domicilioDAO = new DomicilioDAOImpl();

    @Override
    public void save(Domicilio domicilio) throws Exception {
        if (domicilio.getCalle() == null) throw new IllegalArgumentException("La calle no puede ser nula");
        if (domicilio.getNumero() == null) throw new IllegalArgumentException("La calle no puede ser nula");

        domicilioDAO.save(domicilio);
    }

    @Override
    public Domicilio findById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Domicilio> findAll() throws Exception {
        return List.of();
    }

    @Override
    public void update(Domicilio domicilio) throws Exception {
        if (domicilio.getCalle() == null) throw new IllegalArgumentException("La calle no puede ser nula");

    }

    @Override
    public void delete(int id) throws Exception {

    }
}
