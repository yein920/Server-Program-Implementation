package controls;

import java.util.Map;

import dao.MusicDao;

public class MainController implements Controller {

	MusicDao musicDao;
	
	public MainController setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("musics", musicDao.selectList());
		
		return "/main/Main.jsp";
	}

}
