package API;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BestFirstSearch<T> extends CommonSearcher<T> {
    private StateGrader<T> grader;
    private Comper<T> comparator;

	
	public BestFirstSearch(StateGrader<T> grader ,Comper<T> comparator)
	{
		this.grader = grader;
		this.comparator = comparator;
		openList = new PriorityQueue<State<T>>(1,comparator);
	}
	
	public Solution search(Searchable<T> s) throws CloneNotSupportedException
	{ 
		
		openList.add(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		while (openList.size() > 0) {
			State<T> n = popOpenList();// dequeue
			closedSet.add(n);

			if (s.isGoalState(n))
				return backTrace(n);

			List<State<T>> successors = s.getPossibleStates(n,true);
			for (State<T> st : successors) {
				this.grader.grade(st);
				if (!closedSet.contains(st) && !openListContains(st)) {
					openList.add(st);
				}
			}
		}
		return null;
	}

	private Solution backTrace(State<T> s) {

		Solution sol = new Solution();
		String a;
		State<T> n = s.getCameFrom();
		while (n != null) {
			a = s.pos[0] + "," + s.pos[1] + "," + "1";
			sol.add(a);
			s = s.getCameFrom();
			n = s.getCameFrom();
		}
		sol.add("done");
		return sol;

	}

}
	


