package org.example.dao;

import org.example.config.DatabaseConnection;
import org.example.entities.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import org.example.entities.Domicilio;

public class PersonaDAOImpl implements GenericDAO<Persona> {

    @Override
    public void save(Persona persona) throws Exception {
        String query = "INSERT INTO persona (nombre, edad, fk_domicilio) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setInt(3, persona.getDomicilio().getId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Fallo al insertar persona, no se afectaron filas.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    persona.setId(generatedKeys.getInt(1)); // Asigna el ID generado
                } else {
                    throw new Exception("No se pudo obtener el ID generado para persona.");
                }
            }
        }
    }


    @Override

    public Persona findById(int id) throws Exception {
        String query = "SELECT p.id AS persona_id, p.nombre, p.edad, " +
                "d.id AS domicilio_id, d.calle, d.numero, d.localidad " +
                "FROM persona p " +
                "JOIN domicilio d ON p.fk_domicilio = d.id " +
                "WHERE p.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Crear domicilio con el constructor de 3 parámetros
                    Domicilio domicilio = new Domicilio(
                            rs.getString("calle"),
                            rs.getInt("numero"),
                            rs.getString("localidad")
                    );
                    domicilio.setId(rs.getInt("domicilio_id"));

                    // Crear persona y asignar domicilio
                    Persona persona = new Persona(
                            rs.getString("nombre"),
                            rs.getInt("edad")
                    );
                    persona.setId(rs.getInt("persona_id"));
                    persona.setDomicilio(domicilio);

                    return persona;
                }
            }
        }
        return null; // no se encontró la persona con ese id
    }


    @Override
    public List<Persona> findAll() throws Exception {
        String query = "SELECT p.id, p.nombre, p.edad, d.id as domicilio_id, d.calle, d.numero, d.localidad " +
                "FROM persona p " +
                "JOIN domicilio d ON p.fk_domicilio = d.id";

        List<Persona> personas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("localidad")
                );
                domicilio.setId(rs.getInt("domicilio_id"));

                Persona persona = new Persona(rs.getString("nombre"), rs.getInt("edad"));
                persona.setId(rs.getInt("id"));
                persona.setDomicilio(domicilio);

                personas.add(persona);
            }
        }

        return personas;
    }

    @Override
    public void update(Persona persona) throws Exception {
        String query = "UPDATE persona SET nombre = ?, edad = ?, fk_domicilio = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setInt(3, persona.getDomicilio().getId());
            ps.setInt(4, persona.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("No se encontró persona con id: " + persona.getId());
            }
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String query = "DELETE FROM persona WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("No se encontró persona con id: " + id);
            }
        }
    }
}
