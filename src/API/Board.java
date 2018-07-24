package API;

import java.util.ArrayList;

public class Board implements GeneralBoard{
	private char[][] s;
	private int row;
	private int col;
   



	public char[][] getS() {
		return s;
	}

	public void setS(char[][] s) {
		this.s = s;
	}
	public int getRow()
	{
		return this.row;
	}
	public void setRow(int row)
	{
		this.row = row;
	}
	public int getCol()
	{
		return this.col;
	}
	public void setCol(int col)
	{
		this.col = col;
	}
	public char getTile(int i, int j) {
		return this.s[i][j];
	}
	public void setTile(int i, int j, char ch) {
		this.s[i][j]=ch;
	}
	
	public Board(int i, int j)
	{
		s=new char[i][j];
	}
	public Board(ArrayList<String> str) {
		this.row = str.size();
		this.col = findrow(str);
		this.s = new char[row][col];
		
		for (int i = 0; i < row; i++) {
			char[] temp = str.get(i).toCharArray();
			for (int j = 0; j < col; j++) {
				this.setTile(i, j, temp[j]);

			}
		}
	}
	public Board(Board b1) {
		this.s = b1.getS();
		this.row = b1.getRow();
		this.col = b1.getCol();
		}
	public Board(char[][] c) 
	{
		this.setS(c);
	}


	public int findrow(ArrayList<String> str)
	{
		char[] temp = str.get(0).toCharArray();
		return temp.length;
	}
	public void rotate(int i,int j)
	{
		char s = this.getTile(i, j);
		switch(s)
		{
		case '|':
			this.setTile(i, j, '-');
			break;
		case '-':
			this.setTile(i, j, '|');
			break;
		case '7':
			this.setTile(i, j, 'J');
			break;
		case 'J':
			this.setTile(i, j, 'L');
			break;
		case 'L':
			this.setTile(i, j, 'F');
			break;
		case 'F':
			this.setTile(i, j, '7');
			break;
			
			default:
				break;
		}
	}
	
	

	public Object clone() throws CloneNotSupportedException {

		char[][] newData = new char[this.row][this.col];

		for(int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				newData[i][j] = this.getTile(i, j);

			}
		}
		Board b = new Board(newData);

		return b;

	}
	public int getHash()
	{
		int y = this.toString().hashCode();
		return y;
	}
	public String toString()
	{
		String s = "";
		for (int i = 0; i < this.row; i++) {
			s += String.valueOf(this.s[i]);
		}
		return s;
		
	}
	

	
}
