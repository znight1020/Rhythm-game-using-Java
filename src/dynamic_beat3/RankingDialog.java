package dynamic_beat3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

class RankingDialog extends JDialog {
	private DynamicBeat beat;
	private User user;
	private JPanel signUpPanel = new JPanel();
	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/x.png")));

	private int[] userScore = new int[4];
	private int mouseX, mouseY;

	private String titleName;
	private String[] userId = new String[4];

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/MenuBar.png")));
	private JLabel[] id_label = new JLabel[4];
	private JLabel[] score_label = new JLabel[4];

	private Font font = new Font("맑은 고딕", Font.PLAIN, 15);

	public RankingDialog(DynamicBeat frame) {
		setTitle("순위 표");
		setUndecorated(true);
		setContentPane(signUpPanel);

		user = frame.getUser();
		beat = frame;

		signUpPanel.setLayout(null);
		signUpPanel.setBackground(Color.black);

		titleName = beat.trackList.get(beat.nowSelected).getTitleName();
		int score = beat.user.getNowScore();

		userId = beat.dbCon.rank_id;
		userScore = beat.dbCon.rank_score;

		beat.dbCon.userRank(user.getID(), titleName, score);

		exitButton.setBounds(220, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				beat.rank.setVisible(false);
				beat.rank.dispose();
				beat.count--;
			}
		});
		signUpPanel.add(exitButton);

		menuBar.setBounds(0, 0, 250, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		signUpPanel.add(menuBar);

		for (int i = 0; i < 4; i++) {
			id_label[i] = new JLabel(i + 1 + "등 : " + userId[i]);
			id_label[i].setBounds(20, 40 + 30 * i, 100, 20);
			score_label[i] = new JLabel(Integer.toString(userScore[i]));
			score_label[i].setBounds(120, 40 + 30 * i, 100, 20);
			id_label[i].setVisible(true);
			score_label[i].setVisible(true);
			if (userScore[i] == 0) {
				score_label[i].setVisible(false);
				id_label[i].setVisible(false);
			}
			id_label[i].setForeground(Color.white);
			score_label[i].setForeground(Color.white);
			id_label[i].setFont(font);
			score_label[i].setFont(font);

			if (i == 3) {
				id_label[i].setText("내 점수 : ");
				id_label[i].setLocation(20, 180);
				score_label[i].setLocation(90, 180);
			}
			add(id_label[i]);
			add(score_label[i]);
		}
		setLocationRelativeTo(null);
		setSize(250, 280);

	}
}