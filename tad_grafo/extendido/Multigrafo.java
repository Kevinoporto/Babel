package tad_grafo.extendido;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import tad_grafo.general.GrafoNoDirigido;

/**
 * Clase que extiende del tad_grafo general GrafoNoDirigido, para la implementacion de un Multigrafo.
 * ademas agraga la implementacion del algoritmo A*.
 */

public class Multigrafo extends GrafoNoDirigido {
  private List<AristaExtendida> aristasExt;
  private List<VerticeExtendido> verticesExt;

  /*
   * Extrae los metodos de GrafoNoDirigido y cambia los vertices y aristas por vertices Extendidos y Aristas Extendidas.
   */
  public Multigrafo() {
    super();
    this.verticesExt = new ArrayList<>();
    this.aristasExt = new ArrayList<>();
  }
 
  @Override
  public boolean estaVertice(String id) {
    for (VerticeExtendido vertice : verticesExt) {
      if (vertice.getId().equals(id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean estaLado(String u, String v) {
    for (AristaExtendida arista : aristasExt) {
      String Extremo1 = arista.getExtremo1().getId();
      String Extremo2 = arista.getExtremo2().getId();
      if ((Extremo1.equals(u) && Extremo2.equals(v)) || (Extremo1.equals(v) && Extremo2.equals(u))) {
        return true;
      }
    }
    return false;
  }
  // Verifica si una palabra esta en la Lista de Aristas Extendidas.
  public boolean estaPalabra(String palabra) {
    for (AristaExtendida arista : aristasExt) {
      if (arista.getPalabra().equals(palabra))
        return true;
    }
    return false;
  }
  
  /*
   * Busca y retorna un vertice dentro de la lista de Vertices Extendidos, dado un id.
   */
  public VerticeExtendido obtenerVertice(String id) {
    for (VerticeExtendido vertice : verticesExt) {
      if (vertice.getId().equals(id)) {
        return vertice;
      }
    }
    throw new NoSuchElementException(id);
  }
  /*
   * Busca y retorna un vertice dentro de la lista de Vertices Extendidos, dada una palabra.
   */
  public VerticeExtendido obtenerVerticePorPalabra(String palabra) {
    for (VerticeExtendido vertice : verticesExt) {
      if (vertice.getPalabra().equals(palabra))
        return vertice;
    }
    return null;
  }

  // Agrega un vertice a la Lista de vertices extendidos.
  public boolean agregarVertice(VerticeExtendido v) {
    super.nVertices++;
    return verticesExt.add(v);
  }

  public boolean agregarVertice(String id, double peso, String palabra) {
    if (estaVertice(id))
      return false;

    VerticeExtendido nuevoVertice = new VerticeExtendido(id, peso, palabra);
    return agregarVertice(nuevoVertice);
  }

  public boolean agregarArista(AristaExtendida a) {
    super.nAristas++;
    return aristasExt.add(a);
  }

  /**
   * Agrga una arista al grafo
   * 
   * @param u,v     Extremos a conectar.
   * @param id      identificador de la arista a insertar.
   * @param peso    Peso de la arista a insertar.
   * @param palabra Palabra de la arista a insertar.
   * @return false en caso de que ya se encuentre la arista o no se encuentre
   *         alguno de los
   *         vertices a conectar.
   */
  public boolean agregarArista(String id, double peso, String u, String v, String palabra) {
    if (estaPalabra(palabra) || (!estaVertice(u)) || (!estaVertice(v)))
      return false;

    VerticeExtendido j = obtenerVertice(u);
    VerticeExtendido k = obtenerVertice(v);

    AristaExtendida nuevaArista = new AristaExtendida(id, peso, j, k, palabra);
    return agregarArista(nuevaArista);
  }

  /**
   * Verifica las aristas que inciden en un vertice con id dado.
   * @param u id del vertice a consultar
   * @return lista de aristas extendidas incidentes.
   */
  public List<AristaExtendida> aristasDeUnVertice(String u) {
    List<AristaExtendida> aristas = new ArrayList<>();

    for (AristaExtendida arista : aristasExt) {
      String id1 = arista.getExtremo1().getId();
      String id2 = arista.getExtremo2().getId();
      boolean esDelVertice = id1.equals(u) || id2.equals(u);

      if (esDelVertice)
        aristas.add(arista);
    }

    return aristas;
  }

  public List<VerticeExtendido> verticesExt() {
    return verticesExt;
  }

  public List<AristaExtendida> aristaExt() {
    return aristasExt;
  }

 /**

    Este método implementa el algoritmo de búsqueda A* para encontrar el camino de menor costo entre dos vértices en un grafo
    ponderado y dirigido. El costo de cada arista se determina a través de la función heurísticaCostoArista.

    @param idInicial el identificador del vértice de inicio del camino.
    @param idFinal el identificador del vértice final del camino.
    @return una lista de objetos AristaExtendida que forman el camino de menor costo entre el vértice de inicio y el vértice final.
    Si no se encuentra un camino válido, devuelve null.
  */

  public List<AristaExtendida> aEstrella(String idInicial, String idFinal) {
    List<List<AristaExtendida>> caminosAbiertos = new ArrayList<>();
    List<List<AristaExtendida>> caminosCerrado = new ArrayList<>();
    boolean[] aristaTomada = new boolean[nAristas];
    boolean[] verticeVisitado = new boolean[nVertices];
    VerticeExtendido verticeInicial = obtenerVertice(idInicial);

    verticeVisitado[verticesExt().indexOf(verticeInicial)] = true;

    for (AristaExtendida arista : aristasDeUnVertice(idInicial)) {
      List<AristaExtendida> camino = new ArrayList<>();
      camino.add(arista);
      aristaTomada[aristaExt().indexOf(arista)] = true;
      caminosAbiertos.add(camino);
    }

    while (caminosAbiertos.size() > 0) {
      List<AristaExtendida> caminoActual = heuristicaMenorCosto(caminosAbiertos);
      List<VerticeExtendido> verticesEnCamino = verticesEnCamino(verticeInicial, caminoActual);
      VerticeExtendido verticeFinalCamino = verticesEnCamino.get(verticesEnCamino.size() - 1);
      int indiceVerticeFinal = verticesExt().indexOf(verticeFinalCamino);

      if (verticeFinalCamino.getId().equals(idFinal))
        caminosCerrado.add(caminoActual);

      verticeVisitado[indiceVerticeFinal] = true;
      for (AristaExtendida arista : aristasDeUnVertice(verticeFinalCamino.getId())) {
        if (!aristaTomada[aristaExt().indexOf(arista)]) {
          aristaTomada[aristaExt().indexOf(arista)] = true;
          List<AristaExtendida> nuevoCaminoAbierto = new ArrayList<>(caminoActual);
          nuevoCaminoAbierto.add(arista);
          caminosAbiertos.add(nuevoCaminoAbierto);
        }
      }

    }

    List<AristaExtendida> caminoMenorCosto = heuristicaMenorCosto(caminosCerrado);
    if (heuristicaCostoCamino(caminoMenorCosto) == Double.MAX_VALUE)
      return null;

    return caminoMenorCosto;
  }

  /**

    Este método devuelve una lista de vértices que forman parte del camino actual que comienza en el vértice inicial
    especificado y sigue a través de las aristas especificadas en la lista de aristas caminoActual.
    
    @param verticeInicial el vértice inicial del camino.
    @param caminoActual la lista de aristas que forman el camino actual.
    @return una lista de vértices que forman parte del camino actual.
    */

  private List<VerticeExtendido> verticesEnCamino(VerticeExtendido verticeInicial, List<AristaExtendida> caminoActual) {
    List<VerticeExtendido> verticesEnCamino = new ArrayList<>();
    VerticeExtendido ex1 = caminoActual.get(0).getExtremo1();
    VerticeExtendido ex2 = caminoActual.get(0).getExtremo2();
    if (ex1.equals(verticeInicial)) {
      verticesEnCamino.add(ex1);
      verticesEnCamino.add(ex2);
    } else {
      verticesEnCamino.add(ex2);
      verticesEnCamino.add(ex1);
    }

    for (int i = 1; i < caminoActual.size(); i++) {
      AristaExtendida arista1 = caminoActual.get(i);
      VerticeExtendido ar1Ex1 = arista1.getExtremo1();
      VerticeExtendido ar1Ex2 = arista1.getExtremo2();

      if (ar1Ex1.equals(ex1) || ar1Ex1.equals(ex2)) {
        verticesEnCamino.add(ar1Ex2);
      } else {
        verticesEnCamino.add(ar1Ex1);
      }

      ex1 = ar1Ex1;
      ex2 = ar1Ex2;
    }
    return verticesEnCamino;
  }

  /**
   * Calcula el costo de una lista de aristas (camino).
   *
   * @param camino Lista de Aristas correspondientes a un camino desde un idioma a otro idioma
   * @return La suma de los costos de las aristas correspondientes al camino. Si dos aristas
   * consecutivas comienzan con la misma letra retorna infinito. Y si solo hay una arista en el 
   * camino, retorna el peso de esa arista.
   */
  public double heuristicaCostoCamino(List<AristaExtendida> camino) {
    double costo = camino.get(0).getPeso();
    if (camino.size() == 1)
      return costo;

    for (int i = 0; i < camino.size() - 1; i++) {
      Character letra1 = camino.get(i).getPalabra().charAt(0);
      Character letra2 = camino.get(i + 1).getPalabra().charAt(0);
      if (letra1 == letra2)
        return Double.MAX_VALUE;

      costo += camino.get(i + 1).getPeso();
    }

    return costo;
  }

  /**
   * Encuentra el camino de menor costo entre dos idiomas.
   * @param caminos Lista de posibles caminos entre dos idiomas.
   * @return El camino de menor costo de la lista de entrada.
   */
  private List<AristaExtendida> heuristicaMenorCosto(List<List<AristaExtendida>> caminos) {
    List<AristaExtendida> caminoMenorCosto = caminos.get(0);

    double menorCosto = heuristicaCostoCamino(caminoMenorCosto);

    for (List<AristaExtendida> camino : caminos) {
      double costoCaminoActual = heuristicaCostoCamino(camino);
      if (costoCaminoActual < menorCosto) {
        menorCosto = costoCaminoActual;
        caminoMenorCosto = camino;
      }
    }

    caminos.remove(caminoMenorCosto);
    return caminoMenorCosto;
  }

}
