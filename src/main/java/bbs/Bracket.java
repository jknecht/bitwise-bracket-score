package bbs;

import java.util.ArrayList;
import java.util.List;


public class Bracket {
	private Tournament tournament;
	private long[] roundPicks = new long[6];  	//can't really have more than 6 rounds due to the fact that
									  			//the our data structure is a Long (limited to 64-bits)
	
	public Bracket(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public void setPick(int round, Teams pick) {
		roundPicks[round] = roundPicks[round] | (1L << pick.ordinal());
	}
	
	public int countCorrectPicks(int round) {
		long correctPicks = roundPicks[round] & tournament.getWinners(round);
		return Long.bitCount(correctPicks);
	}
	
	public long getPickBits(int round) {
		return roundPicks[round];
	}
	
	public void setPickBits(int round, long picks) {
		roundPicks[round] = picks;
	}
	
	public List<Teams> getPicks(int round) {
		ArrayList<Teams> teams = new ArrayList<Teams>();
		for (int i = 0; i < Teams.values().length; i++) {
			if ((roundPicks[round] & (1L << i)) == (1L << i)) {
				teams.add(Teams.values()[i]);
			}
		}
		return teams;
	}

	public List<Teams> getCorrectPicks(int round) {
		ArrayList<Teams> teams = new ArrayList<Teams>();
		for (int i = 0; i < Teams.values().length; i++) {
			if ((roundPicks[round] & tournament.getWinners(round) & (1L << i)) == (1L << i)) {
				teams.add(Teams.values()[i]);
			}
		}
		return teams;
	}

}
