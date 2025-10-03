package Matriculas;
import java.util.*;
import java.util.stream.Collector;

public class Personas {
    protected String id;
    protected String nombre;
    protected String apellido;
    protected String correo;

    public Personas(String id, String nombre, String apellido, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }
}
