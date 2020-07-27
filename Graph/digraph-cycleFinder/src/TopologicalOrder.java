package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class TopologicalOrder {

	private Graph m_digraph;
	private ArrayList<Integer> m_active;
	private PriorityQueue<Integer> m_candidate;
	public ArrayList<Integer> m_topological_order;
	private String runTimeString = "";
	public boolean orderExist = false;
	
	
	
	public TopologicalOrder(Graph digraph){
		m_digraph = digraph;
		m_active = new ArrayList<Integer>();
		m_candidate = new PriorityQueue<Integer>();
		m_topological_order = new ArrayList<Integer>();
		

	}
	
	/**
	 * Check if there exist a topological order in a {@code Graph} , if it exist 
	 * the replace current topological order in the class to the order. Return true.
	 * If not exist return false.
	 * 
	 * <p> This function first find all the {@code Vertex} with 0 in-degree. Since if 
	 * there exist a topological order implies no cycle is in the graph, there exist
	 *  a {@code Vertex} with 0 in-degree. Then added the vertex in priority queue. 
	 *  When any vertex is pop from the queue, the in-degree of its neighbor is deducted
	 *  by 1. All neighbor with 0 in-degree afterward is added to the queue. This 
	 *  terminate when there is no element in the priority queue.
	 *  
	 * @return if there exist a topological Order
	 */
	public boolean computeOrder()
	{	
		// Append number of in degree of each vertex
		for (int i = 0; i < m_digraph.size(); i++) {
			int numberOfInDegree = m_digraph.get(i).getInDegree().size();
			m_active.add(numberOfInDegree);
			
			// append to queue if no in degree
			if (numberOfInDegree == 0){
				m_candidate.add(i);
			}
		}
	    runTimeString += ("initialize queue with 0 indegree vertex: " + m_candidate + "\n");
	    
	    while (!m_candidate.isEmpty())
	    {
	    	// poll the smallest number in the priority queue and set it to vertex
	        int vertex = m_candidate.poll();
	        m_topological_order.add(vertex);
	        
	        // loop though out degree of vertex and deduct one in degree from all neighbors.
	        Iterator<Integer> iter_out_degree = m_digraph.get(vertex).getOutDegree().iterator();	        
	        while(iter_out_degree.hasNext()) 
	        {
	        	int tmp = iter_out_degree.next();
	        	m_active.set(tmp, m_active.get(tmp)-1);
	            if(m_active.get(tmp) == 0)
	                m_candidate.add(tmp);
	        }
	        
	        runTimeString += String.format("Exploring vertex %-5d candidate queue = %s%n",vertex, m_candidate);
	    }
	    
	    // check if all vertex is include, if not, there exist a cycle
	    if (m_topological_order.size() == m_digraph.size()) {
	    	orderExist = true;
	    	return true;
	    }
	    
	    return false;
	}
	
	
	
	/**
	 * Print runtime Report of topologicalOrder algorithm.
	 */
	public void printReport() {
		
		System.out.println("---------------------------------------------");
		System.out.println("Topological order report:");
		System.out.print(runTimeString);
		
		System.out.println("---------------------------------------------");
		System.out.println("Result Topological Order:");
		System.out.println(m_topological_order);
		System.out.println();
		
	}

}
