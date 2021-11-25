package model;

public class Asignatura {
    private int codAsignatura;
    private String nombre;
    private String curso;

    public Asignatura(int code, String nombre, String curso) {
        this.codAsignatura = code;
        this.nombre = nombre;
        this.curso = curso;
    }

    public Asignatura() {

    }

    
    /** 
     * @return int
     */
    public int getCodAsignatura() {
        return codAsignatura;
    }

    
    /** 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * @return String
     */
    public String getCurso() {
        return curso;
    }

    
    /** 
     * @param codAsignatura
     */
    public void setCodAsignatura(int codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @param curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return this.codAsignatura + "\t|\t" + this.nombre + "\t|\t" + this.curso;
    }
}
