package tad_grafo.general;

/**
 * Clase que representa un grafo dirigido.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Digrafo implements Grafo {

    /**
     * Número de vértices del grafo.
     */
    private int nrVertices;

    /**
     * Número de arcos del grafo.
     */
    private int nrArcos;

    /**
     * Lista de vértices del grafo.
     */
    private List<Vertice> vertices;

    /**
     * Lista de arcos del grafo.
     */
    private List<Arco> arcos;

    public Digrafo() {
        this.vertices = new ArrayList<>();
        this.arcos = new ArrayList<>();
        this.nrVertices = 0;
        this.nrArcos = 0;
    }

    /**
     * Constructor de la clase Digrafo.
     * 
     * @param nrVertices Número de vértices del grafo.
     * @param nrArcos    Número de arcos del grafo.
     * @param vertices   Lista de vértices del grafo.
     * @param arcos      Lista de arcos del grafo.
     */
    public Digrafo(int nrVertices, int nrArcos, List<Vertice> vertices, List<Arco> arcos) {
        this.nrVertices = nrVertices;
        this.nrArcos = nrArcos;
        this.vertices = vertices;
        this.arcos = arcos;
    }

    /**
     * Crea un grafo no dirigido.
     * 
     * @return Grafo no dirigido.
     */
    public static Digrafo crearDigrafo() {
        List<Vertice> nuevosVertices = new ArrayList<>();
        List<Arco> nuevosArcos = new ArrayList<>();
        Digrafo nuevoDigrafo = new Digrafo(0, 0, nuevosVertices, nuevosArcos);
        return nuevoDigrafo;
    }

    /**
     * Carga un grafo desde un archivo.
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
            nrVertices = Integer.parseInt(line);
            line = br.readLine();
            nrArcos = Integer.parseInt(line);

            for (int i = 0; i < nrVertices; i++) {
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

            for (int i = 0; i < nrArcos; i++) {
                line = br.readLine();
                if (line == null)
                    break;
                String[] datos = line.split(" ");

                if (datos.length != 4)
                    System.err.println("Formato de archivo inválido");

                Vertice extremoInicial = obtenerVertice(datos[1]);
                Vertice extremoFinal = obtenerVertice(datos[2]);
                double peso = Double.parseDouble(datos[3]);
                Arco nuevoArco = new Arco(datos[0], peso, extremoInicial, extremoFinal);
                arcos.add(nuevoArco);
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

    /**
     * Número de vertices del digrafo
     * 
     * @return número de vertices
     */
    public int numeroDeVertices() {
        return nrVertices;
    }

    /**
     * Número de lados del digrafo
     * 
     * @return número de lados
     */
    public int numeroDeLados() {
        return nrArcos;
    }

    /**
     * Añade un vértice al digrafo
     * 
     * @param v Vertice añadido
     * @return true si se ha añadido correctamente, false en caso contrario
     */
    public boolean agregarVertice(Vertice v) {
        return vertices.add(v);
    }

    /**
     * Añade un vértice al digrafo
     * 
     * @param id   El Identificador del vertice a añadir
     * @param peso Peso del vertice a añadir
     * @return true si se ha añadido correctamente, false en caso contrario
     */
    public boolean agregarVertice(String id, double peso) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id))
                return false;
        }

        Vertice vertice = new Vertice(id, peso);
        return vertices.add(vertice);
    }

    /**
     * Busca un vértice dentro del digrafo dado un id
     * 
     * @param id El identificador del vertice a buscar
     * @return Vertice encontrado si existe
     * @throws NoSuchElementException si no existe ningún vértice con el ID
     *                                especificado
     */
    public Vertice obtenerVertice(String id) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id))
                return vertice;
        }

        throw new NoSuchElementException(id);
    }

    /**
     * Determina si un vertice está dentro del digrafo
     * 
     * @param id El identificador del vertice a buscar
     * @return true si se encuentra, false en caso contrario
     */
    public boolean estaVertice(String id) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id))
                return true;
        }

        return false;
    }

    /**
     * Determina si un lado está dentro del digrafo
     * 
     * @param u El identificador del extremo inicial del lado
     * @param v El identificador del extremo final del lado
     * @return true si se encuentra, false en caso contrario
     */
    public boolean estaLado(String u, String v) {
        for (Arco arco : arcos) {
            String extremoInical = arco.getExtremoInicial().getId();
            String extremoFinal = arco.getExtremoFinal().getId();
            if (extremoInical.equals(u) && extremoFinal.equals(v))
                return true;
        }

        return false;
    }

    /**
     * Elimina el vértice con el ID especificado.
     *
     * @param id El identificador del vértice que se eliminará
     * @return true si se eliminó el vértice; false en caso contrario
     */
    public boolean eliminarVertice(String id) {
        Vertice verticeAEliminar = null;
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id))
                verticeAEliminar = vertice;
        }

        return vertices.remove(verticeAEliminar);
    }

    /**
     * Devuelve una lista de todos los vértices en este grafo.
     *
     * @return Una lista de todos los vértices en este grafo
     */
    public List<Vertice> vertices() {
        return vertices;
    }

    /**
     * Devuelve una lista de todos los lados en este grafo.
     *
     * @return Una lista de todos los lados en este grafo
     */
    public List<Lado> lados() {
        List<Lado> lados = new ArrayList<>();
        lados.addAll(arcos);
        return lados;
    }

    /**
     * Devuelve el grado del vértice con el ID especificado.
     *
     * @param id El identificador del vértice cuyo grado se calculará
     * @return El grado del vértice con el ID especificado
     * @throws NoSuchElementException si no existe ningún vértice con el ID
     *                                especificado
     */
    public int grado(String id) {
        if (!estaVertice(id))
            throw new NoSuchElementException(id);
        int count = 0;
        for (Arco arco : arcos) {
            String extremoInical = arco.getExtremoInicial().getId();
            String extremoFinal = arco.getExtremoFinal().getId();
            if (extremoInical.equals(id) || extremoFinal.equals(id))
                count++;
        }

        return count;
    }

    /**
     * Devuelve una lista de todos los vértices adyacentes al vértice con el ID
     * especificado.
     *
     * @param id El identificador del vértice cuyos vértices adyacentes se
     *           devolverán
     * @return Una lista de todos los vértices adyacentes al vértice con el ID
     *         especificado
     * @throws NoSuchElementException si no existe ningún vértice con el ID
     *                                especificado
     */
    public List<Vertice> adyacentes(String id) {
        if (!estaVertice(id))
            throw new NoSuchElementException(id);
        List<Vertice> adyacentes = new ArrayList<>();
        for (Arco arco : arcos) {
            String extremoInical = arco.getExtremoInicial().getId();
            if (extremoInical.equals(id))
                adyacentes.add(arco.getExtremoFinal());
        }

        return adyacentes;
    }

    /**
     * Devuelve una lista de todos los lados incidentes en el vértice con el ID
     * especificado.
     *
     * @param id El identificador del vértice cuyos lados incidentes se devolverán
     * @return Una lista de todos los lados incidentes en el vértice con el ID
     *         especificado
     * @throws NoSuchElementException si no existe ningún vértice con el ID
     *                                especificado
     */
    public List<Lado> incidentes(String id) {
        if (!estaVertice(id))
            throw new NoSuchElementException(id);
        List<Lado> incidentes = new ArrayList<>();
        for (Arco arco : arcos) {
            String extremoFinal = arco.getExtremoFinal().getId();
            if (extremoFinal.equals(id))
                incidentes.add(arco);
        }

        return incidentes;
    }

    /**
     * Crea y devuelve una copia exacta de este grafo.
     *
     * @return una copia exacta de este grafo
     */
    public Object clone() {
        List<Vertice> verticesCopia = new ArrayList<>();
        List<Arco> arcosCopia = new ArrayList<>();

        for (Vertice vertice : vertices) {
            Vertice verticeCopia = new Vertice(vertice.getId(), vertice.getPeso());
            verticesCopia.add(verticeCopia);
        }

        for (Arco arco : arcos) {
            Arco arcoCopia = new Arco(arco.getId(), arco.getPeso(), arco.getExtremoInicial(), arco.getExtremoFinal());
            arcosCopia.add(arcoCopia);
        }

        return new Digrafo(nrVertices, nrArcos, verticesCopia, arcosCopia);

    }

    /**
     * Devuelve un string que representa el grafo.
     * 
     * @return Un string que representa el grafo.
     */
    public String toString() {
        String resultado = new String();

        resultado += String.format("%d\n%d\n", nrVertices, nrArcos);

        for (Vertice vertice : vertices) {
            resultado += String.format("%s %f\n", vertice.getId(), vertice.getPeso());
        }

        for (Arco arco : arcos) {
            resultado += String.format("%s %s %s %f\n", arco.getId(), arco.getExtremoInicial().getId(),
                    arco.getExtremoFinal().getId(), arco.getPeso());
        }

        return resultado;
    }

    /**
     * Agrega un arco al grafo.
     * 
     * @param a El arco a agregar.
     * @return true si el arco se agregó correctamente, false en caso contrario.
     */
    public boolean agregarArco(Arco a) {
        return arcos.add(a);
    }

    /**
     * Agrega un nuevo arco al grafo.
     * 
     * @param id       El identificador del nuevo arco.
     * @param peso     El peso del nuevo arco.
     * @param eInicial Extremos inicial a conectar.
     * @param eFinal   Extremos final a conectar.
     * @return true si el arco se agregó correctamente, false en caso contrario.
     */
    public boolean agregarArco(String id, double peso, String eInicial, String eFinal) {
        if (estaLado(eInicial, eFinal) || (!estaVertice(eInicial)) || (!estaVertice(eFinal))) {
            return false;
        }
        Vertice j = obtenerVertice(eInicial);
        Vertice k = obtenerVertice(eFinal);
        Arco nuevoArco = new Arco(id, peso, j, k);
        return agregarArco(nuevoArco);
    }

    /**
     * Devuelve el grado interior de un vértice.
     * 
     * @param id El identificador del vértice.
     * @return El grado interior del vértice.
     */

    public int gradoInterior(String id) {
        int cuenta = 0;
        for (Arco arco : arcos) {
            if (arco.getExtremoFinal().getId().equals(id))
                cuenta++;
        }

        return cuenta;
    }

    /**
     * Devuelve el grado exterior de un vértice.
     * 
     * @param id El identificador del vértice.
     * @return El grado exterior del vértice.
     */
    public int gradoExterior(String id) {
        int cuenta = 0;
        for (Arco arco : arcos) {
            if (arco.getExtremoInicial().getId().equals(id))
                cuenta++;
        }

        return cuenta;
    }

    /**
     * Devuelve una lista de vértices sucesores de un vértice.
     * 
     * @param id El identificador del vértice.
     * @return Una lista de vértices sucesores del vértice.
     * @throws NoSuchElementException Si el vértice no existe en el grafo.
     */
    public List<Vertice> sucesores(String id) {
        if (!estaVertice(id))
            throw new NoSuchElementException(id);
        List<Vertice> sucesores = new ArrayList<>();
        for (Arco arco : arcos) {
            if (arco.getExtremoInicial().getId().equals(id)) {
                sucesores.add(arco.getExtremoFinal());
            }
        }

        return sucesores;
    }

    /**
     * Devuelve una lista de vértices predecesores de un vértice.
     * 
     * @param id El identificador del vértice.
     * @return Una lista de vértices predecesores del vértice.
     * @throws NoSuchElementException Si el vértice no existe en el grafo.
     */
    public List<Vertice> predecesores(String id) {
        if (!estaVertice(id))
            throw new NoSuchElementException(id);
        List<Vertice> sucesores = new ArrayList<>();
        for (Arco arco : arcos) {
            if (arco.getExtremoFinal().getId().equals(id)) {
                sucesores.add(arco.getExtremoInicial());
            }
        }

        return sucesores;
    }

    /**
     * Elimina un arco del grafo.
     * 
     * @param id El identificador del arco a eliminar.
     * @return true si el arco se eliminó correctamente, false en caso contrario.
     * @throws NoSuchElementException Si el arco no existe en el grafo.
     */
    public boolean eliminarArco(String id) {
        for (Arco arco : arcos) {
            if (arco.getId().equals(id)) {
                return arcos.remove(arco);
            }
        }

        throw new NoSuchElementException(id);
    }

    /**
     * Obtiene un arco del grafo.
     * 
     * @param id El identificador del arco a obtener.
     * @return El arco obtenido.
     * @throws NoSuchElementException Si el arco no existe en el grafo.
     */
    public Arco obtenerArco(String id) {
        for (Arco arco : arcos) {
            if (arco.getId().equals(id)) {
                return arco;
            }
        }

        throw new NoSuchElementException(id);
    }
}