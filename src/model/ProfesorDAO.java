package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import view.Errores;

public class ProfesorDAO implements DAO<Profesor> {

    /**
     * Devuelve un nuevo Profesor
     * 
     * @param id
     * @return Profesor
     */
    @Override
    public Profesor get(long id) {
        return new Profesor();
    }

    /**
     * Obtiene todos los profesores de la BBDD
     * 
     * @param conn
     * @return List<Profesor>
     */
    @Override
    public List<Profesor> getAll(Connection conn) {
        List<Profesor> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM PROFESOR;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<>(totalRows);
            while (rs.next()) {
                int id = rs.getInt(1);
                String dni = rs.getString(2);
                String nombre = rs.getString(3);
                String apellidos = rs.getString(4);
                lista.add(new Profesor(id, dni, nombre, apellidos));
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * @param conn
     * @param profesor
     * @return int
     */
    public int insert(Connection conn, Profesor profesor) {
        try (PreparedStatement s = conn
                .prepareStatement("INSERT INTO PROFESOR (DNI, NOMBRE, APELLIDOS) VALUES (?, ?, ?);")) {
            s.setString(1, profesor.getDni());
            s.setString(2, profesor.getNombre());
            s.setString(3, profesor.getApellidos());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

    
    /** 
     * @param conn
     * @param profesor
     * @return int
     */
    public int delete(Connection conn, Profesor profesor) {
        try (PreparedStatement s = conn.prepareStatement("DELETE FROM PROFESOR WHERE CODPROF = ?")) {
            s.setInt(1, profesor.getCodProf());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

    
    /** 
     * @param conn
     * @param profesor
     * @param dept
     * @return int
     */
    public int update(Connection conn, Profesor profesor, Departamento dept) {
        try (PreparedStatement s = conn.prepareStatement(
                "UPDATE PROFESOR SET DNI = ?, NOMBRE = ?, APELLIDOS = ?, DEPARTAMENTO = ? WHERE CODPROF = ?;")) {
            s.setString(1, profesor.getDni());
            s.setString(2, profesor.getNombre());
            s.setString(3, profesor.getApellidos());
            s.setInt(4, dept.getCode());
            s.setInt(5, profesor.getCodProf());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

    
    /** 
     * @param conn
     * @param profesor
     * @return List<Asignatura>
     */
    public List<Asignatura> getSubjects(Connection conn, Profesor profesor) {
        try (PreparedStatement s = conn.prepareStatement("SELECT * FROM ASIGNATURASPROFESOR WHERE CODPROF = ?")) {
            s.setInt(1, profesor.getCodProf());
            ResultSet rs = s.executeQuery();
            ArrayList<Asignatura> asignaturas = new ArrayList<>();
            while (rs.next()) {
                asignaturas.add(new Asignatura(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return asignaturas;
        } catch (SQLException e) {
            Errores.sqlError(e);
            return new ArrayList<>();
        }
    }
}
