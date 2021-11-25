package model;

import java.time.LocalDate;
import java.util.List;

public class Alumno {
    private int idAlumno;
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate birthDate;
    private List<Asignatura> asignaturas;

    public Alumno(int idAlumno, String dni, String nombre, String apellidos, LocalDate nacimiento,
            List<Asignatura> asignaturas) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.birthDate = nacimiento;
        this.asignaturas = asignaturas;
    }

    public Alumno() {
    }

    
    /** 
     * @return int
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    
    /** 
     * @return String
     */
    public String getDni() {
        return dni;
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
    public String getApellidos() {
        return apellidos;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    
    /** 
     * @return List<Asignatura>
     */
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    
    /** 
     * @param idAlumno
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    
    /** 
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    /** 
     * @param birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    
    /** 
     * @param asignaturas
     */
    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return this.idAlumno + "\t|\t" + this.dni + "\t|\t" + this.nombre + "\t|\t" + this.apellidos + "\t|\t"
                + this.birthDate;
    }
}
