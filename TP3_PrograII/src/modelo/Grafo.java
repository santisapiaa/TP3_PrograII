package modelo;
import interfaces.IGrafo;
import interfaces.INodo;
import interfaces.IObtenerId;

import java.util.*;

public class Grafo<T extends IObtenerId> implements IGrafo<T> {

    private Map<Integer, INodo<T>> nodos = new HashMap<>();

    @Override
    public void agregarNodo(T valor) {
        if (!nodos.containsKey(valor.getId())) {
            nodos.put(valor.getId(), new Nodo<>(valor));
        }
    }

    @Override
    public void agregarArista(T origen, T destino) {
        if (nodos.containsKey(origen.getId()) && nodos.containsKey(destino.getId())) {
            INodo<T> nodoOrigen = nodos.get(origen.getId());
            INodo<T> nodoDestino = nodos.get(destino.getId());
            nodoOrigen.agregarVecino(nodoDestino);
            nodoDestino.agregarVecino(nodoOrigen);
        }
    }

    @Override
    public void mostrarMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia");
        List<Integer> claves = new ArrayList<>(nodos.keySet());
        Collections.sort(claves);

        // Imprimir encabezado
        System.out.print("  ");
        for (int i : claves) System.out.print(i + " ");
        System.out.println();

        // Imprimir matriz
        for (int i : claves) {
            System.out.print(i + ": ");
            for (int j : claves) {
                INodo<T> nodoI = nodos.get(i);
                INodo<T> nodoJ = nodos.get(j);
                System.out.print(nodoI.getVecinos().contains(nodoJ) ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    @Override
    public void bfs(T inicio) {
        if (!nodos.containsKey(inicio.getId())) return;
        Set<Integer> visitados = new HashSet<>();
        Queue<INodo<T>> cola = new LinkedList<>();

        INodo<T> nodoInicio = nodos.get(inicio.getId());
        cola.add(nodoInicio);
        visitados.add(inicio.getId());

        System.out.print("Recorrido BFS: ");

        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll();
            System.out.print(actual.getValor() + " ");

            for (INodo<T> vecino : actual.getVecinos()) {
                if (!visitados.contains(vecino.getValor().getId())) {
                    visitados.add(vecino.getValor().getId());
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }

    @Override
    public void dfs(T inicio) {
        if (!nodos.containsKey(inicio.getId())) return;
        Set<Integer> visitados = new HashSet<>();
        System.out.print("Recorrido DFS: ");
        dfsRec(nodos.get(inicio.getId()), visitados);
        System.out.println();
    }

    private void dfsRec(INodo<T> actual, Set<Integer> visitados) {
        visitados.add(actual.getValor().getId());
        System.out.print(actual.getValor() + " ");

        List<INodo<T>> vecinos = actual.getVecinos();
        for (int i = vecinos.size() - 1; i >= 0; i--) {
            INodo<T> vecino = vecinos.get(i);
            if (!visitados.contains(vecino.getValor().getId())) {
                dfsRec(vecino, visitados);
            }
        }
    }
}