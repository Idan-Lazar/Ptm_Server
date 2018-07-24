package API;

import java.util.List;
import java.util.Random;


public class HillClimbingSearcher<T> extends CommonSearcher<T> {


    private long timeToRun;
    private StateGrader<T> grader;


    HillClimbingSearcher(StateGrader<T> grader, long timeToRun) {
        this.grader = grader;
        this.timeToRun = timeToRun;
    }
	public Solution search(Searchable<T> searchable) throws CloneNotSupportedException
	{
  //Define the current state as an initial state
    State<T> next = searchable.getInitialState();
    Solution result = new Solution();
    
    long time0 = System.currentTimeMillis();


    //Loop until the goal state is achieved or no more operators can be applied on the current state:
    while (System.currentTimeMillis() - time0 < timeToRun && !(searchable.isGoalState(next))) {
        List<State<T>> neighbors = searchable.getPossibleStates(next,false);

        if (Math.random() < 0.7) { // with a high probability
            // find the best one
            int grade = 0;
            for (State<T> step : neighbors) {
                int g = grader.grade(step);
                if (g > grade) {
                    grade = g;
                    next = step;
                }
            }
            
        } else {
            next = neighbors.get(new Random().nextInt(neighbors.size()));
        }
    }
    result = backtrace(next);

    return result;
	}
	public Solution backtrace(State<T> s)
	{
		Solution sol = new Solution();
		String a;
		State<T> n = s.getCameFrom();
		while(n!= null)
		{
			a = s.pos[0] + "," + s.pos[1] + "," + "1";
			sol.add(a);
			s= s.getCameFrom();
			n=s.getCameFrom();
		}
		sol.add("done");
		return sol;
		
	}
}
    

