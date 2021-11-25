package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import view.Errores;

public class AlumnoDAO implements DAO<Alumno> {

    /**
     * @param id
     * @return Alumno
     */
    @Override
    public Alumno get(long id) {
        return new Alumno();
    }

    /**
     * @param conn
     * @return List<Alumno>
     */
    @Override
    public List<Alumno> getAll(Connection conn) {
        List<Alumno> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM ALUMNO;");
            lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String dni = rs.getString(2);
                String nombre = rs.getString(3);
                String apellidos = rs.getString(4);
                LocalDate fechaNac = LocalDate.parse(rs.getDate(5).toString());
                ArrayList<Asignatura> materias = new ArrayList<>();
                lista.add(new Alumno(id, dni, nombre, apellidos, fechaNac, materias));
            }
            rs.close();
            rs = s.executeQuery(
                    "SELECT A.CODASIGN, A.NOMBRE, A.CURSO, M.ALUMNO FROM ASIGNATURA A INNER JOIN MATRICULA M ON M.ASIGNATURA = A.CODASIGN GROUP BY M.ALUMNO ORDER BY M.ALUMNO;");
            int idalumno = 0;
            while (rs.next()) {
                while (idalumno < rs.getInt(4))
                    idalumno++;
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String curso = rs.getString(3);
                lista.get(idalumno).getAsignaturas().add(new Asignatura(id, nombre, curso));
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * @param conn
     * @param alumno
     * @return int
     */
    public int insert(Connection conn, Alumno alumno) {
        try (PreparedStatement s = conn
                .prepareStatement("INSERT INTO ALUMNO (DNI, NOMBRE, APELLIDOS, FECHA) VALUES (?, ?, ?, ?);")) {
            s.setString(1, alumno.getDni());
            s.setString(2, alumno.getNombre());
            s.setString(3, alumno.getApellidos());
            s.setDate(4, Date.valueOf(alumno.getBirthDate()));
            return s.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return -1;
        }
    }

    /**
     * @param conn
     * @param alumno
     */
    public void update(Connection conn, Alumno alumno) {
        try (PreparedStatement s = conn.prepareStatement(
                "UPDATE ALUMNO SET DNI = ?, NOMBRE = ?, APELLIDOS = ?, FECHA = ? WHERE IDALUMNO = ?")) {
            s.setString(1, alumno.getDni());
            s.setString(2, alumno.getNombre());
            s.setString(3, alumno.getApellidos());
            s.setDate(4, Date.valueOf(alumno.getBirthDate()));
            s.setInt(5, alumno.getIdAlumno());
            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    public void delete(Connection conn, Alumno alumno) {
        try (PreparedStatement s = conn.prepareStatement("DELETE FROM ALUMNO WHERE IDALUMNO = ?")) {
            s.setInt(1, alumno.getIdAlumno());
            s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
        }
    }
}
