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

    public Alumno get(long id) {
        return new Alumno();
    }

    public boolean createTable(Connection conn) throws SQLException {
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String createIfNotExists = "CREATE TABLE IF NOT EXISTS ALUMNO(" + "IDALUMNO INT AUTO_INCREMENT PRIMARY KEY,"
                    + "DNI CHAR (9) NOT NULL," + "NOMBRE VARCHAR (50) NOT NULL," + "APELLIDO1 VARCHAR (40),"
                    + "APELLIDO2 VARCHAR (40)," + "FECHA DATE);";
            return s.execute(createIfNotExists);
        } catch (SQLException e) {
            throw new SQLException();
        }
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
                String apellido1 = rs.getString(4);
                String apellido2 = rs.getString(5);
                LocalDate fechaNac = LocalDate.parse(rs.getDate(6).toString());
                lista.add(new Alumno(id, dni, nombre, apellido1, apellido2, fechaNac));
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
