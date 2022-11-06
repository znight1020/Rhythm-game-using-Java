package dynamic_beat3;

public class Beat {
	private int time;
	private String noteName;
	private int num;
	public Beat(int time, String noteName , int num) {
		super();
		this.time = time;
		this.noteName = noteName;
		this.num = num;
	}

	public void setTime(int Time) {
		this.time = Time;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	public String getNoteName() {
		return noteName;
	}

	public int getTime() {
		return time;
	}
	
	public int getnum() {
		return num;
	}
}