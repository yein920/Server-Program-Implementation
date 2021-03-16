package controls;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.MusicDao;
import vo.Member;
import vo.Music;

//페이지나 redirect 반환할것 -> 그걸 프론트컨트롤에서 jsp 위임할것
// 공통 작업은 프론트 컨트롤러에 있으니 여기서 개별 페이지 작업해야해..

public class UploadController implements Controller {

	MusicDao musicDao = null;
	String mno = null;
	String mPic = null;
	
	public UploadController setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		if(model.get("music")==null) {
			return "UploadForm.jsp";
		}else {
			System.out.println("UploadController-post");
			//요청객체에서 사용하던 것은 Dispatcher에서 모델에 담아 가져옴
//			PrintWriter out = (PrintWriter) model.get("out");

			@SuppressWarnings("unchecked")
			List<FileItem> items= (List<FileItem>) model.get("items"); //여기서items null

			String contextRootPath= (String) model.get("contextRootPath");
			FileUpload(items,contextRootPath);

			Music music = (Music)model.get("music");
			
			HttpSession session = (HttpSession) model.get("session");
			Member member  = (Member) session.getAttribute("member");
			int no = member.getUser_no();
			
			music.setUser_no(no);
			music.setMusic_id(mno);
			music.setMusic_pic(mPic);
			
			int done = musicDao.upload(music);
			System.out.println(done + "개 업로드 완료");
			return "redirect:../music/play.do?id="+mno;
		}
		//내가 올린목록(정연씨) 한테로 리디렉해야할듯. 아님 재생페이지
	}
	
	private void FileUpload( List<FileItem> items, String contextRootPath)throws ServletException, IOException  {

		try {
			FileItem item = null;

			for(int i=3;i<items.size();i++) {
				item = items.get(i);
				processUploadFile(item, contextRootPath);
//					model.put("img", imgName); //반환해야하나?
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void processUploadFile(FileItem item, String contextRootPath)
			throws Exception{
		String name = item.getFieldName();
//		String contentType = item.getContentType();
		String fileName = item.getName();		
		//name = null,Cotent
		System.out.println(contextRootPath);
		
		//랜덤숫자?일련번호생성되는데 이거 music_no로 db에 넣으면 될듯?
		String uploadedFileName = System.currentTimeMillis()+
					fileName.substring(fileName.lastIndexOf(".")); //없으면 확장자 없이 사용가능
		File uploadedFile = new File(
				contextRootPath + "/upload/" + uploadedFileName);//
		item.write(uploadedFile);
		
		if(name.equals("photo")) {
			mPic = uploadedFileName;
		}else {
			mno = uploadedFileName;
		}
	}
	//Description 지금은 없기 떄문에
//	private void processFormField(PrintWriter out, FileItem item) 
//			throws Exception{
//			
//			String name = item.getFieldName();
//			String value = item.getString("UTF-8");
//			
//			out.println(name + ":" + value + "<br>");
//		}
}


