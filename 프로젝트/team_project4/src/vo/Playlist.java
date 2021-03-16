package vo;

public class Playlist {
	protected String playlistName;
	protected int user_no;
	protected String music_no;

	public String getPlaylistName() {
		return playlistName;
	}

	public Playlist setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
		return this;
	}

	public int getUser_no() {
		return user_no;
	}

	public Playlist setUser_no(int uno) {
		this.user_no = uno;
		return this;
	}

	public String getMusic_no() {
		return music_no;
	}

	public Playlist setMusic_no(String music_no) {
		this.music_no = music_no;
		return this;
	}

}
