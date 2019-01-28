package problem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import utils.Node;

public class GraphColoring implements Problem{
	
	int color;
	boolean [][] graph ;
	int n; 
	
	public GraphColoring() {
		// TODO Auto-generated constructor stub
		getInput();
	}
	
	@Override
	public Node initialState() {
		// TODO Auto-generated method stub
		int [] c = new int[n];
		for(int i=0; i<n; i++)
			c[i]=new Random().nextInt(color);
		return new Node(c);
	}

	@Override
	public Node goalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> actions(Object state) {
		// TODO Auto-generated method stub
		ArrayList<Integer> acts = new ArrayList<>();
		for(int i=0; i<n; i++)
			acts.add(i);
		return acts;
	}

	@Override
	public Node result(Object state, int action) {
		// TODO Auto-generated method stub
		int [] newState = ((int [])state).clone();
		newState[action] = (newState[action]+1)%color;
		return new Node(newState);
	}

	@Override
	public boolean goalTest(int state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int stepCost(Object currentState, int action, Object nextState) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double huristic(Object state) {
		// TODO Auto-generated method stub
		int same = 0, tot=0;
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				if(graph[i][j]){
					tot++;
					if(((int[])state)[i] == ((int[])state)[j])
						same++;
				}
			}
		}
//		System.out.println("same "+same);
		return same;
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		graph = new boolean[n][n];
		for(int i=0; i<m; i++){
			int v1 = sc.nextInt(), v2 = sc.nextInt();
			graph[v1][v2]=true;
			graph[v2][v1] = true;
		}
		color = sc.nextInt();
	}

	@Override
	public Node generateRandomNode() {
		// TODO Auto-generated method stub
		int [] c = new int[n];
		for(int i=0; i<n; i++)
			c[i]=new Random().nextInt(color);
		return new Node(c);
	}

	@Override
	public void showResult(Object state) {
		// TODO Auto-generated method stub
		for(int i : ((int [])state))
			System.out.print(i+" ");
		System.out.println();
	}

}
