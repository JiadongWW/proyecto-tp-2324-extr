import java.util.Scanner;

/**
 * Clase Utilidades
 */
public class Utilidades {

    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     * TODO leer por pantalla y comprobar que es una cadena de caracteres válida.
     *
     * @param teclado Permite introducir informacion a traves de la consola
     * @param s       String que debe ser impreso
     * @return Lo que se introduzca por teclado
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.println(s);
        return teclado.nextLine();
    }

    /**
     * Método estático leerNumero para leer un numero pasado por pantalla
     * TODO leer por pantalla y comprobar que es un número valido. Solicita un número repetidamente hasta que se
     *  introduzca uno correcto (dentro de los límites)
     *
     * @param teclado Permite introducir informacion a traves de la consola
     * @param mensaje Mensaje para pedir un numero entre el minimo y el maximo
     * @param minimo  valor minimo que tiene que tener el numero a introducir
     * @param maximo  valor maximo que tiene que tener el numero a introducir
     * @return int num
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int num;
        System.out.println(mensaje);
        num = teclado.nextInt();
        while (num < minimo || num > maximo) {
            System.out.println(mensaje);
            num = teclado.nextInt();
        }
        return num;
    }
}
