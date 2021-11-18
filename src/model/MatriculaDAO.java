package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO implements DAO<Matricula> {

    @Override
    public Matricula get(long id) {
        return new Matricula();
    }

    @Override
    public List getAll(Connection conn) {
        List<Matricula> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM MATRICULA;");
            lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                int alumno = rs.getInt(2);
                int asignatura = rs.getInt(3);
                int profesor = rs.getInt(4);
                lista.add(new Matricula(id, alumno, asignatura, profesor));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insert(Connection conn, Matricula matricula) {
        try (PreparedStatement s = conn
                .prepareStatement("INSERT INTO MATRICULA (ALUMNO, ASIGNATURA, PROFESOR) VALUES (?, ?, ?);")) {
            s.setInt(1, matricula.getIdalumno());
            s.setInt(2, matricula.getAsignatura());
            s.setInt(3, matricula.getProfesor());
            return !s.execute();
        } catch (SQLException e) {
            System.out.println(e.getSQLState()+": "+e.getMessage());
            return false;
        }
    }
}
