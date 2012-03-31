package bbs;

public class Tournament {

	long[] winners = new long[6];
	
	public Long getWinners(int round) {
		return winners[round];
	}
	
	public void setWinner(int round, Teams team) {
		winners[round] = winners[round] | (1 << team.ordinal());
	}
	
}
