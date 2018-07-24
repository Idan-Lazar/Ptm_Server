package API;


public interface Solver<T> {
	
	public Solution solve(Searchable<T> s) throws CloneNotSupportedException ;
	
}
