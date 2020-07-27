package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Helper class, contain all format function and reading and writing. 
 * Has nothing to do with the algorithm.
 * @author Neville
 *
 */
public class Helper {
	
	static boolean showReport = false;
	static boolean showResult = false;
	static boolean writeOutput = true;
	
	
	/**
	 * Write the text file. omit line 0;
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void writeToFile(String outputFileName, String[] line)
			throws FileNotFoundException, UnsupportedEncodingException {
		if (writeOutput) {
			PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
			if (line.length < 6) {
				System.out.println("Something went very wrong, cound not write to file");
			}
			writer.println(line[1]);
			writer.println(line[2]);
			writer.println(line[3]);
			writer.println(line[4]);
			writer.println(line[5]);
			writer.close();
	    
			System.out.printf("Result has been output to %s%n%n", outputFileName);
		}
	}
	
	/**
	 * Check if user input is a valid command;
	 * valid command includes toggleShowReport and toggleShowResult;
	 * @param input
	 * @return true or false
	 */
	public static boolean isCommand(String input) {
		 if (input.equalsIgnoreCase("toggleshowreport")||
				 input.equalsIgnoreCase("toggleshowresult") ||
				 input.equalsIgnoreCase("togglewriteoutput")){
			 return true;
		 }
		return false;
	}
	/**
	 * Helper method turn array list in to string with no bracket and comma
	 * @param array
	 * @return output string
	 */
	public static String arrayToStringOutput(ArrayList<Integer> array) {
		if (array.size() == 0) { return ""; }
		String result = "";
		result += array.get(0);
		for (int i = 1; i < array.size(); i++) {
			result += " " + array.get(i);;
		}
		return result;
	}
	
	/**
	 * Process the command of the user
	 * @param input the user input in string
	 */
	public static void processCommand(String input) {
		 if (input.equalsIgnoreCase("toggleshowreport")){
			 showReport = !showReport;
			 System.out.println("ShowReport is set to " + showReport + "\n");
			 
		 } else if (input.equalsIgnoreCase("toggleshowresult")){
			 showResult = !showResult;
			 System.out.println("showResult is set to " + showResult + "\n");
		 } else if (input.equalsIgnoreCase("toggleshowresult")){
			 writeOutput = !writeOutput;
			 System.out.println("writeOutput is set to " + writeOutput + "\n");
		 }
		
	}
	
	
	/**
	 * Helper method read input from console
	 * @param prompt String to print
	 * @return user input
	 */
	public static String readFromKeyboard(String prompt){

		InputStreamReader stdin = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(stdin);
		try {
			System.out.print(prompt);
			String input = in.readLine();

			return input;
		} catch (IOException e){

		}
		return null; 
	}
	
	/**
	 * Print the report if toggleShowCommand or toggleShowResult is enter as command.
	 * @param file_name
	 * @param outputFileName
	 * @param line
	 * @param digraph
	 * @param degreeCounter
	 * @param to
	 * @param cycleFinder
	 */
	public static void processReport(String file_name, String outputFileName, String line[], Graph digraph, DegreeCounter degreeCounter, 
			TopologicalOrder to, CycleFinder cycleFinder){
	    if (showReport) {
	    	System.out.println("Filename: " + file_name +"\n");
	        digraph.print();
	        degreeCounter.printReport();
	        if (to.orderExist) { to.printReport(); } 
	        else { cycleFinder.printReport();}
	    }
	    if (showResult) {
	    	System.out.println("Filename: " + outputFileName);
	    	System.out.printf("Result:%n%s%n%s%n%s%n%s%n%s%n%n",line[1],line[2],line[3],line[4],line[5]);
	    	
	    }
	    
		
	}
	
}
