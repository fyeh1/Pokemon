package Model;

public class Tester {

	public static void main(String[]args)
	{
		for(int x = 10;x>0;x--)			
			System.out.println((int)((Math.random()*4+1)/4)*25);

		System.out.println(Integer.MIN_VALUE);
		
		int hitc = hitRandom(1,10);
		System.out.println(hitc);
	}
	
	private static int hitRandom(int lowerBound, int upperBound) {
		int hitc = (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
		return hitc;
	}
	
}
