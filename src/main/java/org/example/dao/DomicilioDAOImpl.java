package org.example.dao;

import org.example.entities.Domicilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.example.config.DatabaseConnection;
public class DomicilioDAOImpl implements GenericDAO<Domicilio> {
    @Override
    public void save(Domicilio domicilio) throws Exception {
        String query = "INSERT INTO domicilio (calle, numero) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

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

    }

    @Override
    public void delete(int id) throws Exception {

    }
}
