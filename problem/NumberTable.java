package problem;

import java.util.ArrayList;
import java.util.Scanner;

import util.Node;

public class NumberTable implements Problem{

	long input = 0;
	
	public NumberTable() {
		// TODO Auto-generated constructor stub
		getInput();
	}
	@Override
	public Node initialState() {
		Node init = new Node(input);
		return init;
	}

	@Override
	public Node goalState() {
		Node goal = new Node(12345678);
		return goal;
	}

	@Override
	public ArrayList<Integer> actions(long state) {
		ArrayList<Integer> acts = new ArrayList<>();
		int[] pos = new int[9];
		int k=0;
		while(state > 0){
			pos[8-k] = (int) (state%10);
			state/=10;
			k++;
		}
		int p=0;
		for(int i=0; i<9; i++){
			if(pos[i] == 0){
				p=i;
				break;
			}
		}
		// go up
		if(p-3 >= 0)
			acts.add(1);
		// go down
		if(p+3 < 9)
			acts.add(3);
		// go right
		if((p+1 <9) && ((p/3) == ((p+1)/3)))
			acts.add(2);
		// go left
		if((p-1 >= 0) && ((p/3) == ((p-1)/3)))
			acts.add(4);
		
		return acts;
	}

	@Override
	public Node result(long state, int action) {
		int[] pos = new int[9];
		int k=0;
		while(state > 0){
			pos[8-k] = (int) (state%10);
			state/=10;
			k++;
		}
		int p =0;
		for(int i=0; i<9; i++){
			if(pos[i] == 0){
				p=i;
				break;
			}
		}
		switch(action){
		case 1:	// up
			pos[p]=pos[p-3];
			pos[p-3]=0;
			break;
		case 2:	// right
			pos[p]=pos[p+1];
			pos[p+1]=0;
			break;
		case 3:	// down
			pos[p]=pos[p+3];
			pos[p+3]=0;
			break;
		case 4:	// left
			pos[p]=pos[p-1];
			pos[p-1]=0;
			break;
		}
		
		int newState = 0;
		for(int i=0; i<9; i++){
			newState *= 10;
			newState+=pos[i];
		}
		
		return new Node(newState);
	}

	@Override
	public boolean goalTest(long state) {
		if (state == 12345678)
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
		int[] pos = new int[9];
		int k=0;
		while(state > 0){
			pos[8-k] = (int) (state%10);
			state/=10;
			k++;
		}
		int diff = 0;
		for (int i=1; i<=9; i++)
			if( pos[i-1] != i)
				diff++;
		return diff;
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		Scanner sc = new  Scanner(System.in);
		for(int i =0; i<9; i++){
			input*=10;
			input+=sc.nextInt();
		}
	}
	
	public ArrayList<Character> showPath(ArrayList<Node> path){
		ArrayList<Character> acts = new ArrayList<>();
		for(int i = 0; i<path.size()-1; i++){
			long now = path.get(i).getState();
			long next = path.get(i+1).getState();
			int[] now_i = new int[9];
			int[] next_i = new int[9];
			int k=0;
			while(now > 0){
				now_i[8-k] = (int) (now%10);
				now/=10;
				k++;
			}
			k=0;
			while(next > 0){
				next_i[8-k] = (int) (next%10);
				next/=10;
				k++;
			}
			int now_p=0, next_p=0;
			for(int ii=0; ii<9; ii++){
				if(now_i[ii] == 0){
					now_p=ii;
					break;
				}
			}
			for(int ii=0; ii<9; ii++){
				if(next_i[ii] == 0){
					next_p=ii;
					break;
				}
			}
			if (next_p == now_p+1)
				acts.add('L');
			else if (next_p == now_p-1)
				acts.add('R');
			else if (next_p == now_p-3)
				acts.add('D');
			else 
				acts.add('U');
		}
		return acts;
	}

}
