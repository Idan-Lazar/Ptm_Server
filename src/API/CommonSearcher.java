package API;

import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;

	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes = 0;
	}

	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}
	
	@Override
	public Solution search(Searchable<T> searchable) throws CloneNotSupportedException
    {
		return null;
		
    }
	@Override 
	public int getNumberOfNodesEvaluated() 
	{ 
		return evaluatedNodes; 
	} 
	public void addToOpenList(State<T> s)
	{
		openList.add(s);
	}
	public boolean openListContains(State<T> s)
	{
		return (openList.contains(s));
	}
	


}
