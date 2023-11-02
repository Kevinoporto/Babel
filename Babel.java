import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tad_grafo.extendido.AristaExtendida;
import tad_grafo.extendido.Multigrafo;
import tad_grafo.extendido.VerticeExtendido;

/**

    La clase InputIdiomas define un objeto que contiene información sobre los idiomas de origen y destino,
    así como las palabras comunes que se comparten entre los idiomas.
*/

class InputIdiomas {
  public int numeroElementos;
  public List<ArrayList<String>> listaIdiomas;
  public List<ArrayList<String[]>> listaPalabrasComunes;
  public List<String> idiomasInicial;
  public List<String> idiomasFinal;

  /**

    Construye un objeto InputIdiomas con listas vacías y nro de elementos iniciado en 0.
  */
  
  public InputIdiomas() {
    numeroElementos = 0;
    listaIdiomas = new ArrayList<>();
    listaPalabrasComunes = new ArrayList<>();
    idiomasInicial = new ArrayList<>();
    idiomasFinal = new ArrayList<>();
  }
}

/*
 * Recibe la entrada especificada al comienzo del codigo, crea un multigrafo y retorna
 * la cadena mas corta desde el idioma inicial, al final con las instrucciones requeridas.
 */

public class Babel {

  /*
   * Verifica si un idioma esta en la lista de idiomas (Vertices del multigrafo).
   */
  private static boolean estaIdioma(ArrayList<String> Idiomas, String idioma) {
    for (String idiomas : Idiomas) {
      if (idiomas.equals(idioma)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @param verticesACrear Lista de idiomas.
   * @param AristasACrear Tripletas, donde las posiciones: [0] Idioma 1 ; [1] Idoma 2 ; [2] Palabra en comun entre los idiomas.
   * @return Multigrafo con vertices (idiomas) y aristas (Si hay una palabra en comun entre dos idiomas).
   */
  private static Multigrafo crearGrafo(ArrayList<String> verticesACrear, ArrayList<String[]> AristasACrear) {
    Multigrafo grafo = new Multigrafo();
    for (int i = 0; i < verticesACrear.size(); i++) {
      String j = Integer.toString(i);
      String verticeNuevo = verticesACrear.get(i);
      grafo.agregarVertice(j, 0, verticeNuevo);
    }
    for (int i = 0; i < AristasACrear.size(); i++) {
      String j = Integer.toString(i);
      String extremo1 = null;
      String extremo2 = null;
      String palabra = AristasACrear.get(i)[2];
      Double costo = Double.parseDouble(Integer.toString(palabra.length()));
      for (VerticeExtendido vertice : grafo.verticesExt()) {
        if (AristasACrear.get(i)[0].equals(vertice.getPalabra())) {
          extremo1 = vertice.getId();
        }
        if (AristasACrear.get(i)[1].equals(vertice.getPalabra())) {
          extremo2 = vertice.getId();
        }
      }
      grafo.agregarArista(j, costo, extremo1, extremo2, palabra);
    }
    return grafo;
  }

  /**
   * 
   * @param path Archivo de texto, que recibe el programa
   * @return Un objeto de tipo listasInput que contiene las listas que se usaran mas adelante.
   */
  
  public static InputIdiomas parseInput(String path) {
    InputIdiomas listasInput = new InputIdiomas();
    BufferedReader lector = null;
    try {
      FileReader archivo = new FileReader(path);
      lector = new BufferedReader(archivo);
      String linea = lector.readLine();

      while (!linea.equals("0")) {
        ArrayList<String> idiomas = new ArrayList<>();
        ArrayList<String[]> palabrasComunes = new ArrayList<>();

        if (linea == null )
          throw new IllegalArgumentException("Error en el formato de entrada");

        int nroPalabras = Integer.parseInt(linea);
        String linea2 = lector.readLine();
        String[] elementos = linea2.split(" ");
        if (elementos.length != 2)
          throw new IllegalArgumentException("Se deben especificar los idiomas a conectar");
        String I1 = elementos[0];
        String I2 = elementos[1];

        listasInput.idiomasInicial.add(I1);
        listasInput.idiomasFinal.add(I2);

        for (int i = 0; i < nroPalabras; i++) {
          linea = lector.readLine();
          elementos = linea.split(" ");
          if ((!estaIdioma(idiomas, elementos[0]))) {
            idiomas.add(elementos[0]);
          }
          if ((!estaIdioma(idiomas, elementos[1]))) {
            idiomas.add(elementos[1]);
          }
          palabrasComunes.add(elementos);
        }

        listasInput.listaIdiomas.add(idiomas);
        listasInput.listaPalabrasComunes.add(palabrasComunes);
        listasInput.numeroElementos++;

        linea = lector.readLine();
      }

    } catch (IOException e) {
      System.out.println("Error al leer el arvhivo: " + e.getMessage());
    } finally {
      if (lector != null)
        try {
          lector.close();
        } catch (IOException e) {
          System.err.println("Error cerrando el archivo: " + e.getMessage());
        }
    }

    return listasInput;
  }

  public static void main(String[] args) {
    InputIdiomas listasInput = parseInput(args[0]);

    for (int i = 0; i < listasInput.numeroElementos; i++) {
      ArrayList<String> idiomas = listasInput.listaIdiomas.get(i);
      ArrayList<String[]> palabrasComunes = listasInput.listaPalabrasComunes.get(i);
      String idiomaInicial = listasInput.idiomasInicial.get(i);
      String idiomaFinal = listasInput.idiomasFinal.get(i);

      Multigrafo grafo = crearGrafo(idiomas, palabrasComunes);

      VerticeExtendido verticeInicial = grafo.obtenerVerticePorPalabra(idiomaInicial);
      VerticeExtendido verticeFinal = grafo.obtenerVerticePorPalabra(idiomaFinal);

      if (verticeInicial == null || verticeFinal == null) {
        System.out.println("imposible");
        continue;
      }

      List<AristaExtendida> camino = grafo.aEstrella(verticeInicial.getId(), verticeFinal.getId());

      if (camino == null || camino.size() == 0) {
        System.out.println("imposible");
        continue;
      }

      String palabrasOutput = "";
      Double costo = 0.0;
      for (AristaExtendida arista : camino) {
        costo += arista.getPeso();
        palabrasOutput += camino.indexOf(arista) == camino.size() - 1 ? String.format("%s", arista.getPalabra())
            : String.format("%s ", arista.getPalabra());
      }

      System.out.printf("%.0f \"%s\"\n", costo, palabrasOutput);
    }
  }
}
