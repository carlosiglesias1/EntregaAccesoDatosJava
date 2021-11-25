package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import view.Errores;

public class AsignaturaDAO implements DAO<Asignatura> {

    /**
     * @param id
     * @return Asignatura
     */
    @Override
    public Asignatura get(long id) {
        return new Asignatura();
    }

    /**
     * @param conn
     * @return List<Asignatura>
     */
    @Override
    public List<Asignatura> getAll(Connection conn) {
        List<Asignatura> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM ASIGNATURA;");
            lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String curso = rs.getString(3);
                lista.add(new Asignatura(id, nombre, curso));
            }
            rs.close();
        } catch (SQLException e) {
            Errores.sqlError(e);
        }
        return lista;
    }

    /**
     * @param conn
     * @param asignatura
     * @return int
     */
    public int insert(Connection conn, Asignatura asignatura) {
        try (PreparedStatement s = conn.prepareStatement("INSERT INTO ASIGNATURA (NOMBRE, CURSO) VALUES (?, ?);")) {
            s.setString(1, asignatura.getNombre());
            s.setString(2, asignatura.getCurso());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

    public int update(Connection conn, Asignatura asignatura) {
        try (PreparedStatement s = conn.prepareStatement("UPDATE ASIGNATURA SET NOMBRE =?, CURSO = ?")) {
            s.setString(1, asignatura.getNombre());
            s.setString(2, asignatura.getCurso());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

    /**
     * @param conn
     * @param asignatura
     * @return int
     */
    public int delete(Connection conn, Asignatura asignatura) {
        try (PreparedStatement s = conn.prepareStatement("DELETE FROM ASIGNATURA WHERE CODASIGN = ?;")) {
            s.setInt(1, asignatura.getCodAsignatura());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

}
