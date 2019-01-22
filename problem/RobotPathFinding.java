package problem;

import java.util.ArrayList;
import java.util.Scanner;

import problem.Problem;
import util.Node;

public class RobotPathFinding implements Problem {

	int n, m;
	ArrayList<Integer[]> ob;

	@Override
	public Node initialState() {
		// TODO Auto-generated method stub
		return new Node(0);
	}

	@Override
	public ArrayList<Integer> actions(long state) {
		// TODO Auto-generated method stub
		ArrayList<Integer> acts = new ArrayList<>();
		long i = state / n, j = state % n;
		boolean[] flag = new boolean[4];
		for (Integer[] aa : ob) {
			if (aa[0] == i && aa[2] == i)
				if ((aa[1] == j && aa[3] == j + 1)
						|| (aa[1] == j + 1 && aa[3] == j))
					flag[0] = true;
				else
					flag[2] = true;
			else if (aa[1] == j && aa[3] == j)
				if ((aa[0] == i && aa[2] == i + 1)
						|| (aa[0] == i + 1 && aa[2] == i))
					flag[1] = true;
				else
					flag[3] = true;
		}
		// right
		if (!flag[0])
			if (j < m - 1)
				acts.add(1);
		// left
		if (!flag[2])
			if (j > 0)
				acts.add(3);
		// up
		if (!flag[3])
			if (i > 0)
				acts.add(4);
		// down
		if (!flag[1])
			if (i < n - 1)
				acts.add(2);
		return acts;
	}

	@Override
	public Node result(long state, int action) {
		// TODO Auto-generated method stub
		long i = state / n, j = state % n;
		switch (action) {
		case 1:
			return new Node(i * m + (j + 1));
		case 2:
			return new Node((i + 1) * m + j);
		case 3:
			return new Node(i * m + (j - 1));
		case 4:
			return new Node((i - 1) * m + j);
		}
		return new Node(state);
	}

	@Override
	public boolean goalTest(long state) {
		// TODO Auto-generated method stub
		if (state == m*n - 1)
			return true;
		return false;
	}

	@Override
	public int stepCost(long currentState, int action, long nextState) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double huristic(long state) {
		// TODO Auto-generated method stub
		long i = state / n, j = state % n;
		return Math.sqrt(Math.pow((n - i - 1),2) + Math.pow((m - j - 1),2));
	}

	public RobotPathFinding() {
		// TODO Auto-generated constructor stub
		getInput();
	}
	@Override
	public Node goalState() {
		// TODO Auto-generated method stub
		return new Node(m*n-1);
	}

	public ArrayList showPath(ArrayList<Node> path){
		ArrayList<Character> actions = new ArrayList();
		for(int i=path.size()-2; i>=0; i--){
			long now = path.get(i).getState();
			long next = path.get(i+1).getState();
			if(next==now+1)
				actions.add('r');
			else if(next == now-1)
				actions.add('l');
			else if(next == now-m)
				actions.add('u');
			else
				actions.add('d');
		}
		return actions;
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		ArrayList<Integer[]> o = new ArrayList<Integer[]>();
		// robot path finding
		Scanner sysi=new Scanner(System.in);

		n = sysi.nextInt();
		m = sysi.nextInt();
		int k = sysi.nextInt();
		for(int i =0; i<k; i++){
			Integer[] obstacle = new Integer[4];
			for(int j=0; j<4; j++)
				obstacle[j]=sysi.nextInt();
			o.add(obstacle);
			}
		this.ob = o;
	}
}
