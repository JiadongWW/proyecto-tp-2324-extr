import java.util.Scanner;

/**
 * Clase Utilidades
 */
public class Utilidades {

    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     * TODO leer por pantalla y comprobar que es una cadena de caracteres válida. 
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.println(s);
        return teclado.next();
    }

    /**
     * Método estático leerNumero para leer un numero pasado por pantalla
     * TODO leer por pantalla y comprobar que es un número valido. Solicita un número repetidamente hasta que se
     *  introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int num;
        System.out.println(mensaje);
        num=teclado.nextInt();
        while (num<minimo || num > maximo){
            System.out.println(mensaje);
            num=teclado.nextInt();
        }
        return num;
    }
}
