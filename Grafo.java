import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Representa un grafo no dirigido mediante una matriz de adyacencia.
 */
public class Grafo {
    private final boolean[][] matrizAdyacencia;

    /**
     * Crea un grafo sin aristas con la cantidad indicada de vertices.
     *
     * @param cantidadVertices numero de vertices del grafo
     */
    public Grafo(int cantidadVertices) {
        if (cantidadVertices < 0) {
            throw new IllegalArgumentException("La cantidad de vertices no puede ser negativa.");
        }

        matrizAdyacencia = new boolean[cantidadVertices][cantidadVertices];
    }

    /**
     * Agrega una arista entre dos vertices. Al ser no dirigido, registra la
     * conexion en ambas direcciones.
     *
     * @param origen indice del primer vertice
     * @param destino indice del segundo vertice
     */
    public void agregarArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        matrizAdyacencia[origen][destino] = true;
        matrizAdyacencia[destino][origen] = true;
    }

    /**
     * Elimina la arista entre dos vertices, si existe.
     *
     * @param origen indice del primer vertice
     * @param destino indice del segundo vertice
     */
    public void eliminarArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        matrizAdyacencia[origen][destino] = false;
        matrizAdyacencia[destino][origen] = false;
    }

    /**
     * Indica si existe una arista entre dos vertices.
     *
     * @param origen indice del primer vertice
     * @param destino indice del segundo vertice
     * @return true si los vertices estan conectados
     */
    public boolean existeArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        return matrizAdyacencia[origen][destino];
    }

    /**
     * Devuelve una copia de la matriz para evitar que se modifique desde fuera
     * del grafo.
     *
     * @return copia de la matriz de adyacencia
     */
    public boolean[][] obtenerMatrizAdyacencia() {
        boolean[][] copia = new boolean[matrizAdyacencia.length][matrizAdyacencia.length];

        for (int i = 0; i < matrizAdyacencia.length; i++) {
            copia[i] = matrizAdyacencia[i].clone();
        }

        return copia;
    }

    /**
     * Recorre en profundidad los vertices alcanzables desde el vertice inicial
     * e imprime el orden en que se visitan.
     *
     * @param inicio indice del vertice inicial
     */
    public void DFS(int inicio) {
        validarVertice(inicio);

        boolean[] visitados = new boolean[matrizAdyacencia.length];
        System.out.print("DFS: ");
        recorrerEnProfundidad(inicio, visitados);
        System.out.println();
    }

    /**
     * Recorre en anchura los vertices alcanzables desde el vertice inicial e
     * imprime el orden en que se visitan.
     *
     * @param inicio indice del vertice inicial
     */
    public void BFS(int inicio) {
        validarVertice(inicio);

        boolean[] visitados = new boolean[matrizAdyacencia.length];
        Queue<Integer> cola = new ArrayDeque<>();

        visitados[inicio] = true;
        cola.add(inicio);
        System.out.print("BFS: ");

        // La cola visita primero todos los vecinos del nivel actual.
        while (!cola.isEmpty()) {
            int vertice = cola.remove();
            System.out.print(vertice + " ");

            for (int vecino = 0; vecino < matrizAdyacencia.length; vecino++) {
                if (matrizAdyacencia[vertice][vecino] && !visitados[vecino]) {
                    visitados[vecino] = true;
                    cola.add(vecino);
                }
            }
        }

        System.out.println();
    }

    /**
     * Visita un vertice y explora recursivamente cada vecino no visitado.
     */
    private void recorrerEnProfundidad(int vertice, boolean[] visitados) {
        visitados[vertice] = true;
        System.out.print(vertice + " ");

        for (int vecino = 0; vecino < matrizAdyacencia.length; vecino++) {
            if (matrizAdyacencia[vertice][vecino] && !visitados[vecino]) {
                recorrerEnProfundidad(vecino, visitados);
            }
        }
    }

    /**
     * Comprueba que el indice corresponda a un vertice del grafo.
     */
    private void validarVertice(int vertice) {
        if (vertice < 0 || vertice >= matrizAdyacencia.length) {
            throw new IndexOutOfBoundsException(
                    "El vertice debe estar entre 0 y " + (matrizAdyacencia.length - 1) + ".");
        }
    }
}
