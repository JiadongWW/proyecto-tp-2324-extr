import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Aventuras desde donde lanzar la ejecución de la práctica
 */
public class Aventuras {

    /**
     * Main desde donde ejecutar el programa
     * TODO instanciación e inicialización de objetos para la ejecución,
     *  ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     *  por teclado para jugar. Finalmente guardar la puntuación
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * Metodo guardarPuntuación en fichero
     * TODO abrir y guardar en el fichero pasado como parametro el personaje
     *  siguiendo el formato descrito en la memoria de la práctica
     * @param ficheroPuntuaciones
     * @param jugador
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new FileWriter(ficheroPuntuaciones,true));
            LocalDate fecha= LocalDate.now();
            salida.println(fecha+"  "+jugador.toString()+", "+jugador.getValorMochila());

        }catch (IOException e){
            System.out.println("Error al escribir en el fichero: "+e.getMessage());
        }
    }

    /**
     * Metodo mostrarPuntuaciones del fichero puntuaciones
     * TODO Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     *  pasado como parámetro. P.e:
     *              "Puntuaciones:
     *                  2024-04-04	{ Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     * @param ficheroPuntuaciones
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        Scanner sc = null;
        String linea;
        try {
            sc = new Scanner(new File(ficheroPuntuaciones));
            System.out.println("Puntuaciones: ");
            while (sc.hasNext()){
                linea=sc.nextLine();
                System.out.println(linea);
            }
        }catch (FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero: "+ficheroPuntuaciones);
        }finally {
            if (sc != null){
                sc.close();
            }
        }

    }
}
