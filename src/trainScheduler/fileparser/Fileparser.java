/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// line by line expression analyser
public class Fileparser {

	private String filename;
	private final String filepath = "fileInput/"; // this is where we want our input!
	
	// c'tor
	public Fileparser(String file){
		this.filename = file;
	}
	
	public void parseFile() throws Exception{
		ExpressionAnalyser exAn = new ExpressionAnalyser();
		String line = "";
		
		// open file for reading
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.filepath + this.filename)));
		while ((line = br.readLine()) != null) { // parse each line individually
			try{
				exAn.parseExpression(line);
			} catch(Exception e){ // something went wrong (we don't care what, but we print what line it was)
				br.close();
				System.out.println("Caught exception while parsing line:\n"+line);
				throw e;
			}
		}
		br.close(); // close file reader
	}
}
