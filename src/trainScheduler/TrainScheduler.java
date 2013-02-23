/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import trainScheduler.connections.Schedule;
import trainScheduler.connections.ScheduleRegister;
import trainScheduler.fileparser.Fileparser;
import trainScheduler.queries.QueryMinOfTrackWeights;
import trainScheduler.queries.QueryMinOfConnectionWeights;
import trainScheduler.queries.QueryMinTrainSwitches;
import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.tracks.Track;
import trainScheduler.tracks.TrackRegister;

public class TrainScheduler {

	// command line reader
	private BufferedReader br;
	
	// c'tor
	public TrainScheduler(){
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// run loop
	public void run() throws Exception{
		this.showToUser("Trainscheduler"); // nice headline print
		this.printChoices();				// show what we offer
		
		String input;
		
		// as long as we have input (no EOF)
		while((input = this.br.readLine()) != null){
			int choice = this.getInputNumber(input);
			switch(choice){ // depending on their choice do some stuff
				case 1: // parse a file
					this.handleFileParser();
					break;
				case 2: // execute a query on the current representations
					this.handleQueryRequest();
					break;
				case 3: // exit (return)
					this.showToUser("Exiting the system. Thank you for using.");
					return;
				default:
					this.showToUser("Wrong input. Try again...");
					break;
			}
			
			this.printChoices();
			
		}
	}
	
	// analyse what the user typed (what number) and return a the case number
	public int getInputNumber(String input){
		int choice = 0;
		try{
			choice = Integer.parseInt(input.trim());
		} catch (NumberFormatException nfex){ // something completely wrong typed in, set as 0, it will go to default
			choice = 0;
		}
		return choice;
	}
	
	// user specified that he wants to execute a query
	public void handleQueryRequest() throws Exception{
		
		StationRegister sr = StationRegister.getInstance();
		ArrayList<Schedule> schedules = ScheduleRegister.getInstance().getAllElements();
		
		this.printQueryChoices();
		String input = this.br.readLine();
		int choice = getInputNumber(input.trim());

		if (choice == 5){return;} // we want to exit before specifying the stations... (not nice, but working)
		
		// specify the start and end stations
		this.showToUser("Please enter the identifier of the station where you want to set off.");
		String stationFromIdentifier = this.br.readLine().trim();
		this.showToUser("Please enter the identifier of your destination");
		String stationToIdentifier = this.br.readLine().trim();
		Station from = sr.getElementByIdentifier(stationFromIdentifier);
		Station to = sr.getElementByIdentifier(stationToIdentifier);
		
		// specifications are wrong, return!
		if(from == null || to == null){
			this.showToUser("At least one of the stations you specified does not exist. Exiting.");
			return;
		}
		
		String answerStart;
		String answerEnd;
		String message;
		switch(choice){
		case 0:
			this.showToUser("Your input \n\t"+input+"\n is not a valid input.");
			break;
		case 1: // stations are connected by tracks?
			ArrayList<Track> tracks = TrackRegister.getInstance().getAllAtomicTracks();
			QueryMinOfTrackWeights qc = new QueryMinOfTrackWeights(sr.getAllElements(), tracks);
			
			answerStart = "The station "+to.getIdentifier()+" can";
			answerEnd = " be reached from "+from.getIdentifier()+" by a set of atomic tracks";
			if(qc.execute(from, to) != null){
				message = answerStart + answerEnd;
			} else {
				message = answerStart +" NOT"+ answerEnd;
			}
			this.showToUser(message);
			break;
		case 2: // stations are connected by connections?
			QueryMinOfConnectionWeights qmcw = new QueryMinOfConnectionWeights(sr.getAllElements(), schedules);
			
			answerStart = "The station "+to.getIdentifier()+" can";
			answerEnd = " be reached from "+from.getIdentifier()+" via train connections";
			if(qmcw.execute(from, to) != null){ // if not null, then connected
				message = answerStart + answerEnd;
			} else {
				message = answerStart +" NOT"+ answerEnd;
			}
			this.showToUser(message);
			break;
		case 3: // how long does it take to get from A to B?
			QueryMinTrainSwitches qmts = new QueryMinTrainSwitches(sr.getAllElements(), schedules);
			Double result = qmts.execute(from, to);
			if (result == null){
				this.showToUser("It is not possible to get from "+from.getIdentifier()+" to "+to.getIdentifier()+" with the given schedules");
			} else {
				this.showToUser("To get from "+from.getIdentifier()+" to "+to.getIdentifier()+" you need to switch trains "+result.intValue()+" times");
			}
			break;
		case 4: // we want to know how often we need to switch between stations
			QueryMinOfConnectionWeights qmc = new QueryMinOfConnectionWeights(sr.getAllElements(), schedules);
			
			answerStart = "The station "+to.getIdentifier()+" can";
			answerEnd = " be reached from "+from.getIdentifier()+" via train connections";
			if (qmc.execute(from, to) == null){
				this.showToUser("It is not possible to get from "+from.getIdentifier()+" to "+to.getIdentifier()+" with the given schedules");
			} else {
				this.showToUser("It takes "+qmc.execute(from, to)+" time units to get from "+from.getIdentifier()+" to "+to.getIdentifier()+".");
			}
			break;
		default:
			this.showToUser("Wrong input. Try again...");
			break;
		}
	}
	
	// what queries do we offer?
	public void printQueryChoices(){
		this.showToUser("Choose from the following options:");
		this.showToUser("1) Check if Stations are connected by tracks");
		this.showToUser("2) Check if Stations are connected by any connections");
		this.showToUser("3) Get the minimum number of train switches to get from one to another station");
		this.showToUser("4) Get the minimum connection time between two stations");
		this.showToUser("5) Cancel. Return to Main menu.");
	}
	
	// we want to parse a file, here's the interaction (input and output)
	public void handleFileParser() throws Exception{
		this.showToUser("Enter the name of the file to parse:");
		String filename = this.br.readLine();
		Fileparser fp = new Fileparser(filename);
		try {
			fp.parseFile();
		} catch (FileNotFoundException fnfEx) {
			this.showToUser("Couldn't find the input file "+filename);
		}
		
	}
	
	// what can we do here?
	public void printChoices(){
		this.showToUser("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		this.showToUser("The Trainscheduler supports the following options:");
		this.showToUser("1) Read input from file");
		this.showToUser("2) Query the current input state");
		this.showToUser("3) Exit Trainscheduler");
	}
	
	// in case we also want to print somewhere else (log) or change our output
	public void showToUser(String str){
		System.out.println(str);
	}
	
}
