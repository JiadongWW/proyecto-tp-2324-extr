import java.util.Scanner;

/**
 * Clase Sala
 */
public class Sala {
    private final String descripcion;
    private final Item[] items;
    private final Monstruo[] monstruos;
    private final Trampa[] trampas;

    private final int fila;
    private final int columna;

    /**
     * Constructor de clase para inicializar los atributos de clase
     *
     * @param descripcion       Descripcion de la sala
     * @param max_items         Numero maximo de items por sala
     * @param max_monstruos     Numero maximo de monstruos por sala
     * @param maxTrampasPorSala Numero maximo de trampas por sala
     * @param fila              Numero de fila
     * @param columna           Numero de columna
     */
    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion = descripcion;
        this.items = new Item[max_items];
        this.monstruos = new Monstruo[max_monstruos];
        this.trampas = new Trampa[maxTrampasPorSala];
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Método agregarItem para incluir items en la sala
     * TODO comprobar si existe el objeto en la sala o si la lista de items no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     *
     * @param item Permite introducir el item pasado por parametro en la sala
     * @return false en caso de que exista el objeto y true en caso de no existir
     */
    public boolean agregarItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].equals(item)) {
                return false;
            } else if (items[i] == null) {
                items[i] = item;
                return true;
            }
        }
        return false;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala
     * TODO comprobar si existe el monstruo en la sala o si la lista de monstruos no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     *
     * @param monstruo Permite introducir el monstruo pasado por parametro en la sala
     * @return false en caso de que exista el monstruo y true en caso de no existir
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null && monstruos[i].equals(monstruo)) {
                return false;
            } else if (monstruos[i] == null) {
                monstruos[i] = monstruo;
                return true;
            }
        }
        return false;
    }

    /**
     * Método agregarTrampa para incluir una trampa en la sala
     * TODO comprobar si existe la trampa en la sala o si la lista de trampas no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     *
     * @param trampa Permite introducir la trampa pasado por parametro en la sala
     * @return false en caso de que exista la trampa y true en caso de no existir
     */
    public boolean agregarTrampa(Trampa trampa) {
        for (int i = 0; i < trampas.length; i++) {
            if (trampas[i] != null && trampas[i].equals(trampa)) {
                return false;
            } else if (trampas[i] == null) {
                trampas[i] = trampa;
                return true;
            }
        }
        return false;
    }

    /**
     * Método getDescripcion
     *
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método hayMonstruos para comprobar si hay algún monstruo en la sala
     * TODO comprobar si hay algún monstruo en la lista
     *
     * @return true si hay monstruos en la sala y false en caso contrario
     */
    public boolean hayMonstruos() {
        return monstruos[0] != null;
    }

    /**
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo
     * TODO Mostrar por pantalla todos los monstruos y luego solicitar que se introduzca el nombre del monstruo que se
     *  quiere seleccionar.
     *
     * @param teclado Permite introducir informacion a traves de la consola
     * @return Monstruo monstruo
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        String mensaje;
        listarMonstruos();
        mensaje = Utilidades.leerCadena(teclado, "Selecciona un monstruo: ");
        Monstruo monstruo = buscarMonstruo(mensaje);
        while (monstruo == null) {
            mensaje = Utilidades.leerCadena(teclado, "Monstruo no encontrado. Selecciona un monstruo: ");
            monstruo = buscarMonstruo(mensaje);
        }
        return monstruo;
    }

    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo
     * TODO devolver el monstruo según el nombre pasado como parámetro o devolver null si no se encuentra
     *
     * @param nombreMonstruo Busca el monstruo segun el nombre pasado por parametro
     * @return Monstruo monstruo
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null && nombreMonstruo.equals(monstruos[i].getNombre().trim())) {
                return monstruos[i];
            }
        }
        return null;
    }

    /**
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos
     * TODO mostrar por pantalla la info de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null) {
                System.out.println(monstruos[i].toString());
            }
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado
     * TODO buscar en la lista el monstruo segun el nombre pasado como parámetro y eliminarlo.
     *
     * @param nombreMonstruo elimina el monstruo segun el nombre pasado por parametro
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        for (int i = 0; i < monstruos.length; i++) {
            if (monstruos[i] != null && nombreMonstruo.equals(monstruos[i].getNombre())) {
                moverIzquierdaMonstruos(i);
            }
        }
    }

    private void moverIzquierdaMonstruos(int indice) {
        for (int i = indice; i < monstruos.length - 1; i++) {
            monstruos[i] = monstruos[i + 1];
        }
        monstruos[monstruos.length - 1] = null;
    }

    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     * TODO mostrar si existe alguna trampa en la sala, false en caso contrario
     *
     * @return true si hay trampas en la sala y false en caso contrario
     */
    public boolean hayTrampas() {
        return trampas[0] != null;
    }

    /**
     * Método getFila
     *
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método getColumna
     *
     * @return int columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método hayItems para mostrar si existe algún item en la sala
     * TODO buscar si hay algún item en la lista de items, false en caso contrario
     *
     * @return true si hay items en la sala y false en caso contrario
     */
    public boolean hayItems() {
        return items[0] != null;
    }

    /**
     * Método buscarItem para obtener un item según una descripcion dada
     * TODO buscar en la lista de items un item con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     *
     * @param descripcion Busca el item segun la descripcion pasado por parametro
     * @return Item item
     */
    public Item buscarItem(String descripcion) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && descripcion.equals(items[i].getDescripcion().trim())) {
                return items[i];
            }
        }
        return null;
    }

    /**
     * Método buscarTrampa para obtener una trampa según una descripcion dada
     * TODO buscar en la lista de trampas una trampa con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     *
     * @param descripcion Busca la trampa segun la descripcion pasado por parametro
     * @return Trampa trampa
     */
    public Trampa buscarTrampa(String descripcion) {
        for (int i = 0; i < trampas.length; i++) {
            if (trampas[i] != null && descripcion.equals(trampas[i].getDescripcion().trim())) {
                return trampas[i];
            }
        }
        return null;
    }

    /**
     * Método getTrampas
     *
     * @return Trampa[] trampas
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método seleccionarItem para obtener un item concreto con parámetro pasados por pantalla
     * TODO Mostrar por pantalla todos los items de la sala para despues pedir que se introduzca una descripcion del
     *  item que se quiere seleccionar
     *
     * @param teclado Permite introducir informacion a traves de la consola
     * @return Item item
     */
    public Item seleccionarItem(Scanner teclado) {
        String mensaje;
        listarItems();
        mensaje = Utilidades.leerCadena(teclado, "Escribe la descripcion del item que quieres coger (NINGUNO para cancelar): ");
        if (mensaje.equalsIgnoreCase("NINGUNO")) {
            return null;
        }
        Item item = buscarItem(mensaje);
        while (item == null) {
            mensaje = Utilidades.leerCadena(teclado, "Item no encontrado. Escribelo de nuevo (NINGUNO para cancelar): ");
            if (mensaje.equalsIgnoreCase("NINGUNO")) {
                return null;
            }
            item = buscarItem(mensaje);
        }
        return item;
    }

    /**
     * Método listarItems para mostrar por pantalla todos los items
     * TODO utilizar las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     *  items que hay en la sala
     */
    private void listarItems() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println(items[i].toString());
            }
        }
    }

    /**
     * Método eliminarItem para eliminar un item con la descripcion pasada como parámetro
     * TODO buscar el item que coincida con la descripción pasada por parámetro y eliminarlo de la lista de items
     *
     * @param descripcion Elimina el item segun la descripcion para por parametro
     */
    public void eliminarItem(String descripcion) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && descripcion.equals(items[i].getDescripcion())) {
                moverIzquierdaItems(i);
            }
        }
    }

    private void moverIzquierdaItems(int indice) {
        for (int i = indice; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }
        items[items.length - 1] = null;
    }
}
