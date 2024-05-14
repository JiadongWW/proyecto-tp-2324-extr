/**
 * Clase Item
 */
public class Item {
    private final double peso;
    private final double valor;
    private final String descripcion;

    /**
     * Constructor de la clase item
     * @param descripcion
     * @param peso
     * @param valor
     */
    public Item(String descripcion, double peso, double valor) {
        this.descripcion = descripcion;
        this.peso = peso;
        this.valor = valor;
    }

    /**
     * Método getPeso
     * @return double peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Método getValor
     * @return double valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * Método getDescripcion
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método sobreescrito para devolver la información de un item
     * TODO Método para devolver un String con la información del item en el formato
     *  descrito en la memoria de la práctica P.e: "Espada Mágica (Peso: 1,5, Valor: 100,0)"
     * @return
     */
    @Override
    public String toString() {
        return descripcion+" (Peso: "+peso+", Valor: "+valor+")";
    }

    /**
     * Método que sobreescribe el comportamiento de equals
     *  TODO Método para comparar si el objeto pasado como parámetro es igual a este,
     *      hay que comparar los parámetros internos del objeto (peso, valor, descripción)
     * @param obj
     * @return True en caso de ser igual, false en otro caso
     */
    @Override
    public boolean equals(Object obj) {
        boolean resul=false;
        if (obj instanceof Item){
            Item item = (Item) obj;
            if (this.peso==item.getPeso() && this.valor==item.getValor() && this.descripcion.equals(item.getDescripcion())){
                resul = true;
            }else resul = false;
        }
        return resul;
    }
}
