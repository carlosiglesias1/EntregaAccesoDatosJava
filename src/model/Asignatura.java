package model;

public class Asignatura {
    private int codAsignatura;
    private String nombre;
    private String curso;

    public Asignatura(int code, String nombre, String curso){
        this.codAsignatura = code;
        this.nombre = nombre;
        this.curso = curso;
    }

    public Asignatura(){

    }

    public int getCodAsignatura() {
        return codAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCodAsignatura(int codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
