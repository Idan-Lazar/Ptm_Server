package API;


interface Searcher<T> {
    public Solution search(Searchable<T> searchable) throws CloneNotSupportedException;
	public int getNumberOfNodesEvaluated();

}
