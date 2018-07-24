package API;

import java.util.Comparator;

public class Comp implements Comparator<MyCh>{

	@Override
	public int compare(MyCh o1, MyCh o2) {
		if(o1.N < o2.N)
			return -1;
		if(o2.N < o2.N)
			return 1;
		return 0;
	}





}
