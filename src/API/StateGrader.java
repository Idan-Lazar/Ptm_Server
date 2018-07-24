package API;

public interface StateGrader<T>{
		int grade(State<T> step); // give a grade to a certain state - how close it is to the solution
}