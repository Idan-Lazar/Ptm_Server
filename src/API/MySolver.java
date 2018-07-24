package API;


@SuppressWarnings("hiding")
public class MySolver<Board> implements Solver<Board> {

    private  Searcher<Board> searcher;

    MySolver(Searcher<Board> searcher) {
        this.setSearcher(searcher);
    }
    
    

	public Searcher<Board> getSearcher() {
		return searcher;
	}


	public void setSearcher(Searcher<Board> searcher) {
		this.searcher = searcher;
	}


	public Solution solve(Searchable<Board> s) throws CloneNotSupportedException {
		Solution a = searcher.search(s);
		return a;
	}
}
