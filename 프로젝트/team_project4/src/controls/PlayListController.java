package controls;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MusicDao;

import java.util.ArrayList;
import java.util.HashMap;

import dao.MemberDao;
import vo.Member;
import vo.Music;

public class PlayListController implements Controller {
	/* DI(Dependency Injection)으로 변경한 이유
	 * 1) 클래스간의 의존성을 낮추기 위해
	 * 2) MemberDao 인터페이스를 선언하고 상속구현함으로써 
	 *    다른 DBMS로의 전환을 용이하게 하려고
	 * 3) 나중에 변경할 자동화 작업에 사용하려고
	 * */
	
	MemberDao memberDao = null;
	int no ;
	
	public PlayListController setMemberDao(MemberDao memberDao) { // 여기서 mysql문 셀렋트  리스트를 받음.
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession session = (HttpSession) model.get("session");
		Member member  = (Member) session.getAttribute("member");
		int no = member.getUser_no();
		
		System.out.println(no);
		
		List<Music> playList = memberDao.selectPlayList(no);
		model.put("playList", playList);
		List<String> playListNameList = memberDao.selectPlayListName(no);	//카테고리 분리, 2는 임시값
		model.put("category", playListNameList);
		
		return "/member/PlayList.jsp";
	}

}
