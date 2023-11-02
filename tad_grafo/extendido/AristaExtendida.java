package tad_grafo.extendido;

import tad_grafo.general.Arista;

/**
 * Extiende de la clase tad-grafo general Arisa y agrega un nuevo atributo "palabra"
 * el cual almacena una palabra.
 */

public class AristaExtendida extends Arista {
  private String palabra;
  private VerticeExtendido extremo1;
  private VerticeExtendido extremo2;

  /**
   * 
   * @param id el indice de la arista.
   * @param peso Costo de la arista (Tamaño de la palabra asignada).
   * @param u primer vertice (Idioma) extremo de la arista.
   * @param v segundo vertice (Idioma) extremo de la arista.
   * @param palabra Palabra en comun entre los dos vertices (Idiomas).
   */
  public AristaExtendida(String id, double peso, VerticeExtendido u, VerticeExtendido v, String palabra) {
    super(id, peso, u, v);
    this.palabra = palabra;
    this.extremo1 = u;
    this.extremo2 = v;
  }

  public String getPalabra() {
    return this.palabra;
  }

  public VerticeExtendido getExtremo1() {
    return this.extremo1;
  }

  public VerticeExtendido getExtremo2() {
    return this.extremo2;
  }
}
