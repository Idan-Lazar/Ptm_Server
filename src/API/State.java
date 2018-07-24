package API;

class State<T> {
	private T state;
    private State<T> cameFrom;
    private int cost;
    private boolean visited = false;
    int pos[] = new int[2];
    // add setters and getters, and equals()
    
	
	public T getState() {
		return state;
	}
	public void setState(T state) {
		this.state = state;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	public State(T a)
	{
		this.state = a;
	}

	
	public boolean getVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int[] getPos()
	{
		return this.pos;
	}
	
	public void setPos(int[] pos)
	{
	this.pos = pos;
	}
	

}