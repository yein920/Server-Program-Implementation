package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MusicDao;
import vo.Member;
import vo.Music;
import vo.Playlist;

public class PlayController implements Controller {
	MusicDao musicDao = null;

	public PlayController setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
	
		// 플레이리스트에 추가?
			if (model.get("listName") != null) { 
			System.out.println("Playcontroller - POST");
			
			String id = (String) model.get("id");
			model.put("musicOn", musicDao.play(id));
			
			String name = (String) model.get("listName"); // 플레이리스트 제목
			Music music = (Music) model.get("musicOn"); // 재생중인 music 객체 반환

			HttpSession session = (HttpSession) model.get("session");
			Member member  = (Member) session.getAttribute("member");
			int no = member.getUser_no();
			
			Playlist playlist = new Playlist().setPlaylistName(name).setUser_no(no)
					.setMusic_no(music.getMusic_id());
			model.put("playlist", playlist);
			int result = musicDao.addToList(playlist);
			System.out.println(result + "개 곡을 '" + name + "'에 추가했습니다.");
			
			return "redirect:../member/playList.do";
		}else 
		{
			System.out.println("Playcontroller - GET");
			String id = (String) model.get("id");
			model.put("musicOn", musicDao.play(id));
			musicDao.addCount(musicDao.play(id));

			return "/music/PlayForm.jsp";}
	}

}
