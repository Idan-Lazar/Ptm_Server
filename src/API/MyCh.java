package API;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MyCh implements ClientHandler {
	ChachManger cm;

	Solver<Board> s;
	int N= 0;
	@Override
	public void handleClient(InputStream inputStream, OutputStream outputStream) throws CloneNotSupportedException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter write = new PrintWriter(new OutputStreamWriter(outputStream));
		PrintWriter writertofile;
		String line;
		Solution sol = new Solution();
		Board b = null;
		cm = new MyChachManger();
		ArrayList<String> str = new ArrayList<String>();
		try {
			while (!((line=reader.readLine()).equals("done")))
				str.add(line);

			b= new Board(str);
			sol= cm.GetSol(b);

			if(sol!=null)
			{
				for(String sr : sol)
				{
					write.println(sr);
					write.flush();

				}
			}
			else
			{
				State<Board> st = new State<Board>(b);
				SearchableAdapterBoard s1 = new SearchableAdapterBoard();
				s1.setState(st);
				s= new MySolver<Board>(new HillClimbingSearcher<Board>(new BoardStateGarder(),10000));
				sol = s.solve(s1);
				writertofile= new PrintWriter(b.getHash() + ".txt");
				for(String sr : sol)
				{
					write.println(sr);
					write.flush();
					writertofile.println(sr);
					writertofile.flush();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
