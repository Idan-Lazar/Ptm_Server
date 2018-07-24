package API;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import API.Board;


public class MyChachManger implements ChachManger {



	private BufferedReader reader;



	@SuppressWarnings({ "unused", "resource" })
	public boolean isSolved(int hashMap)
	{
	try
	{
		BufferedReader reader = new BufferedReader(new FileReader(hashMap + ".txt"));
	}
	catch(FileNotFoundException e)
	{
		return false;
	}
		return true;
	}

	@Override
	public Solution GetSol(Board b) throws IOException {
		String line;
		int x = b.getHash();
		Solution sol = new Solution();

		if(isSolved(x)==true)
		{

		   reader = new BufferedReader(new FileReader(x + ".txt"));
		   while (((line=reader.readLine()) != null))
		   {
			 sol.add(line);
		   }
		}
		else
		{
		   return null;
		}
		return sol;
	}

	public String changetostring(Board b)
	{
		String hashMap;
		hashMap = b.getS().toString();

		return hashMap;
	}










}


