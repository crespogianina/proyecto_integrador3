package org.example.dao;

import org.example.config.DatabaseConnection;
import org.example.entities.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class PersonaDAOImpl implements GenericDAO<Persona> {

    @Override
    public void save(Persona persona) throws Exception {
        String query = "INSERT INTO persona (nombre, apellido, fk_domicilio) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setInt(2, persona.getDomicilio().getNumero());
        }
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
