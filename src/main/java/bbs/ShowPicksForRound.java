package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShowPicksForRound {
	
	/**
	 * arg[0] is the bracket id
	 * arg[1] is the round id (0-based, valid values are 0-6)
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		long bracketId = Long.parseLong(args[0]);
		int round = Integer.parseInt(args[1]);
		
		Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");

    	Tournament tournament = new Tournament();
    	List<Teams> winners = tournament.getWinningTeams(round);
    	
	    PreparedStatement psQuery = conn.prepareStatement("select * from bracket where bracket_id = ?");
	    psQuery.setLong(1, bracketId);
	    ResultSet rs = psQuery.executeQuery();
	    if (rs.next()) {
	    	Bracket b = new Bracket(tournament);
	    	b.setPickBits(0, rs.getLong("round1"));
	    	b.setPickBits(1, rs.getLong("round2"));
	    	b.setPickBits(2, rs.getLong("round3"));
	    	b.setPickBits(3, rs.getLong("round4"));
	    	b.setPickBits(4, rs.getLong("round5"));
	    	b.setPickBits(5, rs.getLong("round6"));
	    	
	    	List<Teams> picks = b.getPicks(round);
	    	for(Teams team : picks) {
	    		System.out.print(team);
	    		if (winners.contains(team)) {
	    			System.out.print("*");
	    		}
	    		System.out.print("\n");
	    	}
	    	
	    } else {
	    	System.out.println("No record found for bracket id " + bracketId);
	    }
	    rs.close();
	    conn.close();
	}


}
