package API;

//Java program to print DFS traversal from a given given graph
import java.util.*;


public class DepthFirstTraversal<T> extends CommonSearcher<T>
{

 // Array  of lists for Adjacency List Representation
 private Searchable<T> searchable;
 private State<T> F;   // No. of vertices


 DepthFirstTraversal(Searchable<T> searchable) throws CloneNotSupportedException
 {
     this.searchable = searchable;
 }
    

 
 public void dfs(Searchable<T> searchable) throws CloneNotSupportedException
 {

    Stack<State<T>> s=new  Stack<State<T>>();
	s.push(searchable.getInitialState());
	List<State<T>> visited = new ArrayList<State<T>>();
	visited.add(searchable.getInitialState());
	
	while(!s.isEmpty())
	{
		State<T> n=(State<T>)s.peek();
		State<T> child=getUnvisitedChildNode(n, visited);
		if(child!=null)
		{
			if(!this.searchable.isGoalState(child))
			{
				visited.add(child);
				s.push(child);
			}
			else
			{
				this.F= child;
				break;
			}
		}
		else
		{
			s.pop();
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
 

 
	public Solution search(Searchable<T> searchable) throws CloneNotSupportedException
	{
		Solution sol = new Solution();
		dfs(searchable);
		State<T> a = this.F;
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

