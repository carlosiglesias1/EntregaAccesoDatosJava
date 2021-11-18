package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO implements DAO<Asignatura> {

    @Override
    public Asignatura get(long id) {
        return new Asignatura();
    }

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
            e.printStackTrace();
        }
        return lista;
    }

}
