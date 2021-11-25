package model;

import java.util.List;

public class Departamento {
    private int code;
    private String nombre;
    private List<Profesor> profesors;

    public Departamento(int code, String nombre, List<Profesor> profesors) {
        this.code = code;
        this.nombre = nombre;
        this.profesors = profesors;
    }

    public Departamento() {
        this.code = -1;
        this.nombre = null;
        this.profesors = null;
    }

    
    /** 
     * @return int
     */
    public int getCode() {
        return code;
    }

    
    /** 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * @return List<Profesor>
     */
    public List<Profesor> getProfesors() {
        return profesors;
    }

    
    /** 
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @param profesors
     */
    public void setProfesors(List<Profesor> profesors) {
        this.profesors = profesors;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "|" + this.code + "\t|\t" + this.nombre + "|";
    }
}
