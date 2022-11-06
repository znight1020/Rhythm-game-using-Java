package dynamic_beat3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	protected int ESC_OnOff = 0;

	ArrayList<Note> noteList = new ArrayList<Note>();

	protected int percnt = 0, greatcnt = 0, misscnt = 0;
	private int x, y;
	private int notecount = 0;
	private boolean xy;
	private Image impact = new ImageIcon(Main.class.getResource("../images/impact.png")).getImage();

	private String titleName;
	private String musicTitle;
	private Music gameMusic;
	protected String result;
	private boolean isgamed;
	private static int iCount = 0;
	private String state;

	public Game(String titleName, String musicTitle) {
		isgamed = true;
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}


	public void screenDraw(Graphics2D g) {
		if (xy) {
			g.drawImage(impact, x, y, null);
		}
		if (!isInterrupted()) {
			for (int i = notecount; i < noteList.size(); i++) {
				Note note = noteList.get(i);
				note.screenDraw(g);
				if (note.result != null) {
					notecount++;
					if (note.getnum() == 100) {
						state = "score";
						ESC_OnOff = 2;
					}
				}
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Aral", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
	}

	public String getstate() {
		return state;
	}

	public void pressESCAPE() {
		if(state.equals("게임시작")) {
			close();
			ESC_OnOff = 2;
			state = "ESC";
		}
	}

	public void releaeESCAPE() {

	}

	public void pressA() {
		judge("A");
	}

	public void releaseA() {

	}

	public void pressS() {
		judge("S");
	}

	public void releaseS() {

	}

	public void pressD() {
		judge("D");
	}

	public void releaseD() {

	}

	public void pressJ() {
		judge("J");
	}

	public void releaseJ() {

	}

	public void pressK() {
		judge("K");
	}

	public void releaseK() {

	}

	public void pressL() {
		judge("L");
	}

	public void releaseL() {

	}
	
	public void reset() {
		isgamed = true;
		result = null;
		iCount = 0;
		state = "대기";
		ESC_OnOff = 0;
		percnt = 0;
		greatcnt = 0;
		misscnt = 0;
		notecount = 0;
		noteList.clear();
		gameMusic = new Music(this.musicTitle, false);
	}

	
	@Override
	public void run() {
		try {
			reset();
			state = "게임시작";
			while (isgamed) {
				dropNotes();
			}
			System.out.println("Game Over");
		} catch (Exception e) {

		}
	}

	public void sleep() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		isgamed = false;
		gameMusic.close();
		this.interrupt();
	}

	public void judge(String input) {
		for (int ip = iCount; ip < noteList.size(); ip++) {
			Note note = noteList.get(ip);
			if (note.result != null) {
				if (note.result.equals("Miss")) {
					iCount++;
				}
			} else if (input.equals(note.getNoteType())) {
				result = note.judge();
				if (ip != iCount) {
					iCount++;
					break;
				} else {
					iCount++;
					break;
				}
			}
		}
	}

	public void cal() {
		Note note;
		for (int i = 0; i < noteList.size(); i++) {
			note = noteList.get(i);
			if (note.result.equals("Perfect")) {
				percnt++;
			} else if (note.result.equals("Great")) {
				greatcnt++;
			} else if (note.result.equals("Miss")) {
				misscnt++;
			}
		}
	}

	public void send(boolean xy, int x, int y) {
		this.x = x;
		this.y = y;
		this.xy = xy;
	}

	public void dropNotes() {
		Beat[] beats = null;
		System.out.println(titleName);
		if (titleName.equals("하울의 움직이는 성")) {
			int startTime = 2400 - Main.RECH_TIME * 1000;
			int gap = 150;
			beats = new Beat[] { new Beat(startTime + gap * 2, "A", 1), new Beat(startTime + gap * 5, "S", 2),
					new Beat(startTime + gap * 8, "D", 3), new Beat(startTime + gap * 11, "J", 4),
					new Beat(startTime + gap * 16, "K", 5),

					new Beat(startTime + gap * 20, "J", 6), new Beat(startTime + gap * 23, "D", 7),
					new Beat(startTime + gap * 25, "S", 8), // ok
					new Beat(startTime + gap * 28, "S", 9),

					new Beat(startTime + gap * 36, "K", 10), // ok

					new Beat(startTime + gap * 40, "S", 11), new Beat(startTime + gap * 41, "D", 12),
					new Beat(startTime + gap * 42, "J", 13), new Beat(startTime + gap * 43, "K", 14),
					new Beat(startTime + gap * 45, "L", 15),

					new Beat(startTime + gap * 51, "A", 16), new Beat(startTime + gap * 53, "D", 17),

					new Beat(startTime + gap * 57, "K", 18),

					new Beat(startTime + gap * 62, "D", 19), new Beat(startTime + gap * 64, "K", 20),
					new Beat(startTime + gap * 65, "J", 21),

					new Beat(startTime + gap * 77, "S", 22), new Beat(startTime + gap * 78, "D", 23),
					new Beat(startTime + gap * 79, "K", 24), new Beat(startTime + gap * 81, "J", 25),

					new Beat(startTime + gap * 86, "A", 26),

					new Beat(startTime + gap * 89, "D", 27), new Beat(startTime + gap * 91, "S", 28),
					new Beat(startTime + gap * 93, "A", 29),

					//
					new Beat(startTime + gap * 95, "S", 30), new Beat(startTime + gap * 97, "K", 31),

					new Beat(startTime + gap * 100, "D", 32), new Beat(startTime + gap * 102, "L", 33),

					new Beat(startTime + gap * 112, "A", 34), new Beat(startTime + gap * 114, "S", 35),
					new Beat(startTime + gap * 116, "D", 36), new Beat(startTime + gap * 118, "J", 37),
					new Beat(startTime + gap * 120, "K", 38), new Beat(startTime + gap * 122, "L", 39),
					new Beat(startTime + gap * 124, "K", 40), new Beat(startTime + gap * 128, "J", 100)

			};
		} else if (titleName.equals("Silver Scrapes")) {
			int startTime = 1850 - Main.RECH_TIME * 1000;
			int gap = 150;
			beats = new Beat[] { new Beat(startTime + gap * 3, "A", 1), new Beat(startTime + gap * 6, "L", 2),
					new Beat(startTime + gap * 9, "D", 3), new Beat(startTime + gap * 11, "K", 4),

					new Beat(startTime + gap * 14, "S", 5), new Beat(startTime + gap * 18, "K", 6),
					new Beat(startTime + gap * 20, "A", 7), new Beat(startTime + gap * 23, "L", 8),

					new Beat(startTime + gap * 26, "D", 9), new Beat(startTime + gap * 28, "J", 10),
					new Beat(startTime + gap * 32, "A", 11), new Beat(startTime + gap * 34, "L", 12),

					new Beat(startTime + gap * 38, "D", 13), new Beat(startTime + gap * 40, "J", 14),
					new Beat(startTime + gap * 43, "S", 15), new Beat(startTime + gap * 45, "K", 16),

					new Beat(startTime + gap * 48, "A", 17), new Beat(startTime + gap * 50, "L", 18),
					new Beat(startTime + gap * 54, "D", 19), new Beat(startTime + gap * 56, "K", 20),

					new Beat(startTime + gap * 60, "J", 21), new Beat(startTime + gap * 63, "S", 22),

					new Beat(startTime + gap * 66, "A", 23), new Beat(startTime + gap * 69, "K", 24),
					new Beat(startTime + gap * 72, "D", 25), // 하이라이트 도입부

					new Beat(startTime + gap * 78, "S", 26), new Beat(startTime + gap * 80, "D", 27),
					new Beat(startTime + gap * 83, "K", 28),

					new Beat(startTime + gap * 89, "D", 29), new Beat(startTime + gap * 91, "S", 30),
					new Beat(startTime + gap * 94, "A", 31),

					new Beat(startTime + gap * 99, "K", 32), new Beat(startTime + gap * 101, "D", 33),
					new Beat(startTime + gap * 103, "L", 34),

					new Beat(startTime + gap * 112, "A", 35), new Beat(startTime + gap * 114, "S", 36),
					new Beat(startTime + gap * 116, "D", 37),

					new Beat(startTime + gap * 122, "J", 38), new Beat(startTime + gap * 124, "K", 39),
					new Beat(startTime + gap * 126, "L", 40), new Beat(startTime + gap * 128, "S", 41),

					new Beat(startTime + gap * 135, "J", 42), new Beat(startTime + gap * 136, "S", 43),

					new Beat(startTime + gap * 146, "A", 44), new Beat(startTime + gap * 147, "L", 45),

					new Beat(startTime + gap * 157, "A", 46), new Beat(startTime + gap * 159, "S", 47),
					new Beat(startTime + gap * 162, "L", 48),

					new Beat(startTime + gap * 170, "L", 49), new Beat(startTime + gap * 172, "S", 50),
					new Beat(startTime + gap * 174, "A", 51),

					new Beat(startTime + gap * 180, "D", 52), new Beat(startTime + gap * 182, "J", 53),
					new Beat(startTime + gap * 186, "K", 54),

					new Beat(startTime + gap * 192, "K", 55), new Beat(startTime + gap * 194, "J", 56),
					new Beat(startTime + gap * 197, "D", 57),

					new Beat(startTime + gap * 203, "L", 58), new Beat(startTime + gap * 205, "S", 59),
					new Beat(startTime + gap * 208, "D", 60),

					new Beat(startTime + gap * 214, "A", 61), new Beat(startTime + gap * 216, "K", 62),
					new Beat(startTime + gap * 219, "L", 63),

					new Beat(startTime + gap * 225, "S", 64), new Beat(startTime + gap * 227, "J", 65),

					new Beat(startTime + gap * 239, "A", 66), new Beat(startTime + gap * 241, "S", 67),
					new Beat(startTime + gap * 243, "D", 68),

					new Beat(startTime + gap * 250, "J", 69), new Beat(startTime + gap * 252, "K", 70),
					new Beat(startTime + gap * 254, "L", 71),

					new Beat(startTime + gap * 261, "A", 72), new Beat(startTime + gap * 263, "S", 73),
					new Beat(startTime + gap * 265, "D", 100) };
		} else if (titleName.equals("시대를 초월한 마음")) {
			int startTime = 1000 - Main.RECH_TIME * 1000;
			int gap = 100;
			beats = new Beat[] { new Beat(startTime + gap * 12, "A", 1), new Beat(startTime + gap * 20, "S", 2),
					new Beat(startTime + gap * 30, "D", 3), new Beat(startTime + gap * 40, "J", 4),
					new Beat(startTime + gap * 50, "K", 5), new Beat(startTime + gap * 58, "L", 6),
					new Beat(startTime + gap * 63, "K", 7), new Beat(startTime + gap * 66, "D", 8),

					new Beat(startTime + gap * 75, "D", 9), new Beat(startTime + gap * 80, "K", 10),
					new Beat(startTime + gap * 85, "S", 11),

					new Beat(startTime + gap * 98, "A", 12), new Beat(startTime + gap * 103, "S", 13),
					new Beat(startTime + gap * 108, "J", 14), new Beat(startTime + gap * 113, "K", 15),
					new Beat(startTime + gap * 118, "L", 16), new Beat(startTime + gap * 123, "D", 17),

					//
					new Beat(startTime + gap * 140, "A", 18),

					new Beat(startTime + gap * 150, "S", 19), new Beat(startTime + gap * 155, "D", 20),
					new Beat(startTime + gap * 160, "S", 21), // 5

					new Beat(startTime + gap * 168, "J", 22), new Beat(startTime + gap * 173, "D", 23),
					new Beat(startTime + gap * 178, "J", 24), // 5

					new Beat(startTime + gap * 187, "J", 25), new Beat(startTime + gap * 192, "K", 26), // 5
					new Beat(startTime + gap * 197, "J", 27),

					new Beat(startTime + gap * 206, "K", 28), new Beat(startTime + gap * 211, "J", 29), // 4
					new Beat(startTime + gap * 215, "K", 30),

					////
					new Beat(startTime + gap * 223, "S", 31), new Beat(startTime + gap * 228, "K", 32),
					new Beat(startTime + gap * 233, "S", 33),

					new Beat(startTime + gap * 243, "K", 34), new Beat(startTime + gap * 248, "S", 35),
					new Beat(startTime + gap * 253, "K", 36),

					new Beat(startTime + gap * 260, "D", 37), new Beat(startTime + gap * 272, "D", 38),

					//

					new Beat(startTime + gap * 284, "A", 39), new Beat(startTime + gap * 289, "S", 40),
					new Beat(startTime + gap * 294, "D", 41),

					new Beat(startTime + gap * 304, "J", 42), new Beat(startTime + gap * 309, "K", 43),
					new Beat(startTime + gap * 314, "L", 44),

					new Beat(startTime + gap * 324, "K", 45), new Beat(startTime + gap * 329, "D", 46),
					new Beat(startTime + gap * 334, "S", 47),

					new Beat(startTime + gap * 340, "S", 48), new Beat(startTime + gap * 345, "D", 49),
					new Beat(startTime + gap * 350, "K", 50),
					//
					new Beat(startTime + gap * 360, "K", 51), new Beat(startTime + gap * 365, "D", 52),
					new Beat(startTime + gap * 370, "S", 53),

					new Beat(startTime + gap * 378, "L", 54), new Beat(startTime + gap * 383, "S", 55),
					new Beat(startTime + gap * 388, "D", 56),

					new Beat(startTime + gap * 400, "A", 57), new Beat(startTime + gap * 405, "L", 58),
					new Beat(startTime + gap * 410, "S", 59), new Beat(startTime + gap * 415, "K", 60),
					new Beat(startTime + gap * 420, "D", 61), new Beat(startTime + gap * 425, "J", 62),
					//

					new Beat(startTime + gap * 452, "A", 63), new Beat(startTime + gap * 457, "S", 64),
					new Beat(startTime + gap * 462, "D", 65),

					new Beat(startTime + gap * 470, "L", 66), new Beat(startTime + gap * 475, "K", 67),
					new Beat(startTime + gap * 480, "J", 68),

					new Beat(startTime + gap * 489, "S", 69), new Beat(startTime + gap * 494, "D", 70),
					new Beat(startTime + gap * 499, "J", 71),
					//
					new Beat(startTime + gap * 507, "K", 72), new Beat(startTime + gap * 512, "J", 73),
					new Beat(startTime + gap * 517, "D", 74),

					new Beat(startTime + gap * 527, "S", 75), new Beat(startTime + gap * 532, "K", 76),
					new Beat(startTime + gap * 537, "S", 77),

					new Beat(startTime + gap * 547, "K", 78), new Beat(startTime + gap * 552, "S", 79),
					new Beat(startTime + gap * 557, "K", 80),

					new Beat(startTime + gap * 565, "J", 81), new Beat(startTime + gap * 575, "D", 100), };
		}
		int i = 0;
		iCount = 0;
		gameMusic.start();
		Note note = new Note(beats[0].getNoteName(), beats[0].getnum());
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				note = new Note(beats[i].getNoteName(), beats[i].getnum());
				note.start();
				noteList.add(note);
				i++;
				System.out.println("비트출력" + i);
			}
			if (!dropped && ESC_OnOff == 1) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
