package serlvet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controls.Controller;
import vo.Member;
import vo.Music;

//7. 랜덤음악 배치(메인화면)-임상우
//8. 순위차트(메인화면)-임상우
//9. 검색기능(헤더-메인화면)-임상우
//최신음악(메인화면)

@WebServlet("*.do")
@SuppressWarnings("serial")
public class DispatcherSerlvet extends HttpServlet{

	@SuppressWarnings("deprecation")
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String servletPath = request.getServletPath();
		
		try {
			ServletContext sc = this.getServletContext();
			Controller pageController = (Controller)sc.getAttribute(servletPath);
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			//예인
			if ("/member/list.do".equals(servletPath)) {
			} else if ("/member/add.do".equals(servletPath)) {
				// 아래 조건이 성립하지 않으면 doGet요청(입력 페이지 전송)
				if (request.getParameter("user_email") != null) {
					// doPost (추가할 새로운 데이터가 존재할 때)
					model.put("member", new Member()
							.setUser_email(request.getParameter("user_email"))
							.setUser_pw(request.getParameter("user_pw"))
							.setUser_nick(request.getParameter("user_nick")));

				}
			} else if ("/auth/login.do".equals(servletPath)) {
				if (request.getParameter("user_email") != null) {
					model.put("loginInfo", new Member()
							.setUser_email(request.getParameter("user_email"))
							.setUser_pw(request.getParameter("user_pw")));
				}
			} else if ("/auth/logout.do".equals(servletPath)) {
			}
			
			//상우
			if("/main/main.do".equals(servletPath)) {
				//랜덤음악 배치(메인화면)-임상우
			}else if("/chart/chart.do".equals(servletPath)){
				//순위차트(메인화면)-임상우
					//장르별순위(장르선택하여 값이 들어옴), 가수별 순위(가수이름이 들어옴)
				if(request.getParameter("genre") != null) {
					model.put("chartInput", new Music().setGenre(request.getParameter("genre")));
				}else if(request.getParameter("artist") != null) {
					model.put("chartInput", new Music().setArtist(request.getParameter("artist")));
				}else if(request.getParameter("ranking") != null) {
					model.put("chartInput", new Music().setRanking(Integer.parseInt(request.getParameter("ranking"))) );
				}
			}else if("/search/search.do".equals(servletPath)){
//				검색기능(헤더-메인화면):가수검색, 제목검색, 장르검색
				model.put("search", new Music().setSearch(request.getParameter("search")) );
				if(request.getParameter("category") != null) {
					//세부검색시
					model.put("category", new Music().setCategory(request.getParameter("category")) );
				}else {
					//통합검색시
				}
				
			}
			
			//정연
			if ("/member/list.do".equals(servletPath)) {
				//세션에 user_no가 담겨 있기때문에 필요없다.
//				model.put("member", new Member().setUser_no(Integer.parseInt(request.getParameter("user_no"))));
				//model.put("member", new Member().setUser_no(2));
				System.out.println("★★★★★★★★★★★★★★★★★servletPath" + servletPath);
			}else if ("/member/playList.do".equals(servletPath)) {
			}else if ("/member/update.do".equals(servletPath)) {
	            if(request.getMethod().equals("GET")) {
	                System.out.println("update jsp 페이지 이동");
	             }else {
	                System.out.println("update 파일 업로드 기능");
	                String contextRootPath = this.getServletContext().getRealPath("/");
	                System.out.println("contextRootPath : " + contextRootPath);
	                
	                DiskFileItemFactory diskFactory = new DiskFileItemFactory();
	                diskFactory.setRepository(new File(contextRootPath + "/WEB-INF/temp"));
	                ServletFileUpload upload = new ServletFileUpload(diskFactory);
	                
	                @SuppressWarnings("unchecked")
	                List<FileItem> items = upload.parseRequest(request);
	                FileItem item = null;
	                Member member = new Member();
	                for(int i=0;i<items.size();i++) {
	                   item = items.get(i);
	                   if(item.isFormField()) {      // 일반 전달변수
	                      if (item.getFieldName().equals("user_no")) {
	                         String user_noStr = processFormField(item);
	                         member.setUser_no(Integer.parseInt(user_noStr));
	                      } else if (item.getFieldName().equals("user_email")) {
	                         member.setUser_email(processFormField(item));
	                      } else if (item.getFieldName().equals("user_pw")) {
	                         member.setUser_pw(processFormField(item));
	                      } else if (item.getFieldName().equals("user_nick")) {
	                         member.setUser_nick(processFormField(item));
	                      }
	                   }else {      // 업로드 파일         
	                      if (item.getFieldName().equals("user_pic")) {
	                         member.setUser_pic(processUploadFile(item, contextRootPath));
	                      }
	                   }
	                }
	                model.put("member", member);
	                model.put("contextRootPath", contextRootPath);
	             }
	          }//지은
			if ("/music/play.do".equals(servletPath)) {
				System.out.println(request.getMethod());
				if (request.getMethod().equals("GET") == false)
					model.put("listName",request.getParameter("playlist"));
				//uno는 나중에 받아
				model.put("id", new String(request.getParameter("id")));

			} else if ("/music/upload.do".equals(servletPath)) {
				System.out.println(request.getMethod());
				if (request.getMethod().equals("GET") == false) {

					String contextRootPath = this.getServletContext().getRealPath("/");

					System.out.println("contextRootPath : " + contextRootPath);

					DiskFileItemFactory diskFactory = new DiskFileItemFactory();
					diskFactory.setRepository(new File(contextRootPath + "/WEB-INF/temp"));
					ServletFileUpload upload = new ServletFileUpload(diskFactory);

					@SuppressWarnings("unchecked")
					List<FileItem> items = upload.parseRequest(request); // 여기선 잘들어옴, name= null
					model.put("items", items); // 여기서 안들어오네
					FileItem item = null;

					String title = "", artist = "", genre = "";
					for (int i = 0; i < items.size(); i++) {
						item = items.get(i);
						if (item.isFormField()) { // 일반 전달변수일 떄
							if (item.getFieldName().equals("title"))// 파라미터 판별
								title = item.getString("UTF-8"); // 파라미터 값 문자열로 가져오기
							if (item.getFieldName().equals("artist"))
								artist = item.getString("UTF-8");
							if (item.getFieldName().equals("genre"))
								genre = item.getString("UTF-8");
						}
						// [0][1][2][3][4] 순이라 파일들은 index 3,4에 있음
					}

					model.put("music", new Music().setTitle(title).setArtist(artist).setGenre(genre));
//
					model.put("out", response.getWriter());
					model.put("contextRootPath", contextRootPath);

				}} else if ("/music/delete.do".equals(servletPath)) {
			}
			
			String viewUrl = "";
			if(pageController != null) {
				viewUrl = pageController.execute(model);
				for(String key: model.keySet())
					request.setAttribute(key, model.get(key));
			}else {
				System.out.println("주소 대상 Controller가 없습니다!");
			}
			
			// 경로가 'redirect:'로 시작하면 바로 이동
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	   private String processUploadFile(FileItem item, String contextRootPath) throws Exception{
		      String name = item.getFieldName();
		      String fileName = item.getName();
		      
		      String uploadedFileName = System.currentTimeMillis() + 
		               fileName.substring(fileName.lastIndexOf("."));
		      File uploadedFile = new File(
		            contextRootPath + "/upload/" + uploadedFileName);
		      item.write(uploadedFile);
		      return uploadedFileName;
		   }
		   
		   private String processFormField(FileItem item) throws Exception{
		      String name = item.getFieldName();
		      String value = item.getString("UTF-8");
		      return value;
		   }
}
