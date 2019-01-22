package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import util.Node;

public class Rubik implements Problem{

	static String[] moves = {"T", "F", "R", "TC", "FC", "RC"};
	char[] input = new char[24];
	static char[][] cubieColors = new char[][]{
        {'y', 'r', 'b'}, {'r', 'b', 'y'}, {'b', 'y', 'r'},
        {'y', 'b', 'o'}, {'b', 'o', 'y'}, {'o', 'y', 'b'},
        {'y', 'g', 'r'}, {'g', 'r', 'y'}, {'r', 'y', 'g'},
        {'y', 'o', 'g'}, {'o', 'g', 'y'}, {'g', 'y', 'o'},
        {'w', 'b', 'r'}, {'b', 'r', 'w'}, {'r', 'w', 'b'},
        {'w', 'o', 'b'}, {'o', 'b', 'w'}, {'b', 'w', 'o'},
        {'w', 'r', 'g'}, {'r', 'g', 'w'}, {'g', 'w', 'r'},
        {'w', 'g', 'o'}, {'g', 'o', 'w'}, {'o', 'w', 'g'}
    };
	
    public Rubik() {
		// TODO Auto-generated constructor stub
    	getInput();
	}
	@Override
	public Node initialState() {
		// TODO Auto-generated method stub
		long state = 0;
		char[][] cubies = new char[8][3];
        cubies[0] = new char[]{input[0],    input[16],  input[14]};
        cubies[1] = new char[]{input[1],    input[15],  input[21]};
        cubies[2] = new char[]{input[2],    input[4],   input[17]};
        cubies[3] = new char[]{input[3],    input[20],  input[5]};
        cubies[4] = new char[]{input[10],   input[12],  input[18]};
        cubies[5] = new char[]{input[11],   input[23],  input[13]};
        cubies[6] = new char[]{input[8],    input[19],  input[6]};
        cubies[7] = new char[]{input[9],    input[7],   input[22]};
        for (int x = 0; x < 8; x++){
            int index = 0;
            while (true){
                if (Arrays.equals(cubies[x], cubieColors[index])) {
                    break;
                }
                index++;
            }
            state*=100;
            state+=index;
        }
		return new Node(state);
	}

	@Override
	public Node goalState() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public ArrayList<Integer> actions(long state) {
		// TODO Auto-generated method stub
		ArrayList<Integer> acts = new ArrayList<>();
		for (int i=1; i <=6; i++)
			acts.add(new Integer(i));
		
		return acts;
	}

	@Override
	public Node result(long state, int action) {
		// TODO Auto-generated method stub
		int[] index = new int[8];
		for (int i=7; i>=0; i--){
			index[i] = (int) (state%100);
			state /=100;
		}
		long newState = 0;
		int pos, rot;
		switch(action){
		case 1:	//T
			newState+=index[2];
			newState*=100;
			newState+=index[0];
			newState*=100;
			newState+=index[3];
			newState*=100;
			newState+=index[1];
			newState*=100;
			newState+=index[4];
			newState*=100;
			newState+=index[5];
			newState*=100;
			newState+=index[6];
			newState*=100;
			newState+=index[7];
			break;
		case 2: //Tc
			newState+=index[1];
			newState*=100;
			newState+=index[3];
			newState*=100;
			newState+=index[0];
			newState*=100;
			newState+=index[2];
			newState*=100;
			newState+=index[4];
			newState*=100;
			newState+=index[5];
			newState*=100;
			newState+=index[6];
			newState*=100;
			newState+=index[7];
			break;
		case 3: //F
			newState+=index[0];
			newState*=100;
			newState+=index[1];
			newState*=100;
			pos = index[6]/3;
			rot = ((index[6]%3)+1)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			pos = index[2]/3;
			rot = ((index[2]%3)+2)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[4];
			newState*=100;
			newState+=index[5];
			newState*=100;
			pos = index[7]/3;
			rot = ((index[7]%3)+2)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			pos = index[3]/3;
			rot = ((index[3]%3)+1)%3;
			newState+= (pos*3 + rot);
			break;
		case 4: //FC
			newState+=index[0];
			newState*=100;
			newState+=index[1];
			newState*=100;
			pos = index[3]/3;
			rot = ((index[3]%3)+1)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			pos = index[7]/3;
			rot = ((index[7]%3)+2)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[4];
			newState*=100;
			newState+=index[5];
			newState*=100;
			pos = index[2]/3;
			rot = ((index[2]%3)+2)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			pos = index[6]/3;
			rot = ((index[6]%3)+1)%3;
			newState+= (pos*3 + rot);
			break;
		case 5:	//R
			newState+=index[0];
			newState*=100;
			pos = index[3]/3;
			rot = ((index[3]%3)+2)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[2];
			newState*=100;
			pos = index[7]/3;
			rot = ((index[7]%3)+1)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[4];
			newState*=100;
			pos = index[1]/3;
			rot = ((index[1]%3)+1)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[6];
			newState*=100;
			pos = index[5]/3;
			rot = ((index[5]%3)+2)%3;
			newState+= (pos*3 + rot);
			break;
		case 6:	//RC
			newState+=index[0];
			newState*=100;
			pos = index[5]/3;
			rot = ((index[5]%3)+2)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[2];
			newState*=100;
			pos = index[1]/3;
			rot = ((index[1]%3)+1)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[4];
			newState*=100;
			pos = index[7]/3;
			rot = ((index[7]%3)+1)%3;
			newState+= (pos*3 + rot);
			newState*=100;
			newState+=index[6];
			newState*=100;
			pos = index[3]/3;
			rot = ((index[3]%3)+2)%3;
			newState+= (pos*3 + rot);
			break;
		
		}
		return new Node(newState);
		
	}

