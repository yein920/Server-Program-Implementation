package controls;

import java.util.Map;

import dao.MusicDao;
import vo.Music;

public class SearchController implements Controller {
	
	MusicDao musicDao = null;
	
	public SearchController setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Music cate = (Music) model.get("category");
		String category = "integrated";
		if(cate != null) {
			category = cate.getCategory();
		}
		Music search = (Music) model.get("search");
		String haveBarSpace = search.getSearch();
		//검색으로 입력된 문자열의 공백과 막대바를 제거
		String noBarSpace = haveBarSpace.replaceAll(" ", "").replaceAll("-","");  
		
		model.put("searchResult",musicDao.search(noBarSpace,category));
		return "/search/Search.jsp";
	}

}
