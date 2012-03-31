package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Scorer {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");

	    long start = System.currentTimeMillis();
	    
    	Tournament tournament = new Tournament();
    	
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

    	
	    conn.setAutoCommit(false);
	    int updates = 0;
    	PreparedStatement psUpdate = conn.prepareStatement("update bracket set score1=?, score2=?, score3=?, score4=?, score5=?, score6=?, total=? where bracket_id = ?");
	    PreparedStatement psQuery = conn.prepareStatement("select * from bracket");
	    ResultSet rs = psQuery.executeQuery();
	    while (rs.next()) {
	    	Bracket b = new Bracket(tournament);
	    	int bracketId = rs.getInt("bracket_id");
	    	b.setPickBits(0, rs.getLong("round1"));
	    	b.setPickBits(1, rs.getLong("round2"));
	    	b.setPickBits(2, rs.getLong("round3"));
	    	b.setPickBits(3, rs.getLong("round4"));
	    	b.setPickBits(4, rs.getLong("round5"));
	    	b.setPickBits(5, rs.getLong("round6"));
	    	
	    	int score1 = b.countCorrectPicks(0) * 1;
	    	int score2 = b.countCorrectPicks(1) * 2;
	    	int score3 = b.countCorrectPicks(2) * 4;
	    	int score4 = b.countCorrectPicks(3) * 8;
	    	int score5 = b.countCorrectPicks(4) * 16;
	    	int score6 = b.countCorrectPicks(5) * 32;
	    	int total = score1 + score2 + score3 + score4 + score5 + score6;
	    	
	    	psUpdate.setInt(1, score1);
	    	psUpdate.setInt(2, score2);
	    	psUpdate.setInt(3, score3);
	    	psUpdate.setInt(4, score4);
	    	psUpdate.setInt(5, score5);
	    	psUpdate.setInt(6, score6);
	    	psUpdate.setInt(7, total);
	    	psUpdate.setInt(8, bracketId);
	    	psUpdate.execute();
	    	updates++;
	    	if (updates % 25000 == 0) {
	    	    long end = System.currentTimeMillis();
	    	    System.out.println("Records udpated: " + updates + " in " + (end - start) + " ms.");
	    	}
	    	
	    }
	    
	    conn.commit();
	    conn.close();
	    long end = System.currentTimeMillis();
	    System.out.println("Records committed: " + updates + " in " + (end - start) + " ms.");
	}

}
