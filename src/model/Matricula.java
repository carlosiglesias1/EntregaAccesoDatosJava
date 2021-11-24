package model;

public class Matricula {
    private int id;
    private Alumno idalumno;
    private Asignatura asignatura;
    private Profesor profesor;

    public Matricula() {

    }

    public Matricula(int id, Alumno alumno, Asignatura asignatura, Profesor profesor) {
        this.id = id;
        this.idalumno = alumno;
        this.asignatura = asignatura;
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public int getId() {
        return id;
    }

    public Alumno getIdalumno() {
        return idalumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdalumno(Alumno idalumno) {
        this.idalumno = idalumno;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
