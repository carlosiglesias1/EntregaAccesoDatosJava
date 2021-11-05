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
}
