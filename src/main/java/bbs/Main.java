package bbs;

public class Main {
	public static void main(String[] args) {
		Tournament t = new Tournament();
		t.setWinner(0, Teams.Kentucky);
		t.setWinner(0, Teams.Iowa_St);
		t.setWinner(0, Teams.VCU);
		t.setWinner(0, Teams.Indiana);
		t.setWinner(0, Teams.Colorado);
		t.setWinner(0, Teams.Baylor);
		t.setWinner(0, Teams.Xavier);
		t.setWinner(0, Teams.Lehigh);

		
		Bracket b = new Bracket(t);
		b.setPickBits(0, -1161863443);

		for(Teams team : t.getWinningTeams(0)) {
			System.out.println(team);
		}

		System.out.println("----");
		for(Teams team : b.getPicks(0)) {
			System.out.println(team);
		}

		System.out.println("----");
		for(Teams team : b.getCorrectPicks(0)) {
			System.out.println(team);
		}
		
		System.out.println("----");
		System.out.println(b.countCorrectPicks(0));
	}
}
