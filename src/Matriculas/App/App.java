package Matriculas.App;

import Matriculas.Modelo.*;
import Matriculas.Patrones.*;

import java.util.Locale;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static SistemaMatricula sistemaMat=SistemaMatricula.getInstancia();

    public static void main(String[] args) {
        boolean salir=false;

        while (!salir){
            menuMatriculas();
            int opcion=sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            switch (opcion){
                case 1->AgregarEstudiante();
                case 2->AgregarMateria();
                case 3->MatricularMaterias();
                case 4->ListarMaterias();
                case 5->{
                    salir=true;
                    System.out.println("Saliendo del sistema de matriculas...");
                }
                default->System.out.println("Opcion no valida, intente de nuevo.");
            }
        }
        sc.close();// Cerrar el scanner al finalizar
    }
    public static void menuMatriculas(){// Menu principal
        System.out.println("\n--- Menu de Matriculas ---");
        System.out.println("1. Agregar Estudiante");
        System.out.println("2. Agregar Materia");
        System.out.println("3. Matricular Materias a Estudiante");
        System.out.println("4. Listar Materias por Creditos Minimos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    public static void AgregarEstudiante(){// metodo para agregar estudiante con los datos necesarios
        System.out.println("\n--- Agregar Estudiante ---");
        System.out.print("Documento: ");
        String documento=sc.nextLine();
        System.out.print("Nombre: ");
        String nombre=sc.nextLine();
        System.out.print("Apellido: ");
        String apellido=sc.nextLine();
        System.out.print("Correo: ");
        String correo=sc.nextLine();
        System.out.print("Codigo Estudiante: ");
        String codigoEstudiante=sc.nextLine();
        System.out.print("Semestre: ");
        int semestre=sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        System.out.println("Carrera: ");
        String carrera=sc.nextLine();
        System.out.print("Estado (activo/inactivo): ");
        String estado=sc.nextLine();
        Estudiante estudiante=new Estudiante(documento,nombre,apellido,correo,codigoEstudiante,semestre,carrera,estado);
        sistemaMat.agregarEstudiante(estudiante);

    }
    public static void AgregarMateria(){
        System.out.println("\n---Agregar Materia---");
        System.out.print("ID Materia: ");
        String idMateria=sc.nextLine();
        System.out.print("Nombre Materia: ");
        String nombreMateria=sc.nextLine();
        System.out.print("Creditos: ");
        int creditos=sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        System.out.println("Tipo (obligatoria/electiva): ");
        String tipo=sc.nextLine();

        try{
            Materia m=MateriaFactory.CrearMateria(tipo, idMateria,nombreMateria,creditos);// Usando el patron factory para crear la materia
            sistemaMat.agregarMateria(m);// Agregando la materia al sistema
            System.out.println("Materia "+nombreMateria+" agregada exitosamente.");
        }catch(IllegalArgumentException e){// Capturando excepcion en caso de que el tipo de materia no sea valido
            System.out.println("Error al agregar materia: "+e.getMessage());// Mostrando mensaje de error
        }
        //finally {
           // sc.nextLine(); // Limpiar el buffer
       // }
    }
    public static void MatricularMaterias(){// Metodo para matricular materias a un estudiante
        System.out.println("\n---Matricular Materias a Estudiante---");
        System.out.print("Codigo Estudiante: ");
        String codigoEstudiante=sc.nextLine();

        Estudiante estudianteEncontrado= sistemaMat.getEstudiantes().stream()// Buscando el estudiante por codigo
                .filter(e->e.getCodigoEstudiante().equals(codigoEstudiante))// Filtrando por codigo
                .findFirst()// Buscando el primer resultado
                .orElse(null);// Si no se encuentra, retorna null
        if (estudianteEncontrado==null){// Si no se encuentra el estudiante, muestra mensaje y retorna
            System.out.println("Estudiante con codigo "+codigoEstudiante+" no encontrado.");
            return;
        }
        Matriculas matricula=new Matriculas(estudianteEncontrado);// Creando una nueva matricula para el estudiante
        boolean agregarMas=true;// Variable para controlar el ciclo de agregar materias
        while(agregarMas){// Ciclo para agregar materias
            System.out.println("Nombre de la Materia a matricular: ");
            String nombreMateria=sc.nextLine();

            Materia materiaEncontrada= sistemaMat.getMaterias().stream()// Buscando la materia por nombre
                    .filter(m->m.getNombreMateria().toLowerCase().contains(nombreMateria.toLowerCase()))// Filtrando por nombre, ignorando mayusculas/minusculas
                    .findFirst()// Buscando el primer resultado
                    .orElse(null);// Si no se encuentra, retorna null
            if (materiaEncontrada!=null){// Si se encuentra la materia, se agrega a la matricula
                matricula.agregarMateria(materiaEncontrada);// Agregando la materia a la matricula
                System.out.println("Materia "+materiaEncontrada.getNombreMateria()+" agregada a la matricula.");
            }else{
                System.out.println("Materia "+nombreMateria+" no encontrada.");
            }
            System.out.println("¿Desea agregar otra materia? (s/n): ");
            String continuar=sc.nextLine().toLowerCase();// Preguntando si desea agregar otra materia
            agregarMas= continuar.equalsIgnoreCase("s");// Si la respuesta es 's', continua el ciclo
        }
        System.out.println("Matricula completada para el estudiante "+estudianteEncontrado.getNombreCompleto()+":");// Mostrando resumen de la matricula
        matricula.mostrarMaterias();// Mostrando las materias matriculadas
        System.out.println("Total de creditos matriculados: "+matricula.calcularTotalCreditos());// Mostrando el total de creditos
    }
    public static void ListarMaterias(){// metodo para listar las materias por creditos minimos
        System.out.println("\n---Listar materias por Creditos Minimos---");
        System.out.print("Ingrese el numero minimo de creditos: ");
        int creditosMinimos=sc.nextInt();// Leyendo el numero de creditos minimos
        sc.nextLine();// Limpiar el buffer
        sistemaMat.listarMateriasPorCreditos(creditosMinimos);// Listando las materias que cumplen con el requisito
    }

}
