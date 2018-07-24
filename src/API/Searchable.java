package API;

import java.util.List;

interface Searchable<T>
{
		public State<T> getInitialState();
		public  List<State<T>> getPossibleStates(State<T> s, boolean cheakdup) throws CloneNotSupportedException;
		// instead of State<T> getGoalState()
		boolean isGoalState(State<T> s);

}
