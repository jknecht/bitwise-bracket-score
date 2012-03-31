package bbs;


public class Bracket {
	private Tournament tournament;
	private long[] roundPicks = new long[6];  	//can't really have more than 6 rounds due to the fact that
									  			//the our data structure is a Long (limited to 64-bits)
	
	public Bracket(Tournament tournament) {
		this.tournament = tournament;
	}
	
	void setPick(int round, Teams pick) {
		roundPicks[round] = roundPicks[round] | (1 << pick.ordinal());
	}
	
	int countCorrectPicks(int round) {
		long correctPicks = roundPicks[round] & tournament.getWinners(round);
		return Long.bitCount(correctPicks);
	}
	
	
}
