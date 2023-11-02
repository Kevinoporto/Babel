package tad_grafo.general;

/**
 * Esta clase representa un vértice en un grafo.
 */

public class Vertice {
  private String id;
  private double peso;

  /**
   * Constructor de la clase
   *
   * @param id   El id del vértice.
   * @param peso El peso del vértice.
   */
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
  }

  /**
   * Construye un nuevo vértice con el id y peso dados.
   *
   * @param id   El id del vértice.
   * @param peso El peso del vértice.
   */
  public Vertice crearVertice(String id, double p) {
    Vertice nuevoVertice = new Vertice(id, p);
    return nuevoVertice;
  }

  /**
   * Devuelve el peso de este vértice.
   *
   * @return El peso de este vértice.
   */
  public double getPeso() {
    return peso;
  }

  /**
   * Devuelve el id de este vértice.
   *
   * @return El id de este vértice.
   */
  public String getId() {
    return id;
  }

  /**
   * Devuelve una representación en string de este vértice.
   *
   * @return Una representación en string de este vértice.
   */
  public String toString() {
    String string = String.format("id: %d, peso: %d", id, peso);
    return string;
  }
}
