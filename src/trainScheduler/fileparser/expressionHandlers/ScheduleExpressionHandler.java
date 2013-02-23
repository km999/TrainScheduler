package trainScheduler.fileparser.expressionHandlers;

import java.util.List;
import java.util.Arrays;

import trainScheduler.connections.ScheduleRegister;
import trainScheduler.fileparser.IExpressionHandler;
import trainScheduler.trains.Train;
import trainScheduler.trains.TrainRegister;

//input is in the form: identifier,trainIdentifier,(connectionIdentifier)+
//register the schedule
public class ScheduleExpressionHandler implements IExpressionHandler {
	private String symbol = "Schedule";
	
	@Override
	public void handleInput(String[] inputParts) throws Exception {
		
		String identifier = inputParts[0].trim();
		Train train = TrainRegister.getInstance().getElementByIdentifier(inputParts[1].trim());
		List<String> connections = Arrays.asList(Arrays.copyOfRange(inputParts, 2, inputParts.length));
		
		if(train == null || connections.size() <= 0){
			throw new Exception("Either the train is not existing or there are no connections");
		}
		
		ScheduleRegister.getInstance().createSchedule(identifier, train, connections);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
