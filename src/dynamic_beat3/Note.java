package dynamic_beat3;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image white_note = new ImageIcon(Main.class.getResource("../images/white_note.png")).getImage();
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.Note_SPEED) * Main.RECH_TIME;
	private String noteType;
	private boolean proceeded = true;
	protected String result;
	private int num;

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public String getresult() {
		return result;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String noteType, int num) {
		if (noteType.equals("A")) {
			x = 165;
		} else if (noteType.equals("S")) {
			x = 325;
		} else if (noteType.equals("D")) {
			x = 485;
		} else if (noteType.equals("J")) {
			x = 645;
		} else if (noteType.equals("K")) {
			x = 805;
		} else if (noteType.equals("L")) {
			x = 965;
		}
		this.noteType = noteType;
		this.num = num;
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}

	public void drop() {
		y += Main.Note_SPEED;
		if (y > 675 && !DynamicBeat.game.isInterrupted()) {
			result = "Miss";
			DynamicBeat.game.result = result;
			noteBasicImage = white_note;
			close();
			interrupt();
		} else if(DynamicBeat.game.isInterrupted()) {
			close();
			interrupt();
		}
	}

	public String judge() {
		noteBasicImage = white_note;
		if (y >= 680) {
			result = "Miss";
			close();
		} else if (y >= 660) {
			result = "Great";
			close();
		} else if (y >= 640) {
			result = "Perfect";
			close();
		} else if (y >= 620) {
			result = "Great";
			close();
		} else if (y >= 570) {
			result = "Miss";
			close();
		}
		return result;
	}

	public int getnum() {
		return num;
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
