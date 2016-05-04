package baseball;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;

public class BaseballElimination {
	private int numTeams;
	private void readFile(String filename){
		In in = new In(filename);
		numTeams = in.readInt();
		int teamNum = 0;
		FlowNetwork fn = new FlowNetwork(V, E);
		String[] lines = in.readAllLines();
		int nodeId = numTeams;// incremental the node id, starts from the number of team
		for(String line:lines){
			String[] words = line.split(" ");
			String name = words[0];
			// win loss left NY Bal Bos Tor Det
			// 0  New York      75   59   28     -   3   8   7   3
			for(int i=4; i < words.length; i++){
				if(i ==  teamNum)// if it's the team itself then continue
					continue;
				Edge edge= new Edge(v, w, weight);
				
			}
			teamNum ++;
		}
	}
	public BaseballElimination(String filename){
		this.readFile(filename);
	}
}
