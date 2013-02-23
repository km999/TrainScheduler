package trainScheduler.fileparser;

// every expression handler needs to be able to handle input and to give information about its symbol (operator/flag/identifier)
public interface IExpressionHandler {
	final String symbol = "";
		
	public String getSymbol();

	public void handleInput(String[] tailParts) throws Exception;
}