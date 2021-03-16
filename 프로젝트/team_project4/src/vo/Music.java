package vo;

import java.sql.Date;

public class Music {
	String music_id;
	String music_pic;
	int user_no;         
	String genre;          
	String title;           
	Date  cre_date;          
	String artist;         
	int count;
	String user_pic;

	//테이블이랑은 무관하지만 필요해서 추가
	//Databinding을 안 써서 자꾸 추가되는것
	int ranking;
	String search;
	String category;
	String user_nick;
	String Playlist_name;
	
	public String getPlaylist_name() {
		return Playlist_name;
	}
	public Music setPlaylist_name(String playlist_name) {
		Playlist_name = playlist_name;
		return this;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public Music setUser_nick(String user_nick) {
		this.user_nick = user_nick;
		return this;
	}
	//테이블이랑은 무관하지만 필요해서 추가
	public String getSearch() {
		return search;
	}
	public Music setSearch(String search) {
		this.search = search;
		return this;
	}
	public String getCategory() {
		return category;
	}
	public Music setCategory(String category) {
		this.category = category;
		return this;
	}
	public int getRanking() {
		return ranking;
	}
	public Music setRanking(int ranking) {
		this.ranking = ranking;
		return this;
	}
	
	
	
	public String getMusic_id() {
		return music_id;
	}
	public Music setMusic_id(String music_id) {
		this.music_id = music_id;
		return this;
	}
	public String getMusic_pic() {
		return music_pic;
	}
	public Music setMusic_pic(String music_pic) {
		this.music_pic = music_pic;
		return this;
	}
	public int getUser_no() {
		return user_no;
	}
	public Music setUser_no(int user_no) {
		this.user_no = user_no;
		return this;
	}
	public String getGenre() {
		return genre;
	}
	public Music setGenre(String genre) {
		this.genre = genre;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Music setTitle(String title) {
		this.title = title;
		return this;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public Music setCre_date(Date cre_date) {
		this.cre_date = cre_date;
		return this;
	}
	public String getArtist() {
		return artist;
	}
	public Music setArtist(String artist) {
		this.artist = artist;
		return this;
	}
	public int getCount() {
		return count;
	}
	public Music setCount(int count) {
		this.count = count;
		return this;
	}

}
