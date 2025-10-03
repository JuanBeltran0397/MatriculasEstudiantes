package Matriculas.Patrones;
import Matriculas.Modelo.Materia;

//se aplica el patron factory para crear objetos de tipo materia
public class MateriaFactory {
    public static Materia CrearMateria(String tipo, String id, String nombre, int creditos) {
        if (tipo.equalsIgnoreCase("obligatoria")) {// si el tipo es obligatoria se crea una materia obligatoria
            return new Materia(id, nombre + " [Obligatoria]", creditos);
        } else if (tipo.equalsIgnoreCase("electiva")) {// si el tipo es electiva se crea una materia electiva
            return new Materia(id, nombre + " [Electiva]", creditos);
        } else {
            throw new IllegalArgumentException("Tipo de materia no valido");// si el tipo no es valido se lanza una excepcion
        }
    }
}