package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

public class LogInController implements Controller {
	
	MemberDao memberDao = null;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		if(model.get("loginInfo") == null) {
			return "/auth/LogInForm.jsp";
		}else {
			Member loginInfo = (Member)model.get("loginInfo");
			
			Member member = memberDao.exist(loginInfo.getUser_email(), 
											loginInfo.getUser_pw()
											);
			if(member != null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("member", member);
				return "redirect:../main/main.do";
			}else {
				return "/auth/LogInFail.jsp";
			}
		}
	}
}





