package trainScheduler.fileparser;

import java.util.HashMap;

import trainScheduler.fileparser.expressionHandlers.*;

public class ExpressionAnalyser {

	private static final String commentSymbol = "#"; // this defines a comment (we cut off comments)
	private static final String headTailSeparator = ":="; // this separates the 'head' and the 'tail' of a rule
	private static final String argumentSeparator = ","; // separates the list of arguments in the tail
	
	// symbol, ExpressionHandler mapping
	private HashMap<String, IExpressionHandler> operators = new HashMap<String, IExpressionHandler>();
	
	// c'tor
	public ExpressionAnalyser(){
		// add all expression handlers to the hashmap
		this.addOperator(new AtomicTrackExpressionHandler());
		this.addOperator(new ConnectionExpressionHandler());
		this.addOperator(new LocomotiveExpressionHandler());
		this.addOperator(new RouteExpressionHandler());
		this.addOperator(new ScheduleExpressionHandler());
		this.addOperator(new StationExpressionHandler());
		this.addOperator(new TrainExpressionHandler());
		this.addOperator(new WagonExpressionHandler());

	}
	// we add an operator to the mapping, based on the symbol we can decide what class to call
	private void addOperator(IExpressionHandler expHandler){
		this.operators.put(expHandler.getSymbol().toUpperCase(), expHandler);
	}

	public void parseExpression(String input) throws Exception{
		input = ExpressionAnalyser.cutOffComment(input); 					// remove the part after the first commentSymbol
		if (input.trim().equals("")){return;} 								// if the line is empty -> do nothing
		String[] headAndTail = ExpressionAnalyser.splitHeadAndBody(input);	// split head and tail
		this.parseRuleInput(headAndTail[0], headAndTail[1]);				// and now work on it
	}
	
	
	private void parseRuleInput(String head, String tail) throws Exception{
		// see if there is a class defined for that rule head
		IExpressionHandler expHandler = this.operators.get(head.trim().toUpperCase()); 		

		if (expHandler == null){ // if there is no class, throw an exception
			throw new Exception("Illegal input. Head: "+head+ " Tail: "+tail);
		}
		String[] tailParts = splitTail(tail); 	// split the tail into a list of trimmed Strings
		expHandler.handleInput(tailParts);		// now inject the input into the class
	}
	
	// take a string, split it at argumentSeparators and trim the individual parts
	private static String[] splitTail(String input){
		String[] tailparts = input.split(argumentSeparator);
		for (int i = 0; i < tailparts.length; i++){
			tailparts[i] = tailparts[i].trim();
		}
		return tailparts;
	}
	
	// remove the part after the first comment symbol (if there is one)
	private static String cutOffComment(String input){
		String[] inputParts = input.split(commentSymbol,2);
		input = inputParts[0].trim();
		return input;
	}
	
	private static String[] splitHeadAndBody(String input){
		return input.split(headTailSeparator,2);
		
	}
}
