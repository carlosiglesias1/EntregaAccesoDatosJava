package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO implements DAO<Matricula> {

    @Override
    public Matricula get(long id) {
        return new Matricula();
    }

    @Override
    public List<Matricula> getAll(Connection conn) {
        List<Matricula> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM VIEWALL;");
            lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                Alumno alumno = new Alumno(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), LocalDate.parse(rs.getDate(6).toString()), new ArrayList<>());
                Asignatura asignatura = new Asignatura(rs.getInt(7), rs.getString(8), rs.getString(9));
                Profesor profesor = new Profesor(rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13));
                lista.add(new Matricula(id, alumno, asignatura, profesor));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int[] insert(Connection conn, Matricula[] matriculas) {
        try (PreparedStatement s = conn
                .prepareStatement("INSERT INTO MATRICULA (ALUMNO, ASIGNATURA, PROFESOR) VALUES (?, ?, ?);")) {
            for (int i = 0; i < matriculas.length; i++) {
                s.setInt(1, matriculas[i].getIdalumno().getIdAlumno());
                s.setInt(2, matriculas[i].getAsignatura().getCodAsignatura());
                s.setInt(3, matriculas[i].getProfesor().getCodProf());
                s.addBatch();
            }
            return s.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + ": " + e.getMessage());
            return new int[0];
        }
    }
}
