package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.Member;
import vo.Music;



public class MemberDao {
	
	DataSource ds;
	
	//데이터소스 주입받음
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	//예인 로그인
	public int insert(Member member) throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement stmt = null;
		final String sqlInsert = "INSERT INTO users (user_no, user_pw, user_email, user_pic, user_nick) VALUES (?, ?, ?, ?, ?)";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
// 여기고쳐음 
			stmt = connection.prepareStatement(sqlInsert);
			stmt.setInt(1, member.getUser_no());
			stmt.setString(2, member.getUser_pw());
			stmt.setString(3, member.getUser_email());
			stmt.setString(4, member.getUser_pic());
			stmt.setString(5, member.getUser_nick());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 다 썼으면 반납하자
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	 
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		Member member = null;
		Statement stmt = null;
		ResultSet rs = null;

		final String sqlSelectOne =  "SELECT user_no, user_pw, user_email, user_pic, user_nick FROM users WHERE user_no=";

		
		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelectOne + no);
			if (rs.next()) {
				member = new Member().setUser_no(rs.getInt("user_no")).setUser_pw(rs.getString("user_pw"))
						.setUser_email(rs.getString("user_email")).setUser_pic(rs.getString("user_pic"))
						.setUser_nick(rs.getString("user_nick"));

			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			// 다 썼으면 반납하자
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return member;
	}
 
	public Member exist(String user_email, String user_pw) throws Exception {
		Connection connection = null;
		Member member = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final String sqlExist = "SELECT user_no, user_email, user_pw, user_nick FROM users WHERE user_email=? AND user_pw =?";

		

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
			
			stmt = connection.prepareStatement(sqlExist);
			stmt.setString(1, user_email);
			stmt.setString(2, user_pw);
			rs = stmt.executeQuery();
			if (rs.next()) {
				member = new Member()
						.setUser_no(rs.getInt("user_no"))
						.setUser_email(rs.getString("user_email"))
						.setUser_pw(rs.getString("user_pw"))
						.setUser_nick(rs.getString("user_nick"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			
			try {
				if(connection != null)
					connection.close();// 다 썼으면 반납하자
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return member;
	}


	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = "SELECT * " + "\r\n" + "FROM users" + "\r\n"
				+ "ORDER BY user_no ASC";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(new Member().setUser_email(rs.getString("user_email")).setUser_nick(rs.getString("user_nick")));
			}

			return members;

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

			/* ds에서 제공하는 Connection객체의 close()의 의미는
			 * 연결을 종료하는 것이 아니라
			 * 객체를 ds내부의 커넥션 풀에 반납한다는 의미이다
			 * */
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//유정연 마이페이지
	   public int update(Member member) throws Exception {
		      Connection connection = null;
		      int result = 0;
		      PreparedStatement stmt = null;
		      final String sqlUpdate = "UPDATE users SET user_email=?,user_pw=?,user_nick=?,user_pic=? " + " WHERE user_no=?";
		      try {
		         // 커넥션풀에서 Connection객체를 빌려온다
		         connection = ds.getConnection();
		         stmt = connection.prepareStatement(sqlUpdate);
		         stmt.setString(1, member.getUser_email());
		         stmt.setString(2, member.getUser_pw());
		         stmt.setString(3, member.getUser_nick());
		         stmt.setString(4, member.getUser_pic());
		         stmt.setInt(5, member.getUser_no());
		         result = stmt.executeUpdate();

		      } catch (Exception e) {
		         throw e;
		      } finally {
		         try {
		            if (stmt != null)
		               stmt.close();
		         } catch (Exception e) {
		         }

		         // 다 썼으면 반납하자
		         try {
		            if (connection != null)
		               connection.close();
		         } catch (Exception e) {
		            e.printStackTrace();
		         }
		      }

		      return result;
		   }
	   public Member selectInfo(int no) throws Exception {
		      Connection connection = null;
		      Member member = null;
		      //Statement stmt = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;

		      final String sqlSelectOne = "SELECT user_no, user_email, user_pw, user_nick, user_pic FROM users WHERE user_no=?";
//		      final String sqlSelectOne = "SELECT user_no,user_email,user_pw,user_nick FROM users" + " WHERE user_no=2";

		      try {
		         // 커넥션풀에서 Connection객체를 빌려온다
		         connection = ds.getConnection();
		         //stmt = connection.createStatement();
		         stmt = connection.prepareStatement(sqlSelectOne);
		         stmt.setInt(1, no);
		         rs = stmt.executeQuery();
		         //rs = stmt.executeQuery(sqlSelectOne + no);
		         //rs = stmt.executeQuery(sqlSelectOne);
		         if (rs.next()) {
		            member = new Member().setUser_no(rs.getInt("user_no"))
		                  .setUser_email(rs.getString("user_email"))
		                  .setUser_pw(rs.getString("user_pw"))
		                  .setUser_nick(rs.getString("user_nick"))
		                  .setUser_pic(rs.getString("user_pic"));
		         } else {
		            throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
		         }

		      } catch (Exception e) {
		         throw e;
		      } finally {
		         try {
		            if (rs != null)
		               rs.close();
		         } catch (Exception e) {
		         }
		         try {
		            if (stmt != null)
		               stmt.close();
		         } catch (Exception e) {
		         }

		         // 다 썼으면 반납하자
		         try {
		            if (connection != null)
		               connection.close();
		         } catch (Exception e) {
		            e.printStackTrace();
		         }
		      }

		      return member;
		   }

	
	public List<Music> selectPlayList(int no) throws Exception {
		Connection connection = null;
		// Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 세션을 가져온다. (가져올 세션이 없다면 생성한다.)
		// HttpSession session = request.getSession(true);
		// Member member = (Member) session.getAttribute("member");

		List<Music> playList = new ArrayList<Music>();

		// if (member != null) {
		/*
		 * final String sqlSelect =
		 * "SELECT p.playlist_name AS playlist_name, m.genre AS genre, m.title AS title, m.cre_date AS cre_date, m.artist AS artist"
		 * + "\r\n" +
		 * " FROM playlist p join users u on p.user_no = u.user_no join musics m on p.music_id=m.music_id"
		 * + "\r\n" + " WHERE p.user_no=?" + "\r\n" + "ORDER BY mno ASC";
		 */
		/*final String sqlSelect = "SELECT p.playlist_name, m.music_pic, m.genre, m.title, m.cre_date, m.artist, p.music_id"
				+ "\r\n"
				+ " FROM playlist p join users u on p.user_no = u.user_no join musics m on p.music_id=m.music_id"
				+ "\r\n" + " WHERE p.user_no=?" + "\r\n" + " ORDER BY playlist_name ASC";*/

		final String sqlSelect = "SELECT p.playlist_name, m.music_pic, m.genre, m.title, m.cre_date, m.artist, p.music_id "
				+ "\r\n"+ " FROM playlist p join musics m on p.music_id = m.music_id and p.user_no = m.user_no "
				+ "\r\n" + " join users u on p.user_no = u.user_no" + "\r\n"  + " WHERE p.user_no=?" + "\r\n" + " ORDER BY playlist_name ASC";
		
		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			/*
			 * connection = ds.getConnection(); stmt = connection.createStatement(); rs =
			 * stmt.executeQuery(sqlSelect);
			 */
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlSelect);
			stmt.setInt(1, no); // 임시로 유저2 넣어놓음. 로그인 시, user_no 값 받아오기// WHERE p.user_no=? 첫번째 물음표의 값-PreparedStatement이거 사용
			rs = stmt.executeQuery();
			while (rs.next()) {
				playList.add(new Music().setPlaylist_name(rs.getString("playlist_name"))
						.setGenre(rs.getString("genre")).setTitle(rs.getString("title"))
						.setCre_date(rs.getDate("cre_date")).setArtist(rs.getString("artist"))
						.setMusic_pic(rs.getString("music_pic"))
						.setMusic_id(rs.getString("music_id")));
			}
			return playList;
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			try {
				if (connection != null)
					connection.close();// 다 썼으면 반납하자
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
public List<Music> selectMusicList(int no) throws Exception {
      Connection connection = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      List<Music> uploadMusicList = new ArrayList<Music>();
      
      final String sqlSelect = "SELECT genre, title, cre_date, artist, music_id "
            + "\r\n" + " FROM musics" + "\r\n" + " WHERE user_no=?" + "\r\n" + " ORDER BY cre_date DESC";

      try {
         connection = ds.getConnection();
         stmt = connection.prepareStatement(sqlSelect);
         stmt.setInt(1, no); // 임시로 유저2 넣어놓음. 로그인 시, user_no 값 받아오기// WHERE p.user_no=? 첫번째 물음표의 값-PreparedStatement이거 사용
         rs = stmt.executeQuery();
         while (rs.next()) {
            uploadMusicList.add(new Music().setGenre(rs.getString("genre"))
                  .setTitle(rs.getString("title"))
                  .setCre_date(rs.getDate("cre_date"))
                  .setArtist(rs.getString("artist"))
                  .setMusic_id(rs.getString("music_id")));
         }
      } catch (Exception e) {
         throw e;
      } finally {
         try {
            if (rs != null)
               rs.close();
         } catch (Exception e) {}
         try {
            if (stmt != null)
               stmt.close();
         } catch (Exception e) {}
         try {
            if (connection != null)
               connection.close();// 다 썼으면 반납하자
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return uploadMusicList;
   }
	public int delete( String music_id ) throws Exception {
		Connection connection = null;
		//String result = "";
		int result = 0;
		Statement stmt = null;
		final String sqlDelete = "DELETE FROM playlist WHERE music_id='" + music_id + "'";
		System.out.println(music_id); // 뮤직 아이디 값가져오기
		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			result = stmt.executeUpdate(sqlDelete);

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 다 썼으면 반납하자
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	public List<String> selectPlayListName(int no) throws Exception {
	      Connection connection = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      final String sqlExist = "SELECT distinct playlist_name FROM playlist " + " WHERE user_no =?" + "\r\n" + " order by playlist_name asc";
	      List<String> list = new ArrayList<String>();
	      try {
	         // 커넥션풀에서 Connection객체를 빌려온다
	         connection = ds.getConnection();
	         stmt = connection.prepareStatement(sqlExist);
	         stmt.setInt(1, no);
	         rs = stmt.executeQuery();
	         while (rs.next()) {
	            list.add(rs.getString(1));
	            System.out.println(rs.getString(1));
	         }
	         
	      } catch (Exception e) {
	         throw e;

	      } finally {
	         try {
	            if (rs != null)
	               rs.close();
	         } catch (Exception e) {
	         }
	         try {
	            if (stmt != null)
	               stmt.close();
	         } catch (Exception e) {
	         }

	         try {
	            if (connection != null)
	               connection.close();// 다 썼으면 반납하자
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }

	      return list;
	   }
}
