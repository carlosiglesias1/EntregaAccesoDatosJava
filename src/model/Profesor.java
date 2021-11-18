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

    public int getCodProf() {
        return codProf;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
