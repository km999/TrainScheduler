package trainScheduler.fileparser.expressionHandlers;

import java.util.Arrays;
import java.util.List;

import trainScheduler.fileparser.IExpressionHandler;
import trainScheduler.trains.TrainRegister;

//input is in the form: identifier,(trainIdentifier)*
//register the composite train
public class TrainExpressionHandler implements IExpressionHandler {

	private String symbol = "Train";
	
	@Override
	public void handleInput(String[] inputParts) {
		List<String> identifiers = Arrays.asList(Arrays.copyOfRange(inputParts, 1, inputParts.length));
		TrainRegister.getInstance().createCompositeTrainByIdentifier(inputParts[0], identifiers);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
