package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.Music;
import vo.Playlist;
import vo.Member;

//7. 랜덤음악 배치(메인화면)-임상우
//8. 순위차트(메인화면)-임상우
//9. 검색기능(헤더-메인화면)-임상우

public class MusicDao {

	DataSource ds = null;
	
	//데이터소스 주입받음
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	//임상우
	public ArrayList<Music> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sqlSelect = "SELECT music_pic,music_id FROM MUSICS ORDER BY RAND() LIMIT 10"; 
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlSelect);
			
			ArrayList<Music> musics =  new ArrayList<Music>();
			while(rs.next()) {
				musics.add(new Music().setMusic_pic(rs.getString("music_pic"))
									  .setMusic_id(rs.getString("music_id"))
							);
			}
			
			return musics;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(conn != null)
					conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Music> selectChart(Music chartInput) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//코드 개선전
		//변하는 부분만 변수로 만들고 변수의 값만 바꿔주면 코드가 줄어든다.
//		if(chartInput == null ) {
//			//종합 순위
//			sqlSelect = "SELECT RANK() OVER(ORDER BY count DESC) AS ranking,title,artist,genre,count\r\n" + 
//								"FROM musics\r\n" + 
//								"ORDER BY count DESC";
//		}else if(chartInput.getGenre() != null){
//			//장르별 순위
//			sqlSelect = "SELECT RANK() OVER(ORDER BY count DESC) AS ranking,title,artist,genre,count\r\n" + 
//								"FROM musics\r\n" + 
//								"WHERE genre = ?\r\n" + 
//								"ORDER BY count DESC";
//			selectInput = chartInput.getGenre();
//		}else if(chartInput.getArtist() != null ) {
//			//가수별 곡순위
//			sqlSelect = "SELECT RANK() OVER(ORDER BY count DESC) AS ranking,title,artist,genre,count\r\n" + 
//								"FROM musics\r\n" + 
//								"WHERE artist = ?\r\n" + 
//								"ORDER BY count DESC";
//			selectInput = chartInput.getArtist();
//		}
//		
//		else if(chartInput.getCre_date() != null ) {
//			//최신음악
//			sqlSelect = "SELECT RANK() OVER(ORDER BY count DESC) AS ranking,title,artist,genre,count,cre_date "
//							+ "FROM musics " 
//							+ "WHERE "+ Target
//							+ "ORDER BY count DESC";
//			selectInput = chartInput.getCre_date();
//		}
		
		
		//코드 개선 후
		String Target = null;
		//컬럼명은 ?를 이용해 넣을 수 없어 따로 넣어준다.
		if(chartInput == null) {
			//종합 순위
			Target = "1 = ? ";
		}else if(chartInput.getGenre() != null) {
			//장르별 순위
			Target = "genre = ? ";
		}else if(chartInput.getArtist() != null){
			//가수별 곡순위
			Target = "artist LIKE ? "; 
		}else if(chartInput.getRanking() == 1) {
			//최신순위
			Target = "cre_date >= DATE_SUB(NOW(), INTERVAL 14 DAY) ";
		}
		
		String sqlSelect = "SELECT RANK() OVER(ORDER BY count DESC) AS ranking,music_id,music_pic,title,artist,genre,count,cre_date "
				+ "FROM musics " 
				+ "WHERE "+ Target
				+ "ORDER BY count DESC";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlSelect);
			
			if(chartInput == null ) {
				//종합 순위
				pstmt.setInt(1, 1);
			}else if(chartInput.getGenre() != null){
				//장르별 순위
				pstmt.setString(1, chartInput.getGenre());
			}else if(chartInput.getArtist() != null) {
				//가수별 곡순위
				String noSpaceArtist = chartInput.getArtist().replaceAll(" ","");//띄어쓰기 무시
				pstmt.setString(1, "%"+noSpaceArtist+"%"); //포함된 모든 글자 가져오기
			}else if(chartInput.getRanking() == 1) {
				
			}
			
			rs = pstmt.executeQuery();
			
			ArrayList<Music> chart = new ArrayList<Music>();
			while(rs.next()) {
				chart.add(new Music().setRanking(rs.getInt(1))
									.setMusic_id(rs.getString("music_id"))
									.setMusic_pic(rs.getString("music_pic"))
									.setTitle(rs.getString("title"))
									.setArtist(rs.getString("artist"))
									.setGenre(rs.getString("genre"))
									.setCount(rs.getInt("count"))
									.setCre_date(rs.getDate("cre_date"))
						  );
			}
			
			return chart;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(conn != null)
					conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Music> search(String noBarSpace, String category) throws SQLException {
		//가수명검색, 제목검색, 장르검색
		//앞글자만 쳐도 결과가 나오게?
		//대소문자? 띄어쓰기?
		Connection conn =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		String target = null;
		String selectSql = null;
		//제목별, 가수별, 장르별 검색
		if(category.equals("title")) {
			//title
			target = "title";
		}else if(category.equals("artist")) {
			//artist
			target = "artist";
		}else if(category.equals("genre")) {
			//genre
			target = "genre";
		}
		
		//LIKE연산자와 와일드카드를 이용하여 글자가 완성되지않아도 검색되도록함
		if(target != null){
			//제목별, 가수별, 장르별 검색
			selectSql = "SELECT music_id,music_pic,genre,title,cre_date,artist FROM musics "
								+ "WHERE REPLACE("+ target +", ' ', '') LIKE ?";
		}else {
			//통합검색
			selectSql = "SELECT music_id,music_pic,genre, title, cre_date, artist " + 
								"FROM musics " + 
								"WHERE REPLACE(genre,' ', '') LIKE ? " + 
								"OR REPLACE(title,' ', '') LIKE ? " + 
								"OR REPLACE(artist,' ', '') LIKE ?";
		}
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, "%"+noBarSpace+"%");
			if(target == null) {
				pstmt.setString(2, "%"+noBarSpace+"%");
				pstmt.setString(3, "%"+noBarSpace+"%");
			}
			rs = pstmt.executeQuery();
			
			ArrayList<Music> search = new ArrayList<Music>();
			while(rs.next()) {
				search.add(new Music().setGenre(rs.getString("genre"))
						.setMusic_id(rs.getString("music_id"))
						.setMusic_pic(rs.getString("music_pic"))
						.setTitle(rs.getString("title"))
						.setCre_date(rs.getDate("cre_date"))
						.setArtist(rs.getString("artist"))
						);
			}
			
			return search;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(conn != null)
					conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	//홍지은
	public int upload(Music music) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int result = 0;
		String sqlInsert = "INSERT INTO musics(music_id,music_pic,user_no,genre,title,cre_date,artist,count)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, NOW(), ?, 1)";
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlInsert);
			stmt.setString(1, music.getMusic_id()); // 음악파일명
			stmt.setString(2, music.getMusic_pic()); // 사진파일
			stmt.setInt(3, music.getUser_no()); //유저일련번호
			stmt.setString(4, music.getGenre()); // 장르
			stmt.setString(5, music.getTitle()); // 제목
			stmt.setString(6, music.getArtist()); // 아티스트
			result = stmt.executeUpdate();

			// 몇개 행에 적용됐는지

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			;
		}
		return result;
	}

	
	public Music play(String id) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Music music = null;

		final String sqlSelect = "SELECT m.music_id, m.music_pic, m.genre, m.title, m.artist, u.user_no, u.user_nick, m.cre_date, m.count"
				+ "\r\n" + "FROM musics m, users u" + "\r\n" + "WHERE m.music_id ='" + id + "'";
		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				music = new Music().setMusic_id(rs.getString(1)).setMusic_pic(rs.getString(2)).setGenre(rs.getString(3))
						.setTitle(rs.getString(4)).setArtist(rs.getString(5)).setUser_no(rs.getInt(6))
						.setUser_nick(rs.getString(7)).setCre_date(rs.getDate(8)).setCount(rs.getInt(9));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return music;
	}

	
	public int addToList(Playlist playlist) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int result = 0;
		String sqlInsert = "INSERT INTO playlist(playlist_name,user_no,music_id)\r\n" + "VALUES(?, ?, ?)";
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlInsert);

			stmt.setString(1, playlist.getPlaylistName());
			stmt.setInt(2, playlist.getUser_no()); // 유저일련번호
			stmt.setString(3, playlist.getMusic_no()); // 음악파일명

			result = stmt.executeUpdate();

			// 몇개 행에 적용됐는지

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	public int delete() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void addCount(Music music) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		String sqlUpdate = "update musics set count = count + 1 where music_id =?";
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlUpdate);
			stmt.setString(1, music.getMusic_id());
			System.out.println(music.getMusic_id());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	


}
