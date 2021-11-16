package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements DAO<Profesor> {
    @Override
    public Profesor get(long id) {
        return new Profesor();
    }

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
}
