package modelo;
import interfaces.IGrafo;
import interfaces.INodo;
import interfaces.IObtenerId;

import java.util.*;

public class Grafo<T extends IObtenerId> implements IGrafo<T>{

	private Map<Integer, Nodo> nodos = new HashMap<>();

	@Override
	public void agregarNodo(INodo<T> valor) {
		if (!nodos.containsKey(valor)) {
			nodos.put(valor.getValor().getId(), new Nodo(valor));
		}
	}

	@Override
	public void agregarArista(INodo<T> origen, INodo<T> destino) {
		if (nodos.containsKey(origen) && nodos.containsKey(destino)) {
			INodo<T> nodoOrigen =  nodos.get(origen);
			INodo<T> nodoDestino = nodos.get(destino);
			
			nodoOrigen.agregarVecino(nodoDestino);
			nodoDestino.agregarVecino(nodoOrigen);
		}
	}

	@Override
	public void mostrarMatrizAdyacencia() {
		System.out.println("Matriz de Adyacencia");
		List<Integer> claves = new ArrayList<>(nodos.keySet());
		
		Collections.sort(claves);
		
		System.out.println("  ");
		for(int i: claves) System.out.println(" ");
		System.out.println();
		
		for(int i: claves) {
			System.out.println(i+": ");
			for(int j: claves) {
				INodo<T> nodoI = nodos.get(i);
				INodo<T> nodoj = nodos.get(j);
				System.out.println(nodoI.getVecinos().contains(nodoj) ? "1":"0");
			}
			System.out.println();
		}
		
	}

	@Override
	public void bfs(INodo<T> inicio) {
		if(!nodos.containsKey(inicio)) return;
		Set<Integer> visitados = new HashSet<>();
		Queue<INodo<T>> cola = new LinkedList<>();
		
		INodo<T> nodoInicio = nodos.get(inicio);
		cola.add(nodoInicio);
		visitados.add(inicio.getValor().getId());
		
		System.out.println("Recorrido BFS: ");
		
		while (!cola.isEmpty()){
			INodo<T> actual = cola.poll();
			System.out.print(actual.getValor() + " ");
			
			for(INodo<T> vecino : actual.getVecinos()) {
				if (!visitados.contains(vecino.getValor())) {
					visitados.add(vecino.getValor().getId());
					cola.add((INodo<T>) vecino);
				}
			}
		}
	     System.out.println();

		
	}

	@Override
	public void dfs(INodo<T> inicio) {
		
	}

	
	
}
