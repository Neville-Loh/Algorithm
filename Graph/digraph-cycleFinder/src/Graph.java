package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Graph data structure in adjacency list representation. The structure is a ArrayList
 * of {@code Vertex}
 * 
 * @author Neville
 *
 */
public class Graph {
	
	private ArrayList<Vertex> vertexList;
	
	
	
	public Graph(int vertexNum) {
		vertexList = new ArrayList<Vertex>();
		// populate the graph with given number of vertex
    	for (int i = 0; i < vertexNum ; i++) {	
    		vertexList.add(new Vertex());
    	}
	}
	
	/**
	 *  Add edge from node start going toward node end to the graph
	 * @param start
	 * @param end
	 */
	public void addEdge(int start, int end) {
		vertexList.get(start).addOutdegree(end);
		vertexList.get(end).addIndegree(start);
	}
	
	/**
	 * Get the number of vertex of the graph
	 * @return size
	 */
	public int size() {
		return vertexList.size();
	}
	/**
	 * Get the i th vertex of the arrayList in the graph
	 * @param i the index of the vertex
	 * @return vertex 
	 */
	public Vertex get(int i) {
		return vertexList.get(i);
	}

	
	/**
	 * Represent vertices in digraph, each vertex contain two list; one for 
	 * in-degree and one for out-degree. The specify list is a linked list.
	 * @author Neville
	 *
	 */
	public class Vertex{
		private LinkedList<Integer> _inDegree;
		private LinkedList<Integer> _outDegree;
		
		public Vertex() {
			_inDegree = new LinkedList<Integer>();
			_outDegree = new LinkedList<Integer>();
		}
		
		/**
		 * Add a vertex to the its in-degree list
		 * @param vertex
		 */
		public void addIndegree(int vertex) {
			_inDegree.addFirst(vertex);
		}
		/**
		 * Add a vertex to its out-degree list
		 * @param vertex
		 */
		public void addOutdegree(int vertex) {
			_outDegree.addFirst(vertex);
		}
		
		/**
		 * @return The linked list of its in degree
		 */
		public LinkedList<Integer> getInDegree(){
			return _inDegree;
		}
		/**
		 * @return The linked list of its out degree
		 */
		public LinkedList<Integer> getOutDegree(){
			return _outDegree;
		}
	}
	
	
	/**
	 * utility method, print the graph as adjacency list representation on console.
	 */
	public void print() {
		System.out.print("Adjacency List: \n");
		int i = 0;
		for (Vertex v : vertexList) {
			System.out.printf("Node " + i +":   Out: %-16s In : %s%n", v.getOutDegree(), v.getInDegree());
        	i++;
		}
		System.out.println();
	}

}
