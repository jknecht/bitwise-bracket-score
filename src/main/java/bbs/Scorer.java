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
