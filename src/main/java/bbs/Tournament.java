package bbs;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

	long[] winners = new long[6];
	
	public Tournament() {
    	Tournament tournament = this;
    	
		//round 1 - South
		tournament.setWinner(0, Teams.Kentucky);
		tournament.setWinner(0, Teams.Iowa_St);
		tournament.setWinner(0, Teams.VCU);
		tournament.setWinner(0, Teams.Indiana);
		tournament.setWinner(0, Teams.Colorado);
		tournament.setWinner(0, Teams.Baylor);
		tournament.setWinner(0, Teams.Xavier);
		tournament.setWinner(0, Teams.Lehigh);

    	//round 1 - West
		tournament.setWinner(0, Teams.Michigan_St);
		tournament.setWinner(0, Teams.St_Louis);
		tournament.setWinner(0, Teams.New_Mexico);
		tournament.setWinner(0, Teams.Louisville);
		tournament.setWinner(0, Teams.Murray_St);
		tournament.setWinner(0, Teams.Marquette);
		tournament.setWinner(0, Teams.Florida);
		tournament.setWinner(0, Teams.Norfolk_St);

    	//round 1 - East
		tournament.setWinner(0, Teams.Syracuse);
		tournament.setWinner(0, Teams.Kansas_St);
		tournament.setWinner(0, Teams.Vanderbilt);
		tournament.setWinner(0, Teams.Wisconsin);
		tournament.setWinner(0, Teams.Cincinnati);
		tournament.setWinner(0, Teams.Florida_St);
		tournament.setWinner(0, Teams.Gonzaga);
		tournament.setWinner(0, Teams.Ohio_St);

    	//round 1 - Midwest
		tournament.setWinner(0, Teams.North_Carolina);
		tournament.setWinner(0, Teams.Creighton);
		tournament.setWinner(0, Teams.So_Florida);
		tournament.setWinner(0, Teams.Ohio);
		tournament.setWinner(0, Teams.NC_St);
		tournament.setWinner(0, Teams.Georgetown);
		tournament.setWinner(0, Teams.Purdue);
		tournament.setWinner(0, Teams.Kansas);

    	//round 2 - South
		tournament.setWinner(1, Teams.Kentucky);
		tournament.setWinner(1, Teams.Indiana);
		tournament.setWinner(1, Teams.Baylor);
		tournament.setWinner(1, Teams.Xavier);

    	//round 2 - West
		tournament.setWinner(1, Teams.Michigan_St);
		tournament.setWinner(1, Teams.Louisville);
		tournament.setWinner(1, Teams.Marquette);
		tournament.setWinner(1, Teams.Florida);

    	//round 2 - East
		tournament.setWinner(1, Teams.Syracuse);
		tournament.setWinner(1, Teams.Wisconsin);
		tournament.setWinner(1, Teams.Cincinnati);
		tournament.setWinner(1, Teams.Ohio_St);

    	//round 2 - Midwest
		tournament.setWinner(1, Teams.North_Carolina);
		tournament.setWinner(1, Teams.Ohio);
		tournament.setWinner(1, Teams.NC_St);
		tournament.setWinner(1, Teams.Kansas);

    	//round 3 - South
		tournament.setWinner(2, Teams.Kentucky);
		tournament.setWinner(2, Teams.Baylor);

    	//round 3 - West
		tournament.setWinner(2, Teams.Louisville);
		tournament.setWinner(2, Teams.Florida);

    	//round 3 - East
		tournament.setWinner(2, Teams.Syracuse);
		tournament.setWinner(2, Teams.Ohio_St);

    	//round 3 - Midwest
		tournament.setWinner(2, Teams.North_Carolina);
		tournament.setWinner(2, Teams.Kansas);

    	//round 4 - South
		tournament.setWinner(3, Teams.Kentucky);

    	//round 4 - West
		tournament.setWinner(3, Teams.Louisville);

    	//round 4 - East
		tournament.setWinner(3, Teams.Ohio_St);

    	//round 4 - Midwest
		tournament.setWinner(3, Teams.Kansas);
		
	}
	
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
