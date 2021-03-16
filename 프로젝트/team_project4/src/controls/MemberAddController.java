package controls;

import java.util.Map;

import dao.MemberDao;
import vo.Member;

public class MemberAddController implements Controller {

	MemberDao memberDao = null;

	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("member") == null) {
			return "/member/MemberForm.jsp"; // 회원가입창
		} else {
			Member member = (Member) model.get("member");
			memberDao.insert(member);

			return "redirect:../main/main.do";
		}
	}

}

