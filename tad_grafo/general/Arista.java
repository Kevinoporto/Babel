package tad_grafo.general;

/**
 * Clase que representa una arista en un grafo.
 */
public class Arista extends Lado {
  private Vertice u;
  private Vertice v;

  /**
   * Constructor de la clase Arista.
   * 
   * @param id   Identificador de la arista.
   * @param peso Peso de la arista.
   * @param u    Vértice inicial de la arista.
   * @param v    Vértice final de la arista.
   */
  public Arista(String id, double peso, Vertice u, Vertice v) {
    super(id, peso);
    this.u = u;
    this.v = v;
  }

  /**
   * Crea una nueva arista.
   * 
   * @param id Identificador de la arista.
   * @param p  Peso de la arista.
   * @param u  Vértice inicial de la arista.
   * @param v  Vértice final de la arista.
   * @return Nuevo objeto de la clase Arista.
   */
  public Arista crearArista(String id, double p, Vertice u, Vertice v) {
    Arista nuevaArista = new Arista(id, p, u, v);
    return nuevaArista;
  }

  /**
   * Obtiene el vértice inicial de la arista.
   * 
   * @return Vértice inicial de la arista.
   */
  public Vertice getExtremo1() {
    return u;
  }

  /**
   * Obtiene el vértice final de la arista.
   * 
   * @return Vértice final de la arista.
   */
  public Vertice getExtremo2() {
    return v;
  }

  /**
   * Devuelve una cadena que representa el objeto actual.
   *
   * @return Cadena que representa el objeto actual.
   */
  public String toString() {
    String string = String.format("id: %d, peso: %d, extremo 1: %d, extremo 2: %d", this.getId(),
        this.getPeso(), u, v);
    return string;
  }
}