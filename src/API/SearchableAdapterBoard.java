package API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchableAdapterBoard  implements Searchable<Board>{

	public boolean a = false;
	public State<Board> s;
	HashMap<Integer,State<Board>> map= new HashMap<Integer,State<Board>>();
	@Override
	public State<Board> getInitialState() {
		int key = s.getState().getHash();
		map.put(key, s);
		return s;
	}
	public void setState(State<Board> a)
	{
		this.s = a;
	}
	@Override
	public List<State<Board>> getPossibleStates(State<Board> s, boolean cheakdup) throws CloneNotSupportedException {
		
		Board b = s.getState();
		int row = b.getRow();
		int col = b.getCol();
		Board b1 = new Board(b);
		int pos[] = new int[2];		
		int key;
        List<State<Board>> b2 = new ArrayList<State<Board>>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				b1 = (Board) b.clone();
				b1.rotate(i, j);
				pos = pos.clone();
				if((b.getTile(i, j) != 's') && (b.getTile(i, j) != 'g'))
				{
					pos[0] = i;
					pos[1] = j;
					b1.setCol(col);
					b1.setRow(row);
					State<Board> s1 = new State(b1);
					key = b1.getHash();
					if(cheakdup)
					{ 
						if(!duplication(key, s1))
					     {
						s1.setCameFrom(s);
						s1.setCost(s.getCost()+1);
						s1.setPos(pos);
						b2.add(s1);
					     }
					}
					else
					{
					s1.setCameFrom(s);
					s1.setCost(0);
					s1.setPos(pos);
					b2.add(s1);
					}
					
					
					
				}
				
			}
		}
		return b2;
	}
	
	public boolean isConnected(char x, char y , char pos)
	{
		switch(pos)
		{
		case'd':
			if(x == 'J' ||x == 'L' || x == '-' || x ==' ') //אריח למעלה
			{
				return false;
			}
			if(y == '-'||y == '7'||y == 'F'|| y ==' ')
				return false;
			return true;
			
		case'u':
			if(y == 'J' ||y == 'L' || y == '-'|| y ==' ')
			{
				return false;
			}
			if(x == '-'||x == '7'||x == 'F'|| x ==' ')
				return false;
			return true;
			
		case'r':	
			if(x == '|' ||x == '7'||x == 'J'|| x ==' ') // אריח שמאלי
				return false;
			if(y == '|' ||y == 'L' || y == 'F'|| y ==' ')
			{
				return false;
			}
		
			return true;
		case'l':
			if(y == '|' ||y == '7' || y == 'J'|| y ==' ')
			{
				return false;
			}
			if(x == '|'||x == 'L'||x == 'F'|| x ==' ')
				return false;
			return true;
			
				default:
					return false;
			
		}
		}
	public boolean ifup(int i , int j)
	{
		if(i == 0)
			return false;
		return true;
	}
	public boolean ifdown(State<Board> s , int i , int j)
	{
		if(s.getState().getRow()-1 == i)
			return false;
		return true;
	}
	public boolean ifright(State<Board> s , int i , int j)
	{
		if(s.getState().getCol()-1 == j)
			return false;
		return true;
	}
	public boolean ifleft(int i , int j)
	{
		if(j == 0)
			return false;
		return true;
	}
	
	public boolean isGoalState(State<Board> s , int i , int j, char camefrom)
		{
		
			if(s.getState().getTile(i, j) == 'g')
			{
				 a= true;
			}
			if(ifup(i,j)== true && camefrom != 'u')
			{
				if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i-1, j), 'u') &&  (s.getState().getTile(i-1, j) !='s'))
				{
					  isGoalState(s, i-1, j , 'd');
				}
			}
			if(ifdown(s,i,j)== true && camefrom != 'd')
			{
				if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i+1, j), 'd') &&  (s.getState().getTile(i+1, j) !='s'))
				{
					 isGoalState(s, i+1, j , 'u');
				}
			}
			if(ifright(s,i,j)== true && camefrom != 'r')
			{
				if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i, j+1), 'r') &&  (s.getState().getTile(i, j+1) !='s'))
				{
					 isGoalState(s, i, j+1 , 'l');
				}
			}
			if(ifleft(i,j)== true && camefrom != 'l')
			{
				if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i, j-1), 'l') && (s.getState().getTile(i, j-1) !='s'))
				{
					 isGoalState(s, i, j-1 , 'r');
				}
			}
			
			return a;

	}
	@Override
	public boolean isGoalState(State<Board> s)
	{
		this.a = false;
		int[] a = new int[2];
		a= this.Finds(s);
		return isGoalState(s,a[0],a[1],'-');
	}
	
	public boolean duplication(int key , State<Board> b)
	{
		if(!map.isEmpty())
		{
			if(map.containsKey(key))
				return true;
			else {
				map.put(key, b);
				return false;
			}
		}
		else
			map.put(key, b);
		
		return false;
	}
	
	public int[] Finds(State<Board> b)
	{
		int[] a = new int[2];
		for (int i = 0; i < s.getState().getRow(); i++) {
			for (int j = 0; j < s.getState().getCol(); j++) {
				if(s.getState().getTile(i, j) =='s')
				{
					a[0]= i;
					a[1]= j;
				}
			}
		}
		return a;
	
	}
	
	

}

