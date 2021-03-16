package controls;

import java.util.Map;

import dao.MemberDao;
import dao.MusicDao;

public class PlayListDeleteController implements Controller {
	
	MemberDao memberDao = null;
	
	public PlayListDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
//		Integer no = (Integer)model.get("no");
//		memberDao.delete(no);
		
		String music_id = (String)model.get("music_id");
		memberDao.delete(music_id);
		
		System.out.println("여기 넘어 왓닌 ? ");
		
		return "redirect:playList.do";
	}

}








