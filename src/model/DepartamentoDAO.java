package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import view.Errores;

public class DepartamentoDAO implements DAO<Departamento> {
    
    /** 
     * @param id
     * @return Departamento
     */
    @Override
    public Departamento get(long id) {
        return new Departamento();
    }

    
    /** 
     * @param conn
     * @return List<Departamento>
     */
    @Override
    public List<Departamento> getAll(Connection conn) {
        List<Departamento> lista = null;
        try (Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = s.executeQuery("SELECT * FROM DEPT;");
            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            lista = new ArrayList<>(totalRows);
            while (rs.next()) {
                int codDEpt = rs.getInt(1);
                String nombre = rs.getString(2);
                List<Profesor> profesors = new ArrayList<>();

                lista.add(new Departamento(codDEpt, nombre, profesors));
            }
            rs.close();
            for (Departamento departamento : lista) {
                rs = s.executeQuery("SELECT * FROM PROFESOR WHERE DEPARTAMENTO = " + departamento.getCode());
                while (rs.next()) {
                    departamento.getProfesors()
                            .add(new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
            }
            rs.close();
        } catch (SQLException e) {
            Errores.sqlError(e);
        }
        return lista;
    }

    
    /** 
     * @param conn
     * @param dept
     * @return int
     */
    public int insert(Connection conn, Departamento dept) {
        try (PreparedStatement s = conn.prepareStatement("INSERT INTO DEPT (NOMBRE) VALUES (?);")) {
            s.setString(1, dept.getNombre());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }

    
    /** 
     * @param conn
     * @param dept
     * @return int
     */
    public int delete(Connection conn, Departamento dept) {
        try (PreparedStatement s = conn.prepareStatement("DELETE FROM DEPT WHERE CODDEPT = ?")) {
            s.setInt(1, dept.getCode());
            return s.executeUpdate();
        } catch (SQLException e) {
            Errores.sqlError(e);
            return -1;
        }
    }
}
