/**
 * Clase Trampa
 */
public class Trampa {
    private final String descripcion;
    private final int danyo;

    /**
     * Constructor de la clase Trampa
     *
     * @param descripcion Descripcion de la trampa
     * @param danyo       Daño de la trampa
     */
    public Trampa(String descripcion, int danyo) {
        this.descripcion = descripcion;
        this.danyo = danyo;
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
     * Método getDanyo
     *
     * @return int danyo
     */
    public int getDanyo() {
        return danyo;
    }
}