	@Override
	public boolean goalTest(long state) {
		// TODO Auto-generated method stub
		int[] index = new int[8];
		for (int x=7; x>=0; x--){
			index[x] = (int) (state%100);
			state/=100;
		}
		if (cubieColors[index[0]][0]==cubieColors[index[1]][0] && cubieColors[index[0]][0]==cubieColors[index[2]][0] && cubieColors[index[0]][0]==cubieColors[index[3]][0])
			if (cubieColors[index[2]][1]==cubieColors[index[3]][2] && cubieColors[index[2]][1]==cubieColors[index[6]][2] && cubieColors[index[2]][1]==cubieColors[index[7]][1])
				if (cubieColors[index[6]][0]==cubieColors[index[7]][0] && cubieColors[index[6]][0]==cubieColors[index[4]][0] && cubieColors[index[6]][0]==cubieColors[index[5]][0])
					if (cubieColors[index[4]][1]==cubieColors[index[5]][2] && cubieColors[index[4]][1]==cubieColors[index[0]][2] && cubieColors[index[4]][1]==cubieColors[index[1]][1])
						if (cubieColors[index[0]][1]==cubieColors[index[2]][2] && cubieColors[index[0]][1]==cubieColors[index[4]][2] && cubieColors[index[0]][1]==cubieColors[index[6]][1])
							if (cubieColors[index[3]][1]==cubieColors[index[1]][2] && cubieColors[index[3]][1]==cubieColors[index[7]][2] && cubieColors[index[3]][1]==cubieColors[index[5]][1])
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
		return 0;
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for(int i =0; i<24; i++)
			input[i] = sc.next().charAt(0);
	}
	
	public ArrayList showPath(ArrayList<Node> path){
		ArrayList<Character> acts = new ArrayList<>();
		for(int i=path.size()-2; i>=0; i--){
			
			long now = path.get(i).getState();
			long next = path.get(i+1).getState();

			System.out.println(now);
			System.out.println(next);
			int [] now_i = new int[8];
			int [] next_i =new int[8];
			for (int j=7; j>=0; j--){
				now_i[j]=(int) (now%100);
				now/=100;
				next_i[j]=(int) (next%100);
				next/=100;
			}
			if (now_i[7]==next_i[7]){	
				if (now_i[2]==next_i[0]){ 	//T
					acts.add('T');
				}else if(now_i[1]==next_i[0]){	//TC
					acts.add('T');
					acts.add('C');
				}
			}else if (now_i[2]==next_i[2]){
				if ((now_i[3]/3)*3+((now_i[3]+2)%3) == next_i[1])
					acts.add('R');
				else if ((now_i[5]/3)*3+((now_i[5]+2)%3) == next_i[1]){
					acts.add('R');
					acts.add('C');
				}
				
			}else if(now_i[1] == next_i[1]){
				if ((now_i[6]/3)*3+((now_i[6]+1)%3) == next_i[2])
					acts.add('F');
				else if ((now_i[3]/3)*3+((now_i[3]+1)%3) == next_i[2]){
					acts.add('F');
					acts.add('C');
				}
			}
			else 
				acts.add('X');
			
		}
		return acts;
	}

}
