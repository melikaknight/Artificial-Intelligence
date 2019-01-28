package algorithm;

import java.util.ArrayList;
import java.util.Random;

import problem.Problem;
import utils.Node;

public class RandomHillClimbing {

	Node current;
	Problem problem;
	int visited=0, expanded=0;

	public RandomHillClimbing(Problem problem) {
		this.problem = problem;
	}

	public Node search() {
		current = problem.initialState();

		visited++;
		System.out.println("search started....");
		while (true) {
			Object state = current.getState();
			expanded++;
			double myEvaluate = problem.huristic(state);
			ArrayList<Node> betterNeighbours = new ArrayList<>();

//			System.out.println(state + "  " + problem.huristic(state));
			for (int act : problem.actions(state)) {
				Node neighbour = problem.result(state, act);
				if (myEvaluate> problem.huristic(neighbour.getState())) {
					betterNeighbours.add(neighbour);
				}

			}
		
			if (betterNeighbours.size() == 0) { // current node is a local maximum
				break;
			} else {				
				current = betterNeighbours.get(new Random().nextInt(betterNeighbours.size()));
				visited++;
			}
		}
		
		System.out.println(visited + " node visited and "+ expanded +" node expanded!");
		
		return current;
	}

}
