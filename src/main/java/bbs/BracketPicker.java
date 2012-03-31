package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class BracketPicker {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
	    conn.createStatement().execute("drop table if exists bracket");
	    conn.createStatement().execute("create table bracket (bracket_id integer primary key autoincrement, name, round1 bigint, round2 bigint, round3 bigint, round4 bigint, round5 bigint, round6 bigint, score1 int, score2, int, score3 int, score4 int, score5 int, score6 int, total int)");

    	Random random = new Random(System.currentTimeMillis());

    	
	    int numBrackets = 100000;
	    Tournament tournament = new Tournament();
	    conn.setAutoCommit(false);
    	PreparedStatement ps =conn.prepareStatement("insert into bracket (name, round1, round2, round3, round4, round5, round6, score1, score2, score3, score4, score5, score6, total) values (?, ?, ?, ?, ?, ?, ?, 0, 0, 0, 0, 0, 0, 0)");
	    for (int i = 0; i < numBrackets; i++) {
	    	if (i % 5000 == 0) {
	    		System.out.println("creating bracket " + i);
	    	}
	    	
	    	Teams[][] rounds = new Teams[7][];
	    	rounds[0] = Teams.values();
	    	rounds[1] = new Teams[32];
	    	rounds[2] = new Teams[16];
	    	rounds[3] = new Teams[8];
	    	rounds[4] = new Teams[4];
	    	rounds[5] = new Teams[2];
	    	rounds[6] = new Teams[1];
	    	
	    	
	    	for (int r = 1; r < 7; r++) {
	    		for (int t = 0; t < rounds[r].length; t++) {
	    			rounds[r][t] = rounds[r -1][random.nextInt(2) + (t * 2)];
	    		}
	    	}
	    	
	    	Bracket b = new Bracket(tournament);
	    	for (int r = 1; r < 7; r++) {
	    		for (int t = 0; t < rounds[r].length; t++) {
	    			b.setPick(r - 1, rounds[r][t]);
	    		}
	    	}
	    	
	    	ps.setString(1, "Bracket " + i);
	    	ps.setLong(2, b.getPickBits(0));
	    	ps.setLong(3, b.getPickBits(1));
	    	ps.setLong(4, b.getPickBits(2));
	    	ps.setLong(5, b.getPickBits(3));
	    	ps.setLong(6, b.getPickBits(4));
	    	ps.setLong(7, b.getPickBits(5));
	    	ps.execute();
	    }
	    conn.commit();
	    ps.close();
	    conn.close();
	    System.out.println("Done");
	}
}
