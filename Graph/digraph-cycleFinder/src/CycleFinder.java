package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * This class find all simple cycle in a given {@code Graph} using DFS traversal at each node.
 * Time complexity = O( (E+V) * C) where, E number of edges , V is the number of vertices
 * C is the number of cycles.
 * @author Neville
 *
 */
public class CycleFinder {
	private Graph digraph;
	private Set<Integer> explored;
	private Map<Integer, Set<Integer>> potentialRemoval;
	private Stack<Integer> stack;
	public ArrayList<ArrayList<Integer>> cycleList;
	private String reportString = "";
	
	
	/**
	 * Class constructor
	 * @param digraph
	 */
	public CycleFinder(Graph digraph) {
		this.digraph = digraph;
		explored = new HashSet<>();
		potentialRemoval = new HashMap<>();
		stack = new Stack<Integer>();
		cycleList = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * This function find all the cycles in the graph by running {@code findCycleDFS()}
	 * on every vertex of the graph, then the vertex is removed from the graph when all
	 * cycle contain the vertex is found by making a subgraph without the vertex.
	 * 
	 */
	public void findCycle() {

		for (int i = 0; i < digraph.size(); i++) {			
			reportString += ("\nFinding cycle at node: " + i );
			explored.clear();
			potentialRemoval.clear();
            findCycleDFS(i, i, getSubgraph(i, digraph));
			
		}
	}
	/**
	 * Find all cycle of the graph that go lead to parameter startVertex, return 
	 * a boolean value indicating if current node is a component of the cycle.
	 * 
	 * <p>This function works by recursively navigating graph using DFS algorithm. 
	 * Whenever a neighbor of the current vertex is the start vertex, a cycle is
	 * found. The cycle order is retrieved from the stack and added to the the cycle list
	 * with in the CycleFinder object. When a cycle is found on the current vertex, 
	 * 
	 * <p> The returned boolean is designed to 
	 * 
	 * @param startVertex 
	 * @param currentVertex
	 * @param myDigraph
	 * @return if current node is in a cycle
	 */
	private boolean findCycleDFS(int startVertex, int currentVertex, Graph subgraph) {
		// Add current vertex to stack and explored
		stack.push(currentVertex);
		explored.add(currentVertex);
		boolean vertexInCycle = false;
		
		reportString += String.format("%nNode %d:   Stack: %-24s Explored : %s"
					,currentVertex, stack.toString(), explored.toString());
		
		
		/* 
		 * DFS portion of the algorithm
		 * Loop though each neighbor of current vertex, which from an outgoing edge from current 
		 * vertex to neighbor, if neighbor is start vertex, add to cycle list
		 * Any other explored vertex is ignored to avoid double counting. Recursively call
		 * the algorithm if it's not yet explored.
		 */
		Iterator<Integer> iter_out_degree =  subgraph.get(currentVertex).getOutDegree().iterator();
		while(iter_out_degree.hasNext()){
			int neighbor = iter_out_degree.next();
	        	
			
        	// neighbor is start, a cycle found from start to current vertex, add it cycle list
	        if (neighbor == startVertex) {        		
	        	ArrayList<Integer> cycle = new ArrayList<Integer>();
	        	cycle.addAll(stack);
	        	cycleList.add(cycle);
	        	vertexInCycle = true;	
	        	reportString += "         ------Cycle found - " + cycle.toString();
	        }
	        // recursively call DFS on the neighbor vertex if not explored yet.
	        else if (!explored.contains(neighbor)) {
	        	boolean neighbourInCycle = findCycleDFS(startVertex, neighbor, subgraph);
	        	if (neighbourInCycle) {
	        		vertexInCycle = true;
	        	}
	        }
	        	
		}
	    
		/*
		 *  When all neighbor are explored and Node is in cycle, removed the current vertex
		 *  in cycle, free up the vertex and its neighbor since there maybe a path contains
		 *  them leading to the start vertex.
		 */
        if (vertexInCycle) {
        	undoExplored(currentVertex);
        	
        /*
         *  All neighbor are explored and the current node is not a part of cycle currently
         *  at the iteration. All current vertex is added to potential removal map when its neighbor 
         *  node is removed. This happens when neighbor vertex is deemed component of a cycle 
         *  in future iteration, implies a potential cycle though neighbor
         */
        } else {
        	for (int edge : subgraph.get(currentVertex).getOutDegree()) {
        		//see if neighbor vertex already exist in the map, if yes, get the set
        		// if no make a new hash set.
        		Set<Integer> vertexSet = potentialRemoval.get(edge);
        		if (vertexSet == null) {
        			vertexSet = new HashSet<>();
        			vertexSet.add(currentVertex);
        			potentialRemoval.put(edge, vertexSet);
        		} else {
        			vertexSet.add(currentVertex);
        		}
        	}
        		
       	}
        
        /*
         *  Pop the stack and return a boolean indicating if current vertex is in a cycle
         *  This is for recursive purposes.
         */
        stack.pop();
        return vertexInCycle;
	    }
	    

	/**
	 * Removed all the linked vertex from Explored according to the hash map
	 * Recursively removed all vertex from explored that is hashed in potential removal
	 * @param vertex
	 */
	private void undoExplored(int vertex) {
		// remove current vertex from explored 
		explored.remove(vertex);
		
		// remove any vertex that have a path to the current vertex
		if(potentialRemoval.get(vertex) != null) {
			for (int v : potentialRemoval.get(vertex)) {
				if (explored.contains(v)) {
					undoExplored(v);
				}
			}
			potentialRemoval.remove(vertex);
			
		}
	}
	
    
    /**
     * Find subgraph of the current graph ignoring any vertex before 
     * start. All the in-going and out going edges of the vertex are removed 
     * from the subgraph.
     *  
     * @param start
     * @param inputGraph
     * @return subgraph 
     */
    public Graph getSubgraph(int start, Graph inputGraph){
    	Graph subgraph = new Graph(digraph.size());
    	
    	// For each node, populate subgraph with out going edges if 
    	// vertex number is greater than start.
    	for (int i = start; i < inputGraph.size(); i++) {
    		for (Integer vertex : inputGraph.get(i).getOutDegree()) {
    			if (vertex >= start) {
    				subgraph.addEdge(i,vertex);
    			}
    		}
    	}
    	return subgraph;
    }
    
    
    /**
     * Print the runtime report of the cycleFinder.
     */
    public void printReport() {
		System.out.println("\n---------------------------------------------");
		System.out.println("Cycle finder report:");
		System.out.println(reportString);
		System.out.println("---------------------------------------------");
		System.out.println("Result:  " + cycleList.size() +" cycle is Found");
		System.out.println(cycleList);
		System.out.println();
    }
    
    
}
