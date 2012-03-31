package bbs;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

	long[] winners = new long[6];
	
	public Long getWinners(int round) {
		return winners[round];
	}
	
	public void setWinner(int round, Teams team) {
		winners[round] = winners[round] | (1L << team.ordinal());
	}

	public List<Teams> getWinningTeams(int round) {
		ArrayList<Teams> teams = new ArrayList<Teams>();
		for (int i = 0; i < Teams.values().length; i++) {
			if ((winners[round] & (1L << i)) == (1L << i)) {
				teams.add(Teams.values()[i]);
			}

		}
		return teams;
	}

}
