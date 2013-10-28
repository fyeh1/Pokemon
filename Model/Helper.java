package Model;

import java.util.LinkedList;

public class Helper {

	public static int[] separate(String str) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for (int x = 0; x < str.length(); x++) {
			if (str.charAt(x) == ',')
				ll.add(x);
		}
		int[] i = new int[ll.size() + 1];

		int ini = 0;
		for (int x = 0; x < ll.size(); x++) {
			i[x] = Integer.parseInt(str.substring(ini, ll.get(x)));
			ini = ll.get(x);
		}
		i[i.length - 1] = Integer.parseInt(str.substring(ini, str.length()));
		return i;
	}

}
