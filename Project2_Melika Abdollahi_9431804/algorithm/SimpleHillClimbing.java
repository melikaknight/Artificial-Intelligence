package algorithm;

import problem.Problem;
import utils.Node;

public class SimpleHillClimbing {

	Node current;
	Problem problem;
	
	int visited=0, expanded=0;

	public SimpleHillClimbing(Problem problem) {
		this.problem = problem;
	}

	public Node search() {
		current = problem.initialState();

		visited++;
		double betterEvaluate = problem.huristic(current.getState());
		Node betterNeighbour = null;
		System.out.println("search started....");
		
		while (true) {
			Object state = current.getState();
			expanded++;
//			System.out.println(state + "  " + problem.huristic(state));
			boolean isLocalMax = true;
			
			for (int act : problem.actions(state)) {
				Node neighbour = problem.result(state, act);
				visited++;
				double h = problem.huristic(neighbour.getState());
				if (betterEvaluate > h) {	// = for shoulders!
					betterEvaluate = h;
					betterNeighbour = neighbour;
					isLocalMax = false;
				}

			}
			if (isLocalMax) { // current node is a local maximum
				break;
			} else {
				current = betterNeighbour;
			}
		}
		
		System.out.println(visited + " node visited and "+ expanded +" node expanded!");
		
		return current;
	}

}
