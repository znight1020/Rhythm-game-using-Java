package dynamic_beat3;

public class Track {

	private String titleName;
	private String startImage;
	private String gameImage;
	private String startMusic;
	private String gamemusic;

	public String getTitleName() {
		return titleName;
	}

	public void setTitleImage(String titleName) {
		this.titleName = titleName;
	}

	public String getStartImage() {
		return startImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public String getGamemusic() {
		return gamemusic;
	}

	public void setGamemusic(String gamemusic) {
		this.gamemusic = gamemusic;
	}

	public Track(String titleName, String startImage, String gameImage, String startMusic, String gamemusic) {
		super();
		this.titleName = titleName;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gamemusic = gamemusic;
	}
}
