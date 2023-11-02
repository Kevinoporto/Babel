package tad_grafo.general;

/**
 * Clase abstracta que representa un lado.
 */

public abstract class Lado {
  private String id;
  private double peso;

  /**
   * Constructor de la clase.
   *
   * @param id   El id del lado.
   * @param peso El peso del lado.
   */
  public Lado(String id, double peso) {
    this.id = id;
    this.peso = peso;
  }

  /**
   * Crea una nueva instancia de la clase y la devuelve.
   *
   * @param id   El id del lado.
   * @param peso El peso del lado.
   * @return Una nueva instancia de la clase.
   */
  public static Lado crearLado(String id, double peso) {
    return new Lado(id, peso) {
      public String toString() {
        String string = String.format("id: %d, peso: %d", getId(), getPeso());
        return string;
      }
    };

  }

  /**
   * Obtiene el id del lado.
   *
   * @return El id del lado.
   */
  public String getId() {
    return id;
  }

  /**
   * Obtiene el peso del lado.
   *
   * @return El peso del lado.
   */
  public double getPeso() {
    return peso;
  }

  /**
   * Devuelve una representación en cadena de este objeto.
   *
   * @return Una representación en cadena de este objeto.
   */
  public abstract String toString();
}
