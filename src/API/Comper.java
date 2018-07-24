package API;

import java.util.Comparator;

public class Comper<T>  implements Comparator<State<T>> {

	public int compare(State<T> n, State<T> st) {
		int x =st.getCost() - n.getCost();
		return x;
	}

}
