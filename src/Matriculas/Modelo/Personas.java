package Matriculas.Modelo;

public class Personas {
    protected String documento;
    protected String nombre;
    protected String apellido;
    protected String correo;

    public Personas(String documento, String nombre, String apellido, String correo) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }
}
