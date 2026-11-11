public class Main {

    public static void main(String[] args) {

        // Crear un grafo con 5 vertices
        Grafo grafo = new Grafo(5);

        // Agregar conexiones entre los vertices
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);

        // Recorrido en profundidad
        System.out.println("Recorrido DFS:");
        grafo.DFS(0);

        // Recorrido en anchura
        System.out.println("Recorrido BFS:");
        grafo.BFS(0);
    }
}