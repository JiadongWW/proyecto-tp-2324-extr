import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Motor
 */
public class Motor {
    Sala[][] mapa;
    private final int maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala;

    /**
     * Constructor Clase Motor
     *
     * @param filas               Numero de filas que tiene el mapa
     * @param columnas            Numero de columnas que tiene el mapa
     * @param maxItemsPorSala     Numero maximo de items por sala
     * @param maxMonstruosPorSala Numero maximo de monstruos por sala
     * @param maxTrampasPorSalas  Numero macimo de trampas por sala
     */
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        this.mapa = new Sala[filas][columnas];
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;

    }

    /**
     * Clase cargarMapa para construir la matriz de mapa a traves del fichero
     * TODO leer los datos del fichero de mapa pasado por parametro y generar una matriz Sala[][]
     *  con dimension Sala[fila][columna] e inicializar la sala con los valores con la descripción del fichero
     *  y los parámetros de maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala.
     *
     * @param ficheroMapa Nombre del fichero que contiene la informacion a leer
     * @return sala generada
     */
    Sala[][] cargarMapa(String ficheroMapa) {
        Scanner sc = null;
        String linea;
        String[] cadena;
        try {
            sc = new Scanner(new FileReader(ficheroMapa));
            while (sc.hasNext()) {
                linea = sc.nextLine();
                cadena = linea.split(";");
                int fila = Integer.parseInt(cadena[0]);
                int columna = Integer.parseInt(cadena[1]);
                String descripcion = cadena[2];
                mapa[fila][columna] = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, fila, columna);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + ficheroMapa + " no encontrado");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return mapa;
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * TODO Método para leer un fichero de items pasado por parámetro y según
     *  la fila y columna introducir el item en la sala.
     *
     * @param ficheroItems Nombre del fichero que contiene la informacion a leer
     */
    private void cargarItems(String ficheroItems) {
        Scanner sc = null;
        String linea;
        String[] cadena;
        try {
            sc = new Scanner(new FileReader(ficheroItems));
            while (sc.hasNext()) {
                linea = sc.nextLine();
                cadena = linea.split(";");
                int fila = Integer.parseInt(cadena[0]);
                int columna = Integer.parseInt(cadena[1]);
                String descripcion = cadena[2];
                double valor = Double.parseDouble(cadena[3]);
                double peso = Double.parseDouble(cadena[4]);
                Item item = new Item(descripcion, peso, valor);
                mapa[fila][columna].agregarItem(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + ficheroItems + " no encontrado");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Método cargarMonstruos para agregar los monstruos del fichero en el mapa
     * TODO Método para leer un fichero de Monstruos pasado por parámetro y según
     *  la fila y columna introducir el monstruo en la sala.
     *
     * @param ficheroMonstruos Nombre del fichero que contiene la informacion a leer
     */
    private void cargarMonstruos(String ficheroMonstruos) {
        Scanner sc = null;
        String linea;
        String[] cadena;
        try {
            sc = new Scanner(new FileReader(ficheroMonstruos));
            while (sc.hasNext()) {
                linea = sc.nextLine();
                cadena = linea.split(";");
                int fila = Integer.parseInt(cadena[0]);
                int columna = Integer.parseInt(cadena[1]);
                String nombre = cadena[2];
                int vida = Integer.parseInt(cadena[3]);
                int ataque = Integer.parseInt(cadena[4]);
                int defensa = Integer.parseInt(cadena[5]);
                Monstruo monstruo = new Monstruo(nombre, vida, ataque, defensa);
                mapa[fila][columna].agregarMonstruo(monstruo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + ficheroMonstruos + " no encontrado");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Método cargarTrampas para agregar las trampas del fichero en el mapa
     * TODO Método para leer un fichero de trampas pasado por parámetro y según
     *   la fila y columna introducir la trampa en la sala.
     *
     * @param ficheroTrampas Nombre del fichero que contiene la informacion a leer
     */
    private void cargarTrampas(String ficheroTrampas) {
        Scanner sc = null;
        String linea;
        String[] cadena;
        try {
            sc = new Scanner(new FileReader(ficheroTrampas));
            while (sc.hasNext()) {
                linea = sc.nextLine();
                cadena = linea.split(";");
                int fila = Integer.parseInt(cadena[0]);
                int columna = Integer.parseInt(cadena[1]);
                String descripcion = cadena[2];
                int danyo = Integer.parseInt(cadena[3]);
                Trampa trampa = new Trampa(descripcion, danyo);
                mapa[fila][columna].agregarTrampa(trampa);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + ficheroTrampas + " no encontrado");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Metodo iniciar, para preparar el mapa
     * TODO instanciación del parametro mapa y carga de datos con los ficheros pasados como parámetros
     *
     * @param ficheroMapa      Nombre del fichero de las salas que contiene la informacion a cagar
     * @param ficheroItems     Nombre del fichero de los items que contiene la informacion a caga
     * @param ficheroMonstruos Nombre del fichero de los monstruos que contiene la informacion a caga
     * @param ficheroTrampas   Nombre del fichero de las trampas que contiene la informacion a caga
     */
    public void iniciar(String ficheroMapa, String ficheroItems, String ficheroMonstruos, String ficheroTrampas) {
        cargarMapa(ficheroMapa);
        cargarItems(ficheroItems);
        cargarMonstruos(ficheroMonstruos);
        cargarTrampas(ficheroTrampas);
    }

    /**
     * Método getSala para obtener una sala concreta del mapa
     * TODO devolver una Sala concreta del mapa
     *
     * @param fila    Numero de fila de la matriz mapa pasado por parametro
     * @param columna Numero de columna de la matriz mapa pasado por parametro
     * @return Sala mapa[fila][columna]
     */
    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    /**
     * Método mostrarMapa para transformar el mapa en String
     * TODO construir un String con la información contenida en el mapa
     *  respetando el formato que aparece en la memoria de la práctica
     *
     * @param fila    Numero de filas de la matriz mapa pasado por parametro
     * @param columna Numero de columnas de la matriz mapa pasado por parametro
     * @return String str
     */
    public String mostrarMapa(int fila, int columna) {
        String str = "";
        str += "╔";
        for (int i = 0; i < mapa[0].length; i++) {
            str += "═";
        }
        str += "╗\n";

        for (int i = 0; i < mapa.length; i++) {
            str += "║";
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == fila && j == columna) {
                    str += "@";
                } else if (mapa[i][j] != null) {
                    str += "░";
                } else {
                    str += " ";
                }
            }
            str += "║\n";
        }

        str += "╚";
        for (int i = 0; i < mapa[0].length; i++) {
            str += "═";
        }
        str += "╝\n";

        return str;
    }

    /**
     * Método jugar para empezar a jugar con el personaje
     * TODO método complejo en el que hay que seguir la siguiente ejecución:
     *  1. mostrar el mapa por pantalla
     *  2. Obtener la sala actual y mientras el personaje tenga vida y no haya llegado a la casilla final
     *  3. Durante una jugada mostrar la descripcion de la sala actual
     *  4. Comprobar si hay monstruos en la sala y si es así entrar en combate
     *  4.a El combate acaba cuando la vida del monstruo o la vida del personaje llega a 0
     *  4.b cada turno en el combate el personaje ataca al monstruo y restamos su vida
     *  4.c Si la vida no llega a 0 el monstruo hace daño al personaje
     *  5. Las salas pueden tener trampas
     *  5.a Si hay trampa hay que comprobar si un valor aleatorio entre 1 y 50 es inferior a la destreza del personaje, si es asi esquiva la trampa
     *  5.b Si no esquiva la trampa el personaje recibe daño
     *  5.c al igual que en combate hay que tener en cuenta si la vida del personaje lleva a 0
     *  6. Por último puede haber items en la sala, en cuyo caso habrá que preguntar al usuario qué ítems quiere guardarse (o NINGUNO para terminar)
     *  ¡IMPORTANTE! se debe mostrar por pantalla avisos para cada opción dando feedback al usuario de todo lo que ocurra (consultar enunciado)
     *
     * @param teclado   Permite introducir informacion a traves de la consola
     * @param personaje Personaje pasado por parametro
     * @param random    Randomizador pasado por parametro
     */
    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        int fila = 0;
        int columna = 0;
        Sala actual = mapa[fila][columna];

        while (personaje.getVida() > 0) {
            System.out.println(mostrarMapa(fila, columna));
            System.out.println("Estas en la sala " + actual.getDescripcion());
            while (actual.hayMonstruos()) {
                Monstruo monstruo = actual.seleccionarMonstruo(teclado);
                while (monstruo.getVida() > 0 && personaje.getVida() > 0) {
                    System.out.println(personaje + " ataca a " + monstruo + " con " + personaje.getAtaque() +
                            " puntos de daño");
                    monstruo.recibirDanyo(personaje.getAtaque());
                    if (monstruo.getVida() > 0) {
                        System.out.println(monstruo + " ataca a " + personaje + " con " + monstruo.getAtaque() +
                                " puntos de daño");
                        personaje.recibirDanyo(monstruo.getAtaque());
                    } else {
                        System.out.println("¡Has derrotado al monstruo!");
                    }
                }
                if (monstruo.getVida() < 0) {
                    actual.eliminarMonstruo(monstruo.getNombre());
                } else {
                    System.out.println(personaje.getNombre() + " ha muerto");
                }
            }

            if (actual.hayTrampas()) {
                Trampa[] trampa = actual.getTrampas();
                if (personaje.getVida() > 0) {
                    for (int i = 0; i < trampa.length; i++) {
                        if (trampa[i] != null) {
                            int numAleatorio = random.nextInt(51);
                            if (numAleatorio <= personaje.getDestreza()) {
                                System.out.println("¡Has esquivado la trampa! " + trampa[i].getDescripcion());
                            } else {
                                System.out.println("¡Has caido en una trampa! " + trampa[i].getDescripcion());
                                personaje.recibirDanyo(trampa[i].getDanyo());
                                System.out.println("Te ha hecho " + trampa[i].getDanyo() + " puntos de daño");
                            }
                        }
                    }
                } else {
                    System.out.println(personaje.getNombre() + " ha muerto");
                }
            }

            if (actual.hayItems()) {
                Item item = actual.seleccionarItem(teclado);
                if (personaje.anyadirItem(item)) {
                    System.out.println("¡Te guardas el objeto! | " + item.toString() + " |");
                    actual.eliminarItem(item.getDescripcion());
                    personaje.infoMochila();
                } else personaje.infoMochila();

            }
            if (personaje.getVida() > 0 && (fila == mapa.length - 1 && columna == mapa[0].length - 1)) {
                System.out.println("¡Has encontrado la salida del mapa!");
                return;
            }
            if (!((fila == mapa.length - 1 && columna == mapa[0].length - 1))) {
                Sala sala = seleccionarMovimiento(teclado, actual);
                if (sala != null) {
                    actual = sala;
                    fila = actual.getFila();
                    columna = actual.getColumna();
                }
            }
        }

    }

    /**
     * Metodo seleccionarMovimiento para establecer las acciones que tome el jugador con su personaje
     * TODO El desplazamiento del personaje se entiende como norte (N), sur (S), este (E) u oeste (O)
     *  en este método hay que capturar por pantalla la acción que va a tomar el usuario de entre las posibles
     *  para ello hay que tener en cuenta que se debe avisar al usuario si puede realizar o no la acción.
     *  Se devolverá la sala destino a la que se ha movido el personaje.
     *
     * @param teclado    Permite introducir informacion a traves de la consola
     * @param salaActual sala en la que el personaje esta actualmente
     * @return Sala mapa[fila][columna]
     */
    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {
        boolean valido = false;
        int fila = salaActual.getFila();
        int columna = salaActual.getColumna();
        String direccion;

        while (!valido) {
            System.out.println("Introduce el movimiento (N,E,S,O): ");
            direccion = teclado.nextLine();
            if (direccion.equals("N")) {
                if (fila > 0) {
                    fila--;
                    valido = true;
                } else {
                    System.out.println("No puedes moverte al norte");
                }
            } else if (direccion.equals("E")) {
                if (columna < mapa[0].length - 1) {
                    columna++;
                    valido = true;
                } else {
                    System.out.println("No puedes moverte al este");
                }
            } else if (direccion.equals("S")) {
                if (fila < mapa.length - 1) {
                    fila++;
                    valido = true;
                } else {
                    System.out.println("No puedes moverte al sur");
                }
            } else if (direccion.equals("O")) {
                if (columna > 0) {
                    columna--;
                    valido = true;
                } else {
                    System.out.println("No puedes moverte al oeste");
                }
            }
            if (mapa[fila][columna] == null) {
                System.out.println("No existe la sala. Introduce de nuevo el movimiento (N,E,S,O): ");
            }
        }

        return mapa[fila][columna];
    }
}
