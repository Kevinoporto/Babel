package tad_grafo.extendido;

import tad_grafo.general.Vertice;

/**
 * Extiende de la calse tad-grafo general Vertice y agraga un nuevo atributo "palabra"
 * que almacena un idioma.
 */
public class VerticeExtendido extends Vertice {
  private String palabra;

  /**
   * 
   * @param id Indice del vertice.
   * @param peso Peso del vertice.
   * @param palabra Idioma asociado al vertice (Sera idioma unico para cada vertice).
   */
  public VerticeExtendido(String id, double peso, String palabra) {
    super(id, peso);
    this.palabra = palabra;
  }

  public String getPalabra() {
    return this.palabra;
  }
}
