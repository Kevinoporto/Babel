package tad_grafo.general;

import java.util.List;

/**
 *
 */

public class ClienteGrafo {
  public static void main(String[] args) {
    Digrafo D = Digrafo.crearDigrafo();

    D.cargarGrafo("./test.txt");

    System.out.printf("%d, %d, %b\n", D.numeroDeVertices(), D.numeroDeLados(), D.estaVertice("1"));
    List<Vertice> vertices = D.vertices();
    System.out.println("Adyacentes: ");
    for (Vertice vertice : vertices) {
      System.out.printf("%s: ", vertice.getId());
      List<Vertice> adyacentes = D.adyacentes(vertice.getId());
      for (Vertice adyacente : adyacentes) {
        System.out.printf("%s ", adyacente.getId());
      }
      System.out.println();
    }

    System.out.println(D.toString());
  }
}