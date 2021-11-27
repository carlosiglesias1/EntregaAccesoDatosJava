package model;

public class Profesor {
    private int codProf;
    private String dni;
    private String nombre;
    private String apellidos;

    public Profesor(int codProf, String dni, String nombre, String apellidos) {
        this.codProf = codProf;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Profesor() {
    }

    /**
     * @return int
     */
    public int getCodProf() {
        return codProf;
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
     * @param codProf
     */
    public void setCodProf(int codProf) {
        this.codProf = codProf;
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
     * @return String
     */
    @Override
    public String toString() {
        return "\t|" + this.codProf + "\t|\t" + this.dni + "\t|\t" + this.nombre + "\t|\t" + this.apellidos + "\t|";
    }
}
