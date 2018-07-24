package API;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS<T>  extends CommonSearcher<T>{

	 private Searchable<T> searchable;
	 private State<T> F;   

	public BFS(Searchable<T> searchable)
	{
		this.searchable =searchable;
	}



	public void bfs(Searchable<T> searchable) throws CloneNotSupportedException
	{
		Queue<State<T>> q=new LinkedList<State<T>>();
	    q.add(searchable.getInitialState());
	List<State<T>> visited = new ArrayList<State<T>>();
	visited.add(searchable.getInitialState());
	while(!q.isEmpty())
	{
		State<T> n=q.remove();
		State<T> child=null;
		while((child=getUnvisitedChildNode(n,visited))!=null)
		{

			if(!this.searchable.isGoalState(child))
			{
				visited.add(child);
				q.add(child);
			}
			else
			{
				this.F = child;
				break;
			}
			
			
		}
	}
	}
	
	private State<T> getUnvisitedChildNode(State<T> n,List<State<T>> visited) throws CloneNotSupportedException {
		
		List<State<T>> neighbours= searchable.getPossibleStates(n,true);
		State<T> d= null;
		while(neighbours.size()>0)
		{
			d=neighbours.remove(neighbours.size()-1);
			if(!visited.contains(d))
			{
			   return d;
			}
		}

		return null;
	}


	@Override
	public Solution search(Searchable<T> searchable) throws CloneNotSupportedException
	{
		Solution sol = new Solution();
		bfs(searchable);
		State<T> a =this.F;
		sol = backtrace(a);
		return sol;
		
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
