import com.sun.security.jgss.GSSUtil;
import jdk.jshell.execution.Util;

import java.util.Scanner;

/**
 * Clase Personaje
 */
public class Personaje {
    private final String nombre;
    private int vida;
    private final int ataque, defensa, destreza;
    private final Item[] items;

    private final double maxPesoPorPersonaje;

    /**
     * Constructor de la clase para inicializar todos los atributos
     * @param nombre
     * @param vida
     * @param ataque
     * @param defensa
     * @param destreza
     * @param maxItemsPorPersonaje
     * @param maxPesoPorPersonaje
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje, double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida= vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        this.items = new Item[maxItemsPorPersonaje];
        this.maxPesoPorPersonaje = maxPesoPorPersonaje;


    }

    /**
     * Metodo crearPersonaje que administra toda la generación de personajes
     * TODO El metodo tiene que ser capaz de recoger todas las características del personaje mediante preguntas y
     *  respuestas por pantalla y se debe controlar que los valores introducidos sean validos. Una vez recabados
     *  todos los datos del personaje generar un objeto con dichas características.
     * @param teclado
     * @return Personaje personaje
     */
    public static Personaje crearPersonaje(Scanner teclado) {
        String nombre;
        int vida,ataque,defensa,destreza, maximo;
        int items, peso;
        nombre=Utilidades.leerCadena(teclado,"¿Como te llamas?");
        System.out.println("¡Hola, "+nombre+"! Tienes 250 puntos para repartir entre vida, ataque, defensa y destreza.");
        maximo=248;
        vida=Utilidades.leerNumero(teclado,"¿Cuanta vida quieres tener? (50-247): ",50,maximo);
        maximo-=vida;
        ataque=Utilidades.leerNumero(teclado,"¿Cuanto ataque quieres tener? (1-"+maximo+"): ",1,maximo);
        maximo= maximo-ataque+1;
        defensa=Utilidades.leerNumero(teclado,"¿Cuanta defensa quieres tener? (1-"+maximo+"): ",1,maximo);
        maximo= maximo-defensa+1;
        destreza=Utilidades.leerNumero(teclado,"¿Cuanta destreza quieres tener? (1-"+maximo+"): ",1,maximo);

        items = destreza/4;
        peso = ataque /2;

        Personaje personaje = new Personaje(nombre,vida,ataque,defensa,destreza,items,peso);

        return personaje;

    }

    /**
     * Método getNombre
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getVida
     * @return int vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método getAtaque
     * @return int ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método getDefensa
     * @return int defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método getDestreza
     * @return int destreza
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Método getItems
     * @return Item[] items
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Método getItem para devolver un Item según un índice dado
     * TODO devolver null si el índice no es válido, y el item si el índice es correcto
     * @param indice
     * @return Item items[]
     */
    public Item getItem(int indice) {
        if (indice< items.length && indice>-1){
            return items[indice];
        }else return null;
    }

    /**
     * Método recibirDanyo para actualizar la vida de un personaje
     * TODO Si el daño no es positivo, no hacer nada. En caso contrario reducir la vida según el daño pasado
     * @param danyo
     */
    public void recibirDanyo(int danyo) {
        if (danyo>0){
            vida -= danyo;
        }
    }

    /**
     * Método anyadirItem para incluir un item en la mochila del personaje
     * TODO Comprobar si el item es valido y si el peso max del personaje no se supera para poder incluir el item,
     *  en caso negativo ddevolver false, en caso de que se pueda incluir, añadir el item a la lista de items del
     *  personaje y devolver true
     * @param item
     * @return false en caso negativo y true en caso positivo
     */
    public boolean anyadirItem(Item item) {
        if (item != null && item.getPeso()<=(maxPesoPorPersonaje-getPesoMochila())){
            for (int i =0; i< items.length; i++){
                if (items[i]==null){
                    items[i]=item;
                    return true;
                }
            }
        }else if (item != null && item.getPeso()>=(maxPesoPorPersonaje-getPesoMochila())){
            System.out.println("No hay espacio en la mochila");
        }else {
            System.out.println("No has añadido nada a tu mochila");
        }

        return false;
    }

    /**
     * Método sobreescrito para devolver la información de un personaje
     * TODO Método para devolver un String con la información del personaje en el formato
     *  descrito en la memoria de la práctica P.e: "{ Edgar (V: 20, A: 5, D: 2, X: 5) }"
     * @return String de la información del personaje
     */
    @Override
    public String toString() {
        return " { "+nombre+" (V: "+vida+", A: "+ataque+", D: "+defensa+", X: "+destreza+") }";
    }

    /**
     * Método getPesoMochila para obtener el peso total que carga en la mochila el personaje
     * TODO recorrer la lista de items para obtener el peso total de todos y devolverlo
     * @return double suma
     */
    public double getPesoMochila() {
        double suma=0.0;
        for (int i = 0; i<items.length;i++){
            if (items[i] != null) {
                suma += items[i].getPeso();
            }
        }
        return suma;
    }

    /**
     * Método getValorMochila para obtener el valor total que lleva entre todos los items el personaje
     * TODO recorrer la lista de items para obtener el valor total de todos y devolverlo
     * @return double suma
     */
    public double getValorMochila() {
        double suma=0.0;
        for (int i = 0; i<items.length;i++){
            if (items[i] != null) {
                suma += items[i].getValor();
            }
        }
        return suma;
    }

    /**
     * Método infoMochila para obtener en formato String la información de la mochila
     * TODO recorrer toda la lista de items del personaje para ir añadiendo la información de los items según el
     *  formato mostrado en la memoria. P.e. "Mochila de Edgar:
     *                                        Espada Mágica Peso: 1.5, Valor: 100
     *                                        Armadura de Gromril Peso: 4, Valor: 300
     *                                        Peso total: 5.5 Kg
     *                                        Tu mochila vale 400 monedas"
     * @return String inf
     */
    public String infoMochila() {
        String inf="";
        System.out.println("Mochila de "+nombre+":");
        for (int i =0; i< items.length;i++){
            if (items[i] != null) {
                inf = items[i].getDescripcion() + " Peso: " + items[i].getPeso() + ", Valor: " + items[i].getValor() + "\n";
                System.out.println(inf);
            }
        }
        System.out.println("Peso total "+getPesoMochila());
        System.out.println("Tu mochila vale "+getValorMochila()+" monedas");

        return inf;
    }
}
