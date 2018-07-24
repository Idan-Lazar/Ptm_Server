package API;

public class BoardStateGarder implements StateGrader<Board> {

	private int grade = 0;
	@Override
	public int grade(State<Board> s) {
		int count =0;
		for (int i = 0; i < s.getState().getRow(); i++) {
			for (int j = 0; j < s.getState().getCol(); j++) {
				count += help(s,i,j);
			}
		}
		this.setGrade(count);
		return this.grade;
	}
	public int help(State<Board> s , int i , int j)
	{
		int count =0;
		if(s.getState().getTile(i, j) =='s' || s.getState().getTile(i, j) =='g')
			return 0;
		if(ifup(i,j)== true)
		{
			if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i-1, j), 'u') &&  (s.getState().getTile(i-1, j) !='s')&&  (s.getState().getTile(i-1, j) !='g'))
			{
				count++;
			}
		}
		if(ifdown(s,i,j)== true)
		{
			if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i+1, j), 'd') &&  (s.getState().getTile(i+1, j) !='s') &&  (s.getState().getTile(i+1, j) !='g'))
			{
				 count++;
			}
		}
		if(ifright(s,i,j)== true)
		{
			if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i, j+1), 'r') &&  (s.getState().getTile(i, j+1) !='s')&&(s.getState().getTile(i, j+1) !='g'))
			{
				 count++;
			}
		}
		if(ifleft(i,j)== true)
		{
			if(isConnected(s.getState().getTile(i, j), s.getState().getTile(i, j-1), 'l') && (s.getState().getTile(i, j-1) !='s')&&(s.getState().getTile(i, j-1) !='g'))
			{
				 count++;
			}
		}
		return count;
		
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

}
