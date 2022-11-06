package dynamic_beat3;

public class User {
	private String ID;
	private String guest;
	protected int howl_score[] = new int[4];
	private String howl_id[] = new String[4];
	private int inu_score[] = new int[4];
	private String inu_id[] = new String[4];
	private int silver_score[] = new int[4];
	private String silver_id[] = new String[4];
	private int nowScore;

	public int getNowScore() {
		return nowScore;
	}

	public void setNowScore(int nowScore) {
		this.nowScore = nowScore;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public void setrank(String titlename, int rank[], String Id[]) {
		if (titlename.equals("howl")) {
			for (int i = 0; i < 4; i++) {
				howl_score[i] = rank[i];
				howl_id[i] = Id[i];
			}
		} else if (titlename.equals("inu")) {
			for (int i = 0; i < 4; i++) {
				inu_score[i] = rank[i];
				inu_id[i] = Id[i];
			}
		} else if (titlename.equals("silver")) {
			for (int i = 0; i < 4; i++) {
				silver_score[i] = rank[i];
				silver_id[i] = Id[i];
			}
			
		}
		
	}
}
