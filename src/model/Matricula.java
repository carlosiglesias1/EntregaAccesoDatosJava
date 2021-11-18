package model;

public class Matricula {
    private int id;
    private int idalumno;
    private int asignatura;
    private int profesor;

    public Matricula() {

    }

    public Matricula(int id, int alumno, int asignatura, int profesor) {
        this.id = id;
        this.idalumno = alumno;
        this.asignatura = asignatura;
        this.profesor = profesor;
    }

    public int getAsignatura() {
        return asignatura;
    }

    public int getId() {
        return id;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }
}
