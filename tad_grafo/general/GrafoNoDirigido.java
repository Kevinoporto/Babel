package tad_grafo.general;

/**
 * Clase que representa un grafo no dirigido.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GrafoNoDirigido implements Grafo {
    protected int nVertices;
    protected int nAristas;
    private List<Vertice> vertices;
    private List<Arista> aristas;

    public GrafoNoDirigido() {
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.nVertices = 0;
        this.nAristas = 0;
    }

    /***
     * Constructor de la clase GrafoNoDirigido ***
     * 
     * @param nVertices Número de vértices del grafo.
     * @param nAristas  Número de aristas del grafo.
     * @param vertices  Lista de vértices del grafo.
     * @param aristas   Lista de aristas del grafo.
     */

    public GrafoNoDirigido(int nVertices, int nAristas, List<Vertice> vertices, List<Arista> aristas) {
        this.nVertices = nVertices;
        this.nAristas = nAristas;
        this.vertices = vertices;
        this.aristas = aristas;
    }

    /***
     * Crea un grafo no dirigido ***
     * 
     * @return Grafo no dirigido.
     */
    public static GrafoNoDirigido crearGrafoNoDirigido() {
        List<Vertice> nuevosVertices = new ArrayList<>();
        List<Arista> nuevasAristas = new ArrayList<>();
        GrafoNoDirigido nuevoGrafoNoDirigido = new GrafoNoDirigido(0, 0, nuevosVertices, nuevasAristas);
        return nuevoGrafoNoDirigido;
    }

    /***
     * Carga un grafo desde un archivo ***
     * 
     * @param dirArchivo la ruta del archivo que contiene los datos del grafo.
     * @return true si se cargó correctamente el grafo, false en caso
     *         contrario.
     */

    public boolean cargarGrafo(String dirArchivo) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dirArchivo));
            String line = br.readLine();
            nVertices = Integer.parseInt(line);
            line = br.readLine();
            nAristas = Integer.parseInt(line);

            for (int i = 0; i < nVertices; i++) {
                line = br.readLine();
                if (line == null)
                    break;
                String[] datos = line.split(" ");

                if (datos.length != 2)
                    System.err.println("Formato de archivo inválido");

                double peso = Double.parseDouble(datos[1]);
                Vertice nuevoVertice = new Vertice(datos[0], peso);
                vertices.add(nuevoVertice);
            }

            for (int i = 0; i < nAristas; i++) {
                line = br.readLine();
                if (line == null)
                    break;
                String[] datos = line.split(" ");

                if (datos.length != 4)
                    System.err.println("Formato de archivo inválido");

                Vertice extremo1 = obtenerVertice(datos[1]);
                Vertice extremo2 = obtenerVertice(datos[2]);
                double peso = Double.parseDouble(datos[3]);
                Arista nuevaArista = new Arista(datos[0], peso, extremo1, extremo2);
                aristas.add(nuevaArista);
            }

        } catch (IOException e) {
            System.err.println("Error abriendo el archivo: " + e.getMessage());
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error cerrando el archivo: " + e.getMessage());
                }
        }
        return true;
    }

    // Retorna el numero de vertices del grafo.

    public int numeroDeVertices() {
        return nVertices;
    }

    // Retorna el numero de lados (Aristas) del grafo.
    public int numeroDeLados() {
        return nAristas;
    }

    // Agrega el vertice "v" al grafo.
    public boolean agregarVertice(Vertice v) {
        nVertices++;
        return vertices.add(v);
    }

    /***
     * Agrega el un vertice al grafo.
     * 
     * @param id   Identificador del vertice a agregar.
     * @param peso Peso asignado al vertice a agregar.
     */
    public boolean agregarVertice(String id, double peso) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id)) {
                return false;
            }
        }
        Vertice nuevoVertice = new Vertice(id, peso);
        return agregarVertice(nuevoVertice);
    }

    /***
     * Busca un vertice requerido en el grafo ***
     * 
     * @param id Identificador del vertice a consultar
     * @return el vertice solicitado, en caso de que no se encuentre retorna Error.
     */
    public Vertice obtenerVertice(String id) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id)) {
                return vertice;
            }
        }
        throw new NoSuchElementException(id);
    }

    /***
     * Verifica si un vertice esta en el grafo ***
     * 
     * @return true si esta el vertice, false si no esta.
     */
    public boolean estaVertice(String id) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /***
     * Verifica si un lado esta en el grafo ***
     * 
     * @return true si esta el lado, false si no esta.
     */
    public boolean estaLado(String u, String v) {
        for (Arista arista : aristas) {
            String Extremo1 = arista.getExtremo1().getId();
            String Extremo2 = arista.getExtremo2().getId();
            if ((Extremo1.equals(u) && Extremo2.equals(v)) || (Extremo1.equals(v) && Extremo2.equals(u))) {
                return true;
            }
        }
        return false;
    }

    /***
     * Elimina un vertice requerido ***
     * 
     * @return Error si no hay ningun vertice con ese id.
     */

    public boolean eliminarVertice(String id) {
        if (!estaVertice(id)) {
            throw new NoSuchElementException();
        }
        Vertice verticeAEliminar = null;
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id)) {
                nVertices--;
                verticeAEliminar = vertice;
            }
        }
        return vertices.remove(verticeAEliminar);
    }

    // Retorna la lista de los vertices en el grafo.
    public List<Vertice> vertices() {
        return vertices;
    }

    public List<Arista> aristas() {
        return aristas;
    }

    // Crea una lista de lados del grafo.
    public List<Lado> lados() {
        List<Lado> lados = new ArrayList<>();
        lados.addAll(aristas);
        return lados;
    }

    // Retorna el grado de un vertice requerido.
    public int grado(String id) {
        if (!estaVertice(id)) {
            throw new NoSuchElementException(id);
        }
        int grado = 0;
        for (Arista arista : aristas) {
            String Extremo1 = arista.getExtremo1().getId();
            String Extremo2 = arista.getExtremo2().getId();
            if (Extremo1.equals(id) || Extremo2.equals(id)) {
                grado++;
            }
        }
        return grado;
    }

    // Busca y retorna la lista de los vertices adyacentes a un vertice dado.
    public List<Vertice> adyacentes(String id) {
        if (!estaVertice(id)) {
            throw new NoSuchElementException(id);
        }
        List<Vertice> adyacentes = new ArrayList<>();
        for (Arista arista : aristas) {
            String Extremo1 = arista.getExtremo1().getId();
            String Extremo2 = arista.getExtremo2().getId();
            if (Extremo1.equals(id)) {
                adyacentes.add(arista.getExtremo2());
            } else if (Extremo2.equals(id)) {
                adyacentes.add(arista.getExtremo1());
            }
        }
        return adyacentes;
    }

    // Busca y retorna una lista de los lados incidentes a un vertice dado.
    public List<Lado> incidentes(String id) {
        if (!estaVertice(id)) {
            throw new NoSuchElementException(id);
        }
        List<Lado> incidentes = new ArrayList<>();
        for (Arista arista : aristas) {
            String Extremo1 = arista.getExtremo1().getId();
            String Extremo2 = arista.getExtremo2().getId();
            if (Extremo1.equals(id) || Extremo2.equals(id)) {
                incidentes.add(arista);
            }
        }
        return incidentes;
    }

    // Crea y retorna una copia de un grafo.
    public Object clone() {
        List<Vertice> verticesClone = new ArrayList<>();
        List<Arista> aristasClone = new ArrayList<>();

        for (Vertice vertice : vertices) {
            Vertice verticeClone = new Vertice(vertice.getId(), vertice.getPeso());
            verticesClone.add(verticeClone);
        }

        for (Arista arista : aristas) {
            Arista aristaClone = new Arista(arista.getId(), arista.getPeso(), arista.getExtremo1(),
                    arista.getExtremo2());
            aristasClone.add(aristaClone);
        }

        return new GrafoNoDirigido(nVertices, nAristas, verticesClone, aristasClone);
    }

    // Retorna el grafo representado mediante un String.
    public String toString() {
        String texto = new String();

        texto += String.format("%d\n%d\n", nVertices, nAristas);

        for (Vertice vertice : vertices) {
            texto += String.format("%s %f\n", vertice.getId(), vertice.getPeso());
        }

        for (Arista arista : aristas) {
            texto += String.format("%s %s %s %f\n", arista.getId(), arista.getExtremo1().getId(),
                    arista.getExtremo2().getId(), arista.getPeso());
        }

        return texto;
    }

    // Agrega una arista al grafo
    public boolean agregarArista(Arista a) {
        nAristas++;
        return aristas.add(a);
    }

    /***
     * Agrga una arista al grafo
     * 
     * @param u,v  Extremos a conectar.
     * @param id   identificador de la arista a insertar.
     * @param peso Peso de la arista a insertar.
     * @return false en caso de que ya se encuentre la arista o no se encuentre
     *         alguno de los
     *         vertices a conectar.
     */
    public boolean agregarArista(String id, double peso, String u, String v) {
        if (estaLado(u, v) || (!estaVertice(u)) || (!estaVertice(v))) {
            return false;
        }
        Vertice j = obtenerVertice(u);
        Vertice k = obtenerVertice(v);

        Arista nuevaArista = new Arista(id, peso, j, k);
        return agregarArista(nuevaArista);
    }

    /***
     * Elimina una Arista requerida ***
     * 
     * @return la lista de aristas, sin la arista requerida o Error en caso de que
     *         no se
     *         encuentre dicha arista.
     */
    public boolean eliminarArista(String id) {
        for (Arista arista : aristas) {
            if (arista.getId().equals(id)) {
                nAristas--;
                return aristas.remove(arista);
            }
        }
        throw new NoSuchElementException();
    }

    /***
     * Busca y retorna una Arista ***
     * 
     * @return la arista requerida o Error en caso de que no se encuentre una arista
     *         con el id requerido.
     */
    public Arista obtenerArista(String id) {
        for (Arista arista : aristas) {
            if (arista.getId().equals(id)) {
                return arista;
            }
        }
        throw new NoSuchElementException();
    }

    public Arista obtenerArista(String extremo1, String extremo2) {
        for (Arista arista : aristas) {
            String Extremo1 = arista.getExtremo1().getId();
            String Extremo2 = arista.getExtremo2().getId();
            if ((Extremo1.equals(extremo1) && Extremo2.equals(extremo2))
                    || (Extremo1.equals(extremo2) && Extremo2.equals(extremo1))) {
                return arista;
            }
        }

        throw new NoSuchElementException();
    }
}