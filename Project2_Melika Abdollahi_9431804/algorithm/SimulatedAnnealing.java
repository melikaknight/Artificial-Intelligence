package algorithm;

import java.util.Random;

import problem.Problem;
import utils.Node;

public class SimulatedAnnealing {

	Problem problem;
	int visited=0, expanded=0;
	
	public SimulatedAnnealing(Problem problem) {
		this.problem = problem;
	}

	public Node anneal(int coolingSchema) {
		double t = 1, tMin = 0.0001, alpha = 0.9;

		Node current = problem.initialState();
		visited++;
		int cycle=0;
		while (t > tMin) {
//			System.out.println(cycle);
			cycle++;
			int i = 1;
			while (i++ < 100) {
//				System.out.println(i);
				Object state = current.getState();
				expanded++;
				if (problem.huristic(state) == 0){
					System.out.println(visited + " node visited and "+ expanded +" node expanded!");
					return current;
				}
				int action = problem.actions(state).get(new Random().nextInt(problem.actions(state).size()));
				Node neighbour = problem.result(state, action);
				visited++;
				double ap = acceptanceProbability(problem.huristic(state), problem.huristic(neighbour.getState()), t);
				double r = new Random().nextDouble();
				if (r < ap) {
					current = neighbour;
				}
			}
			t = newTempreture(coolingSchema, t, cycle);
		}
		
		System.out.println(visited + " node visited and "+ expanded +" node expanded!");
		return current;
	}

	private double newTempreture(int coolingSchema, double tempreture, int cycle) {
		double alpha = 0.9;
		switch (coolingSchema) {
		case 1:
			return tempreture*alpha;
		case 2:
			return 1/(1+(alpha+1)*Math.log(1+cycle));
		case 3:
			return 1/(1+alpha*cycle);
		default:
			System.out.println("choose a valid schema (between 1 and 3)!");
			return tempreture;
		}
	}

	private double acceptanceProbability(double oldCost, double newCost, double temp) {
		return Math.pow(Math.E, (oldCost - newCost) / temp);
	}
}
