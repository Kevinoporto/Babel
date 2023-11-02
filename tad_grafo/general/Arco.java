package tad_grafo.general;

/**
 * Clase que representa un arco en un grafo.
 */

public class Arco extends Lado {
  private Vertice extremoInicial;
  private Vertice extremoFinal;

  /**
   * Constructor de la clase Arco.
   * 
   * @param id             Identificador del arco.
   * @param peso           Peso del arco.
   * @param extremoInicial Vértice inicial del arco.
   * @param extremoFinal   Vértice final del arco.
   */
  public Arco(String id, double peso, Vertice extremoInicial, Vertice extremoFinal) {
    super(id, peso);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  /**
   * Crea un nuevo arco.
   * 
   * @param id Identificador del arco.
   * @param p  Peso del arco.
   * @param vi Vértice inicial del arco.
   * @param vf Vértice final del arco.
   * @return Nuevo objeto de la clase Arco.
   */
  public Arco crearArco(String id, double p, Vertice vi, Vertice vf) {
    Arco nuevoArco = new Arco(id, p, vi, vf);
    return nuevoArco;
  }

  /**
   * Obtiene el vértice inicial del arco.
   * 
   * @return Vértice inicial del arco.
   */
  public Vertice getExtremoInicial() {
    return extremoInicial;
  }

  /**
   * Obtiene el vértice final del arco.
   * 
   * @return Vértice final del arco.
   */
  public Vertice getExtremoFinal() {
    return extremoFinal;
  }

  /**
   * Devuelve una cadena que representa el objeto actual.
   *
   * @return Cadena que representa el objeto actual.
   */
  public String toString() {
    String string = String.format("id: %d, peso: %d, vértice inicial: %d, vértice final: %d", this.getId(),
        this.getPeso(), extremoInicial, extremoFinal);
    return string;
  }
}