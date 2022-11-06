package dynamic_beat3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBConnection {
	private Connection connection;
	private ResultSet rs;
	private Statement st;

	private String music;

	private DynamicBeat beat;

	protected int rank_score[] = new int[4];
	protected String rank_id[] = new String[4];

	private String url = "jdbc:mysql://localhost:3306/database1?serverTimezone=UTC";
	private String user = "root";
	private String password = "1234";
	private String driverName = "com.mysql.cj.jdbc.Driver";

	DBConnection(DynamicBeat d) {
		beat = d;
	}

	public void connect() {

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			st = connection.createStatement();
			System.out.println("[Connection suceesful!]");

		} catch (ClassNotFoundException e) {
			System.out.println("[로드 오류]\n" + e.getStackTrace());
		} catch (SQLException e) {
			System.out.println("[연결 오류]\n" + e.getStackTrace());
		}
	}

	public boolean Enrollment(String myId, String myPw) { // 회원가입
		try {
			String SQL1 = "INSERT INTO table1(Id, Password, howl, inu, silver) VALUES('" + myId + "','" + myPw
					+ "',0,0,0);";
			PreparedStatement pstmt = connection.prepareStatement(SQL1);
			int re = pstmt.executeUpdate();
			if (re == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean isAdmin(String Id) {
		try {
			String SQL1 = "SELECT * FROM table1 WHERE Id = '" + Id + "';".toString();
			rs = st.executeQuery(SQL1);
			if (rs.next()) {
				if (rs.getString("Id").equals(Id)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류 : " + e.getMessage());
		}
		return false;
	}

	public boolean isAdmin(String Id, String Password) {
		try {
			String SQL1 = "SELECT * FROM table1 WHERE Id='" + Id + "';".toString();
			rs = st.executeQuery(SQL1);
			if (rs.next()) {
				if (rs.getString("Id").equals(Id)) {
					if (rs.getString("Password").equals(Password))
						beat.user.setID(Id);
						return true;
				}
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류 : " + e.getMessage());
		}
		return false;
	}

	public boolean userScore(String Id, String MusicName, int Score) {

		if (MusicName == "하울의 움직이는 성")
			music = "howl";
		else if (MusicName == "시대를 초월한 마음")
			music = "inu";
		else if (MusicName == "Silver Scrapes")
			music = "silver";

		try {
			String SQL2 = "SELECT * FROM table1 WHERE Id='" + Id + "';";
			rs = st.executeQuery(SQL2);
			if (rs.next()) {
				if (Score > rs.getInt(music)) {
					String SQL1 = "UPDATE table1 SET " + music + " = " + Score + " where Id = '" + Id + "';";

					PreparedStatement pstmt = null;
					pstmt = connection.prepareStatement(SQL1);
					int re = pstmt.executeUpdate();
					if (re == 1) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean userRank(String user_Id, String MusicName,int myScore) {
		String id;
		int userScore;
		if (MusicName == "하울의 움직이는 성")
			music = "howl";
		else if (MusicName == "시대를 초월한 마음")
			music = "inu";
		else if (MusicName == "Silver Scrapes")
			music = "silver";
		try {
			String SQL1 = "SELECT MAX(" + music + ") FROM table1;";
			rs = st.executeQuery(SQL1);
			if (rs.next()) {
				userScore = rs.getInt("MAX(" + music + ")");
				rank_score[0] = userScore;
			}
			String SQL2 = "SELECT Id FROM table1 WHERE " + music + " LIKE " + rank_score[0] + ";";
			rs = st.executeQuery(SQL2);
			if (rs.next()) {
				id = rs.getString("Id");
				rank_id[0] = id;
			}

			String SQL3 = "SELECT MAX(" + music + ") FROM table1 where " + music + " not in(" + rank_score[0] + ");";
			rs = st.executeQuery(SQL3);
			if (rs.next()) {
				userScore = rs.getInt("MAX(" + music + ")");
				rank_score[1] = userScore;
			}
			String SQL4 = "SELECT Id FROM table1 WHERE " + music + " LIKE " + rank_score[1] + ";";
			rs = st.executeQuery(SQL4);
			if (rs.next()) {
				id = rs.getString("Id");
				rank_id[1] = id;
			}
			String SQL5 = "SELECT MAX(" + music + ") FROM table1 where " + music + " not in(" + rank_score[0] + ","
					+ rank_score[1] + ");";
			rs = st.executeQuery(SQL5);
			if (rs.next()) {
				userScore = rs.getInt("MAX(" + music + ")");
				rank_score[2] = userScore;
			}
			String SQL6 = "SELECT Id FROM table1 WHERE " + music + " LIKE " + rank_score[2] + ";";
			rs = st.executeQuery(SQL6);
			if (rs.next()) {
				id = rs.getString("Id");
				rank_id[2] = id;
			}
			String SQL7 = "SELECT " + music + " FROM table1 WHERE Id= '" + user_Id + "';";
			rs = st.executeQuery(SQL7);
			if (rs.next()) {
				userScore = rs.getInt(music);
				rank_score[3] = userScore;
			}
			rank_id[3] = user_Id;
			beat.user.setrank(music, rank_score, rank_id);
			return true;
		} catch (Exception e) {
			String s = e.getMessage();
			System.out.println(s);
		}
		return false;
	}
}