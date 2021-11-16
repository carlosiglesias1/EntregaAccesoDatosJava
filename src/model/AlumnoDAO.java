package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements DAO<Alumno> {
    @Override
    public Alumno get(long id) {
        return new Alumno();
    }

    @Override
    public List<Alumno> getAll(Connection conn) {
        List<Alumno> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM ALUMNO;");
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
                LocalDate fechaNac = LocalDate.parse(rs.getDate(5).toString());
                ArrayList<Asignatura> materias = new ArrayList<>();
                ResultSet rSet = s.executeQuery("SELECT * FROM ASIGNATURA WHERE ALUMNO = " + id);
                while (rSet.next()) {
                    materias.add(new Asignatura());
                }
                lista.add(new Alumno(id, dni, nombre, apellidos, fechaNac, materias));
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
