package org.example.services;

import org.example.dao.DomicilioDAOImpl;
import org.example.dao.GenericDAO;
import org.example.dao.PersonaDAOImpl;
import org.example.entities.Domicilio;
import org.example.entities.Persona;

import java.util.List;

public class DomicilioServiceImpl implements GenericDAO<Domicilio> {

    private final DomicilioDAOImpl domicilioDAO = new DomicilioDAOImpl();

    @Override
    public void save(Domicilio domicilio) throws Exception {
        if (domicilio.getCalle() == null)
            throw new IllegalArgumentException("La calle no puede ser nula");
        if (domicilio.getNumero() == null)
            throw new IllegalArgumentException("El número no puede ser nulo");

        domicilioDAO.save(domicilio);
    }

    @Override
    public Domicilio findById(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor que cero");
        }

        Domicilio domicilio = domicilioDAO.findById(id);
        if (domicilio == null) {
            throw new Exception("No se encontró ningún domicilio con ID " + id);
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> findAll() throws Exception {
        List<Domicilio> domicilios = domicilioDAO.findAll();
        if (domicilios == null || domicilios.isEmpty()) {
            throw new Exception("No se encontraron domicilio");
        }
        return domicilios;
    }

    @Override
    public void update(Domicilio domicilio) throws Exception {
        if (domicilio.getId() == null || domicilio.getId() <= 0)
            throw new IllegalArgumentException("El id del domicilio es inválido");
        if (domicilio.getCalle() == null)
            throw new IllegalArgumentException("La calle no puede ser nula");
        if (domicilio.getNumero() == null)
            throw new IllegalArgumentException("El número no puede ser nulo");

        domicilioDAO.update(domicilio);
    }

    @Override
    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor que cero");
        }
        domicilioDAO.delete(id);
    }

    public Domicilio saveAndReturnDomicilio(Domicilio domicilio) throws Exception {
        save(domicilio);
        return domicilio;
    }
}