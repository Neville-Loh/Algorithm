package assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	public static void main(String[] args) throws IOException{
	    
		String promt = "Please load the graph file: (type stop to terminate): ";
		String userInput = Helper.readFromKeyboard(promt);
		while(!userInput.equals("stop")){
			 if (Helper.isCommand(userInput)) {
				 Helper.processCommand(userInput);
				 
			 } else {
				 try {
					 	// load the file
					 	String file_name = userInput;
				 		File f = new File(file_name);
				 		BufferedReader br = new BufferedReader(new FileReader(f));
				 		
				 		
				 		// Generate the result
				 		computeResult(br, file_name);
			        
				 	} catch (FileNotFoundException e){
				 		System.out.printf("Cannot find file: %s%n%n",userInput);
				 	}
			 }
			 userInput = Helper.readFromKeyboard(promt);
			 
		}
	}

	
	/**
	 * Compute the require output for the input text file
	 * @param br bufferedReader of file
	 * @param file_name 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void computeResult(BufferedReader br, String file_name) throws NumberFormatException, IOException {
		

        // make new graph
        Graph digraph = inputToGraph(br);
        DegreeCounter degreeCounter = new DegreeCounter(digraph);
        CycleFinder cycleFinder = new CycleFinder(digraph);
        TopologicalOrder to = new TopologicalOrder(digraph);
        
        boolean OrderExist = to.computeOrder();
        
        String[] line = new String[6];
        
        line[1] = Helper.arrayToStringOutput(degreeCounter.sameDegreeNode);
        line[2] = degreeCounter.getAvgInDegree() + " " + degreeCounter.getAvgOutDegree();
        line[3] = "";
        line[4]= "";
        line[5] = "Yes";

        
        // Topological Order exist
	    if(OrderExist){
	    	line[3] = "Order:";
	        line[4] = Helper.arrayToStringOutput(to.m_topological_order);
	    }
	    
	    // Graph contain cycle
	    else {
	    	line[3] = "Cycle(s):";
	    	cycleFinder.findCycle();
	    	line[4] = Helper.arrayToStringOutput(cycleFinder.cycleList.get(0));
	    	if (cycleFinder.cycleList.size() > 3) {
	    		line[5] = "No";
	    	}
	    }
	    
	    
	    // Outputting text file
	    String outputFileName = "output"+file_name;
	    Helper.writeToFile(outputFileName, line);

	    	    
	    // Default set to print runtime Report to false, input toggleShowReport, toggleShowResult to change.
	    Helper.processReport(file_name, outputFileName, line, digraph, degreeCounter, to, cycleFinder);
	    
	}
	
	/**
	 * Generate a graph from text file input.
	 * 
	 * @param br BufferedReader that loaded the text file.
	 * @return Graph
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static Graph inputToGraph(BufferedReader br) throws NumberFormatException, IOException{
        int startVertex;
        int endVertex;
        int totalVertices = Integer.parseInt(br.readLine());
		String line; 
        
        // make new graph
        Graph graph = new Graph(totalVertices);
        
        // add each line as edge
        while ((line = br.readLine()) != null && !line.isEmpty()) {
        		startVertex = Character.getNumericValue(line.charAt(0));
        		endVertex = Character.getNumericValue(line.charAt(2));
        		graph.addEdge(startVertex, endVertex);
        }
        br.close();
        return graph;
	}
	
}

