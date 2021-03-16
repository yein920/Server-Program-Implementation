package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

public class MemberUpdateController implements Controller {
   
   MemberDao memberDao = null;
   
   public MemberUpdateController setMemberDao(MemberDao memberDao) {
      this.memberDao = memberDao;
      return this;
   }

   @Override
   public String execute(Map<String, Object> model) throws Exception {
      
      if(model.get("member") == null) {
         HttpSession session = (HttpSession) model.get("session");
         Member member1  = (Member) session.getAttribute("member");
         int no = member1.getUser_no();
//         int no = 2;   //테스트용
         Member member = memberDao.selectInfo(no);
         model.put("member", member);
//         return "/member/MemberUpdateForm.jsp";
         return "/member/Mypage.jsp";
      }else {
         Member member = (Member)model.get("member");
         memberDao.update(member);
         return "redirect:../member/list.do";
      }
      
      
   }

}

