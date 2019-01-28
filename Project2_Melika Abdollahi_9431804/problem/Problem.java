package problem;

import java.util.ArrayList;

import utils.Node;

public interface Problem {

	Node initialState();
	Node goalState();
	ArrayList<Integer> actions(Object state);	
	Node result(Object state, int action);
	boolean goalTest(int state);
	int stepCost(Object currentState, int action, Object nextState);
	double huristic(Object state);
	
	void getInput();
	void showResult(Object state);
	
	Node generateRandomNode();
	
}
