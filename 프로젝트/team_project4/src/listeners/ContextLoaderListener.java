package listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import context.ApplicationContext;
import controls.MainController;
import controls.MemberAddController;
import controls.MemberListController;
import controls.MemberUpdateController;
import controls.PlayController;
import controls.PlayListController;
import controls.PlayListDeleteController;
import controls.SearchController;
import controls.UploadController;
import controls.ChartController;
import controls.LogInController;
import controls.LogOutController;
import dao.MemberDao;
import dao.MusicDao;

//ServletContext에서 경로를 가져와 ApplicationContext를 생성한다.
public class ContextLoaderListener implements ServletContextListener{ //ServletContextListener를 구현해야 돌아감
	
	ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		try {
			System.out.println("컨텍스트가 초기화되었습니다.");
			ServletContext sc = sce.getServletContext();
			
			//미정
//			String propertiesPath = sc.getRealPath(
//					sc.getInitParameter("contextConfigLocation"));
//			applicationContext = new ApplicationContext(propertiesPath);
		
			InitialContext intialContext = new InitialContext();
			DataSource ds = (DataSource)intialContext.lookup("java:comp/env/jdbc/dropthebit");
			
			//dao객체생성
			MemberDao memberDao = new MemberDao(); 
			memberDao.setDataSource(ds);
			
			MusicDao musicDao = new MusicDao(); //dao객체생성
			musicDao.setDataSource(ds);
			
			//예인 로그인,로그아웃
			sc.setAttribute("/auth/login.do", 
					new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", 
					new LogOutController());		
			sc.setAttribute("/member/add.do", 
					new MemberAddController().setMemberDao(memberDao));
			
			//어노테이션쓰면 대체가능
			//상우 메인,차트,검색
			sc.setAttribute("/main/main.do",
					new MainController().setMusicDao(musicDao));
			sc.setAttribute("/chart/chart.do",
					new ChartController().setMusicDao(musicDao));
			sc.setAttribute("/search/search.do",
					new SearchController().setMusicDao(musicDao));
			//정연 마이페이지,플레이리스트
			sc.setAttribute("/member/list.do", 
					new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/playList.do", // 서블릿 요청시 주소 
					new PlayListController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", 
					new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/mypage.do", 
					new MemberUpdateController().setMemberDao(memberDao)); //이거 추가람
			sc.setAttribute("/member/delete.do", 
					new PlayListDeleteController().setMemberDao(memberDao));
			
			//지은 업로드,재생페이지
			sc.setAttribute("/music/upload.do", new UploadController().setMusicDao(musicDao));
		    sc.setAttribute("/music/play.do", new PlayController().setMusicDao(musicDao));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			System.out.println("컨텍스트가 종료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
