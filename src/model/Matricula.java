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

    
    /** 
     * @return Asignatura
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @return Alumno
     */
    public Alumno getIdalumno() {
        return idalumno;
    }

    
    /** 
     * @return Profesor
     */
    public Profesor getProfesor() {
        return profesor;
    }

    
    /** 
     * @param asignatura
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @param idalumno
     */
    public void setIdalumno(Alumno idalumno) {
        this.idalumno = idalumno;
    }

    
    /** 
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
