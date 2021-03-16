package controls;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;
import vo.Music;

public class MemberListController implements Controller {
   
   MemberDao memberDao = null;
   
   
   public MemberListController setMemberDao(MemberDao memberDao) {
      this.memberDao = memberDao;
      return this;
   }

   @Override
   public String execute(Map<String, Object> model) throws Exception {
//      Integer no = (Integer)model.get("no");
      HttpSession session = (HttpSession)model.get("session");//추가해야함
      Member memberBefore = (Member) session.getAttribute("member");//추가해야함
      int no = memberBefore.getUser_no();//추가해야함
      
      Member member = memberDao.selectInfo(no);
      System.out.println("no : " + member.getUser_no() + ", name : " + member.getUser_nick());
      model.put("member", member);
      
      //업로드 로그
      List<Music> musicList = memberDao.selectMusicList(no);
      model.put("musicList", musicList);
      
      //return "/member/MypageView.jsp?user_no=2";
      return "/member/MypageView.jsp";
   }

}