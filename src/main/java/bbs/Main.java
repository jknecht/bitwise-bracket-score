package bbs;

public class Main {
	public static void main(String[] args) {
		Tournament t = new Tournament();
		
		int numBrackets = 1000000;
		System.out.println("Setting up " + numBrackets + " brackets");
		Bracket[] brackets = new Bracket[numBrackets];
		for (int i = 0; i < brackets.length; i++) {
			Bracket b1 = new Bracket(t);
			//pick random winners
			for (int j = 0; j < 8; j++) {
				b1.setPick(0, Teams.values()[((int) (Math.random() * 2)) * j]);				
			}
			brackets[i] = b1;
		}
		
		
		System.out.println("Setting tournament winners");
		t.setWinner(0, Teams.Kentucky);
		t.setWinner(0, Teams.Iowa_St);
		t.setWinner(0, Teams.VCU);
		t.setWinner(0, Teams.Indiana);
		t.setWinner(0, Teams.Colorado);
		t.setWinner(0, Teams.Baylor);
		t.setWinner(0, Teams.Xavier);
		t.setWinner(0, Teams.Lehigh);
		
		System.out.println("Scoring " + numBrackets + " brackets");
		int scores[] = new int[numBrackets];
		long start = System.currentTimeMillis();
		for (int i = 0; i < brackets.length; i++) {
			scores[i] = brackets[i].countCorrectPicks(0);
		}
		long end = System.currentTimeMillis();
		System.out.println("Done in " + (end - start) + " ms. Random score - " + scores[(int) (Math.random() * numBrackets)]);
		
	}
}
