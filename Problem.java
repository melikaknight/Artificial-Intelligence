package problem;

import java.util.ArrayList;

import util.Node;

public interface Problem {

	Node initialState();
	Node goalState();
	ArrayList<Integer> actions(long state);	
	Node result(long state, int action);
	boolean goalTest(long state);
	int stepCost(long currentState, int action, long nextState);
	double huristic(long state);
	void getInput();
	
}
