package assignment4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import assignment4.Graph.Vertex;



/**
 * This class count the average degree and same degree node 
 *  of the graph given in adjacency List representation.
 * @author Neville
 *
 */
public class DegreeCounter {
	private Graph _digraph;
	private int totalInDegree = 0;
	private int totalOutDegree = 0;
	public ArrayList<Integer> sameDegreeNode = new ArrayList<Integer>();
	
	/**
	 * Class Constructor, count the degree of existing graph 
	 * Call {@code DegreeCounter.countDegree()} on construction.
	 * @param graph 
	 */
	public DegreeCounter(Graph g) {
		_digraph = g;
		countDegree();
	}
	
	
	/**
	 * Count the total in degree and out degree of the graph, and append all node
	 * with the same number of in and out degree to same degree node list with in the
	 * object.
	 * 
	 * <p> The algorithm loop though all the vertex in the graph given in adjacent list 
	 * representation. Time complexity = O(V)
	 * @return list contain node with same number of in and out degree
	 */
	public ArrayList<Integer> countDegree(){
		for (int i = 0; i < _digraph.size(); i++) {
			
			Vertex v = _digraph.get(i);
			totalOutDegree += v.getOutDegree().size();
			totalInDegree += v.getInDegree().size();
			
			// append node with same number of in and out degree
			if (v.getOutDegree().size() ==  v.getInDegree().size()) {
				sameDegreeNode.add(i);
			}
		}
		return sameDegreeNode;
	}
	
	/**
	 * compute and return the average out degree of the graph in a given format
	 * @return average out degree
	 */
	public String getAvgOutDegree() {
		DecimalFormat df = new DecimalFormat("#.#####");
		double avg = (double) totalOutDegree / _digraph.size();
		return df.format(avg);
	}
	
	/**
	 * compute and return the average in degree of the graph in a given format
	 * @return average in degree
	 */
	public String getAvgInDegree() {
		DecimalFormat df = new DecimalFormat("#.#####");
		double avg = (double) totalInDegree / _digraph.size();
		return df.format(avg);
	}
	
	/**
	 * Utility method, print result of the degree counter.
	 */
	public void printReport() {
		System.out.println("Degree counter report:");
		System.out.printf("Average In-Degree: %s", this.getAvgInDegree());
		System.out.printf("   Average Out-Degree: %s%n", this.getAvgOutDegree());
		System.out.println("Node with same degree: " + sameDegreeNode + "\n");

	}
	
	
}
