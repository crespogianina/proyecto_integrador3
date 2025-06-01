package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import org.example.config.DatabaseConnection;
import org.example.entities.Domicilio;

import org.example.config.DatabaseConnection;
public class DomicilioDAOImpl implements GenericDAO<Domicilio> {
    @Override
    public void save(Domicilio domicilio) throws Exception {
        String query = "INSERT INTO domicilio (calle, numero, localidad) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Fallo al insertar domicilio, no se afectaron filas.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    domicilio.setId(generatedKeys.getInt(1)); // Asigna el ID generado
                } else {
                    throw new Exception("No se pudo obtener el ID generado para domicilio.");
                }
            }
        }
    }


    @Override
    public Domicilio findById(int id) throws Exception {
        String query = "SELECT id, calle, numero, localidad FROM domicilio WHERE id = ?";
        Domicilio domicilio = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    domicilio = new Domicilio(
                            rs.getString("calle"),
                            rs.getInt("numero"),
                            rs.getString("localidad")
                    );
                    domicilio.setId(rs.getInt("id"));
                }
            }
        }

        return domicilio;
    }


    @Override

    public List<Domicilio> findAll() throws Exception {
        String query = "SELECT id, calle, numero, localidad FROM domicilio";
        List<Domicilio> domicilios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("localidad")
                );
                domicilio.setId(rs.getInt("id"));

                domicilios.add(domicilio);
            }
        }

        return domicilios;
    }


    @Override
    public void update(Domicilio domicilio) throws Exception {
        String query = "UPDATE domicilio SET calle = ?, numero = ?, localidad = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setInt(4, domicilio.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No se encontró domicilio con id: " + domicilio.getId());
            }
        }
    }


    @Override
    public void delete(int id) throws Exception {
        String query = "DELETE FROM domicilio WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("No se encontró domicilio con id: " + id);
            }
        }
    }

}
