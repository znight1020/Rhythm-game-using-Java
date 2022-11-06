package dynamic_beat3;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DynamicBeat extends JFrame {
	private int myScreenCount = 0;
	private Image screenImage = new ImageIcon(Main.class.getResource("../images/x.png")).getImage();
	private Graphics screenGraphic;
	private int myScore;
	protected int count;

	private ImageIcon startImage = new ImageIcon(Main.class.getResource("../images/start.png"));
	private ImageIcon endImage = new ImageIcon(Main.class.getResource("../images/end.png"));
	private ImageIcon leftImage = new ImageIcon(Main.class.getResource("../images/left.png"));
	private ImageIcon rightImage = new ImageIcon(Main.class.getResource("../images/right.png"));
	private ImageIcon perfect_Impact_Image = new ImageIcon(Main.class.getResource("../images/perfect.png"));
	private ImageIcon great_Impact_Image = new ImageIcon(Main.class.getResource("../images/great.png"));
	private ImageIcon miss_Impact_Image = new ImageIcon(Main.class.getResource("../images/miss.png"));
	private ImageIcon esc_re_Image = new ImageIcon(Main.class.getResource("../images/restart.png"));
	private ImageIcon esc_back_Image = new ImageIcon(Main.class.getResource("../images/menu.png"));

	private Image redflare = new ImageIcon(Main.class.getResource("../images/redflare.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/bg.jpg")).getImage();
	private Image anote = new ImageIcon(Main.class.getResource("../images/anote.png")).getImage();
	private Image snote = new ImageIcon(Main.class.getResource("../images/snote.png")).getImage();
	private Image dnote = new ImageIcon(Main.class.getResource("../images/dnote.png")).getImage();
	private Image jnote = new ImageIcon(Main.class.getResource("../images/jnote.png")).getImage();
	private Image knote = new ImageIcon(Main.class.getResource("../images/knote.png")).getImage();
	private Image lnote = new ImageIcon(Main.class.getResource("../images/lnote.png")).getImage();

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/MenuBar.png")));
	private JLabel perfect_la = new JLabel("PERFECT : " + "");
	private JLabel great_la = new JLabel("GREAT : " + "" + "굿점수");
	private JLabel miss_la = new JLabel("MISS : " + "" + "0점");
	private JLabel per_impact_la = new JLabel(perfect_Impact_Image);
	private JLabel great_impact_la = new JLabel(great_Impact_Image);
	private JLabel miss_impact_la = new JLabel(miss_Impact_Image);
	private JLabel track_Seleted_Name = new JLabel("하울의 움직이는 성");
	private JLabel id_la = new JLabel("Id");
	private JLabel password_la = new JLabel("Password");
	private JLabel score_la = new JLabel("총 점수 : 0점");

	private JButton startButton = new JButton(startImage);
	private JButton easyButton = new JButton(new ImageIcon(Main.class.getResource("../images/Easy.png")));
	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/x.png")));
	private JButton endButton = new JButton(endImage);
	private JButton leftButton = new JButton(leftImage);
	private JButton rightButton = new JButton(rightImage);

	private JButton esc_re_Button = new JButton(esc_re_Image);
	private JButton esc_back_Button = new JButton(esc_back_Image);
	private JButton sign_Button = new JButton("회원가입");
	private JButton login_Button = new JButton("로그인");
	private JButton logout_Button = new JButton("로그아웃");
	private JButton gomenu_Button = new JButton("홈 화면으로 돌아가기");

	private JButton grade_Button = new JButton("점수내기");
	private JButton showRanking_Button = new JButton("순위표 보기");

	private JTextField id_Tf = new JTextField(20);
	private JPasswordField password_tf = new JPasswordField(20);

	private JOptionPane MessageBox = new JOptionPane();

	private boolean isMainscreen = false;
	private boolean isGamescreen = false;
	private boolean isResult = false;
	private int mouseX, mouseY;

	protected Font font = new Font("맑은 고딕", Font.PLAIN, 40);
	protected Font font2 = new Font("맑은 고딕", Font.PLAIN, 15);

	private DynamicBeat beat = this;
	private SignDialog sign = new SignDialog(this);
	protected DBConnection dbCon = new DBConnection(this);
	protected User user = new User();
	protected RankingDialog rank;

	private Image selectedImage;
	private Music selectedMusic;
	protected int nowSelected = 0;
	private static String musicName = "하울의 움직이는 성.mp3";
	public static Game game = new Game(musicName, musicName);

	public static Music introMusic = new Music("(조이)안녕.mp3", true);
	protected ArrayList<Track> trackList = new ArrayList<Track>();

	public DynamicBeat() {
		setUndecorated(true);
		setTitle("Dyanamic Beat");
		setSize(Main.SCREEN_WITTH, Main.SCREEN_HEIGHT);
		introMusic.start();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		dbCon.connect();
		addKeyListener(new KeyListener());

		trackList.add(new Track("하울의 움직이는 성", "howl2.png", "play.png", "하울의 움직이는 성.mp3", "하울의 움직이는 성.mp3"));
		trackList.add(new Track("시대를 초월한 마음", "시대를 초월한 마음.png", "play.png", "시대를 초월한 마음.mp3", "시대를 초월한 마음.mp3"));
		trackList.add(new Track("Silver Scrapes", "lol.jpg", "play.png", "Silver Scrapes.mp3", "Silver Scrapes.mp3"));

		track_Seleted_Name.setFont(font);
		track_Seleted_Name.setBounds(465, 560, 500, 100);
		track_Seleted_Name.setVisible(false);
		track_Seleted_Name.setForeground(Color.WHITE);
		add(track_Seleted_Name);

		showRanking_Button.setFont(font2);
		showRanking_Button.setVisible(false);
		showRanking_Button.setForeground(Color.WHITE);
		showRanking_Button.setBorderPainted(false);
		showRanking_Button.setBounds(200, 640, 130, 20);
		showRanking_Button.setContentAreaFilled(false);
		showRanking_Button.setFocusPainted(false);
		showRanking_Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				dbCon.userRank(user.getID(), trackList.get(nowSelected).getTitleName(), myScore);
				if (count == 0) {
					rank = new RankingDialog(beat);
					rank.setVisible(true);
					count++;
				}
			}

			public void mouseEntered(MouseEvent e) {
				showRanking_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				showRanking_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(showRanking_Button);

		login_Button.setFont(font2);
		login_Button.setForeground(Color.WHITE);
		login_Button.setBorderPainted(false);
		login_Button.setBounds(530, 630, 100, 20);
		login_Button.setContentAreaFilled(false);
		login_Button.setFocusPainted(false);
		login_Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (dbCon.isAdmin(id_Tf.getText(), new String(password_tf.getPassword()))) {
					endButton.setVisible(true);
					startButton.setVisible(true);
					login_Button.setVisible(false);
					id_Tf.setVisible(false);
					password_tf.setVisible(false);
					id_la.setVisible(false);
					password_la.setVisible(false);
					sign_Button.setVisible(false);
					logout_Button.setVisible(true);
					id_Tf.setText("");
					password_tf.setText("");
				} else {
					MessageBox.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다. 다시 입력해주세요.");
					id_Tf.setText("");
					password_tf.setText("");
				}
			}

			public void mouseEntered(MouseEvent e) {
				login_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				login_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

		});
		add(login_Button);

		logout_Button.setVisible(false);
		logout_Button.setFont(font2);
		logout_Button.setForeground(Color.white);
		logout_Button.setBorderPainted(false);
		logout_Button.setBounds(1150, 680, 100, 20);
		logout_Button.setContentAreaFilled(false);
		logout_Button.setFocusPainted(false);
		logout_Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				nowSelected = 0;
				selectTrack(nowSelected);
				track_Seleted_Name.setText(trackList.get(nowSelected).getTitleName());
				leftButton.setVisible(false);
				rightButton.setVisible(false);
				easyButton.setVisible(false);
				track_Seleted_Name.setVisible(false);
				startButton.setVisible(false);
				endButton.setVisible(false);
				startButton.setVisible(false);
				sign_Button.setVisible(true);
				login_Button.setVisible(true);
				password_tf.setVisible(true);
				password_la.setVisible(true);
				id_la.setVisible(true);
				id_Tf.setVisible(true);
				showRanking_Button.setVisible(false);
				logout_Button.setVisible(false);
				if (introMusic.isInterrupted()) {
					selectedMusic.close();
					introMusic = new Music("(조이)안녕.mp3", true);
					introMusic.start();
				} else if (selectedMusic != null) {
					selectedMusic.close();
				}
				selectedImage = null;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				logout_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logout_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
		});
		add(logout_Button);

		sign_Button.setFont(font2);
		sign_Button.setForeground(Color.WHITE);
		sign_Button.setBorderPainted(false);
		sign_Button.setBounds(650, 630, 100, 20);
		sign_Button.setContentAreaFilled(false);
		sign_Button.setFocusPainted(false);
		sign_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sign_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sign_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				sign.setVisible(true);
			}
		});
		add(sign_Button);

		esc_re_Button.setBounds(740, 260, 200, 200);
		esc_re_Button.setContentAreaFilled(false);
		esc_re_Button.setFocusPainted(false);
		esc_re_Button.setVisible(false);
		esc_re_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				esc_re_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				esc_re_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isGamescreen = true;
				grade_Button.setVisible(true);
				isResult = true;
				myScreenCount = 0;
				background = new ImageIcon(
						Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
				game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGamemusic());
				game.ESC_OnOff = 0;
				game.start();
				requestFocus(true);
				setFocusable(true);
			}
		});
		add(esc_re_Button);

		esc_back_Button.setBounds(340, 260, 200, 200);
		esc_back_Button.setBorderPainted(false);
		esc_back_Button.setContentAreaFilled(false);
		esc_back_Button.setFocusPainted(false);
		esc_back_Button.setVisible(false);
		esc_back_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				esc_back_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				esc_back_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				myScreenCount = 0;
				selectTrack(nowSelected);
				menuBar.setVisible(true);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				easyButton.setVisible(true);
				showRanking_Button.setVisible(true);
				isMainscreen = true;
				startButton.setVisible(false);
				isResult = false;
				logout_Button.setVisible(true);
				track_Seleted_Name.setVisible(true);
				isGamescreen = false;
				background = new ImageIcon(Main.class.getResource("../images/bg.jpg")).getImage();
				game.close();
			}
		});
		add(esc_back_Button);

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		easyButton.setBounds(565, 625, 150, 90);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.setVisible(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				track_Seleted_Name.setVisible(false);
				leftButton.setVisible(false);
				rightButton.setVisible(false);
				easyButton.setVisible(false);
				logout_Button.setVisible(false);
				showRanking_Button.setVisible(false);
				isMainscreen = false;
				isGamescreen = true;
				grade_Button.setVisible(true);
				isResult = true;
				selectedMusic.close();
				background = new ImageIcon(
						Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
				game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGamemusic());
				game.start();

				requestFocus(true);
				setFocusable(true);
			}
		});
		add(easyButton);

		leftButton.setBounds(140, 310, 90, 90);
		leftButton.setVisible(false);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
				track_Seleted_Name.setText(trackList.get(nowSelected).getTitleName());
			}
		});
		add(leftButton);

		rightButton.setBounds(1080, 310, 90, 90);
		rightButton.setVisible(false);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
				track_Seleted_Name.setText(trackList.get(nowSelected).getTitleName());
			}
		});
		add(rightButton);

		endButton.setBounds(490, 540, 300, 100);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		endButton.setVisible(false);
		endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				endButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(endButton);

		startButton.setBounds(490, 440, 300, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.setVisible(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				introMusic.close();
				selectTrack(0);
				track_Seleted_Name.setVisible(true);
				startButton.setVisible(false);
				menuBar.setVisible(true);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				easyButton.setVisible(true);
				endButton.setVisible(false);
				showRanking_Button.setVisible(true);
				isMainscreen = true;
				background = new ImageIcon(Main.class.getResource("../images/bg.jpg")).getImage();

			}
		});
		add(startButton);

		menuBar.setBounds(0, 0, 1280, 30);
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
		menuBar.setVisible(true);
		add(menuBar);

		gomenu_Button.setFont(font2);
		gomenu_Button.setBounds(970, 680, 180, 20);
		gomenu_Button.setForeground(Color.white);
		gomenu_Button.setBorderPainted(false);
		gomenu_Button.setContentAreaFilled(false);
		gomenu_Button.setFocusPainted(false);
		gomenu_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gomenu_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				gomenu_Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				gomenu_Button.setVisible(false);
				introMusic.close();
				selectTrack(nowSelected);
				track_Seleted_Name.setVisible(true);

				perfect_la.setVisible(false);
				great_la.setVisible(false);
				miss_la.setVisible(false);
				score_la.setVisible(false);
				startButton.setVisible(false);
				menuBar.setVisible(true);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				easyButton.setVisible(true);
				endButton.setVisible(false);
				logout_Button.setVisible(true);
				showRanking_Button.setVisible(true);
				isMainscreen = true;
				background = new ImageIcon(Main.class.getResource("../images/bg.jpg")).getImage();
			}
		});
		gomenu_Button.setVisible(false);
		add(gomenu_Button);
		
		per_impact_la.setBounds(412, 390, 450, 100);
		per_impact_la.setVisible(false);
		add(per_impact_la);

		great_impact_la.setBounds(475, 390, 300, 100);
		great_impact_la.setVisible(false);
		add(great_impact_la);

		miss_impact_la.setBounds(475, 390, 300, 100);
		miss_impact_la.setVisible(false);
		add(miss_impact_la);
		
		perfect_la.setBounds(480, 100, 400, 100);
		perfect_la.setFont(font);
		perfect_la.setForeground(Color.white);
		perfect_la.setVisible(false);
		add(perfect_la);

		great_la.setBounds(480, 200, 400, 100);
		great_la.setFont(font);
		great_la.setForeground(Color.white);
		great_la.setVisible(false);
		add(great_la);

		miss_la.setBounds(480, 300, 400, 100);
		miss_la.setFont(font);
		miss_la.setForeground(Color.white);
		miss_la.setVisible(false);
		add(miss_la);

		score_la.setBounds(480, 500, 400, 100);
		score_la.setFont(font);
		score_la.setForeground(Color.white);
		score_la.setVisible(false);
		add(score_la);
		
		id_Tf.setBounds(560, 500, 150, 30);
		add(id_Tf);

		password_tf.setBounds(560, 560, 150, 30);
		add(password_tf);
		
		id_la.setBounds(530, 495, 40, 40);
		id_la.setFont(font2);
		id_la.setForeground(Color.white);
		add(id_la);

		password_la.setBounds(480, 555, 100, 40);
		password_la.setFont(font2);
		password_la.setForeground(Color.white);
		add(password_la);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WITTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (isMainscreen) {
			g.drawImage(selectedImage, 320, 100, null);
		}
		if (isGamescreen) {
			if (game.ESC_OnOff == 1) {
			} else {
				g.drawImage(anote, 165, 650, null);
				g.drawImage(snote, 325, 650, null);
				g.drawImage(dnote, 485, 650, null);
				g.drawImage(jnote, 645, 650, null);
				g.drawImage(knote, 805, 650, null);
				g.drawImage(lnote, 965, 650, null);
			}
			game.screenDraw(g);
		}
		if (game.ESC_OnOff == 1 && myScreenCount == 1) {
			requestFocus(true);
			esc_re_Button.setVisible(true);
			esc_back_Button.setVisible(true);
			startButton.setVisible(false);
			menuBar.setVisible(true);
			per_impact_la.setVisible(false);
			great_impact_la.setVisible(false);
			miss_impact_la.setVisible(false);
			background = new ImageIcon(Main.class.getResource("../images/esc.png")).getImage();
		} else if (game.ESC_OnOff == 2) {
			if (game.getstate().equals("ESC")) {
				isGamescreen = false;
				isResult = false;
				game.ESC_OnOff = 1;
				myScreenCount = 1;
			} else if (game.getstate().equals("score")) {
				score();
				game.ESC_OnOff = 0;
				isResult = false;
			}
		} else {
			esc_re_Button.setVisible(false);
			esc_back_Button.setVisible(false);
		}

		if (isResult) {
			if (game.result == null) {

			} else if (game.result.equals("Perfect")) {
				if (game.ESC_OnOff == 1) {
					per_impact_la.setVisible(false);
				} else {
					per_impact_la.setVisible(true);
					great_impact_la.setVisible(false);
					miss_impact_la.setVisible(false);
					g.drawImage(redflare, 320, 370, null);

				}
			} else if (game.result.equals("Great")) {
				if (game.ESC_OnOff == 1) {
					great_impact_la.setVisible(false);
				} else {
					per_impact_la.setVisible(false);
					great_impact_la.setVisible(true);
					miss_impact_la.setVisible(false);
					g.drawImage(redflare, 320, 370, null);

				}
			} else if (game.result.equals("Miss")) {
				if (game.ESC_OnOff == 1) {
					per_impact_la.setVisible(false);
				} else {
					per_impact_la.setVisible(false);
					great_impact_la.setVisible(false);
					miss_impact_la.setVisible(true);
					g.drawImage(redflare, 320, 370, null);

				}
			}
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		if (isGamescreen) {
			selectedMusic = new Music(trackList.get(nowSelected).getGamemusic(), true);
		} else
			selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void score() {
		per_impact_la.setVisible(false);
		great_impact_la.setVisible(false);
		miss_impact_la.setVisible(false);
		gomenu_Button.setVisible(true);
		game.cal();
		perfect_la.setText("Perfect : " + game.percnt + "    " + 150 * game.percnt);
		great_la.setText("Great : " + game.greatcnt + "    " + 100 * game.greatcnt);
		miss_la.setText("Miss : " + game.misscnt);
		perfect_la.setVisible(true);
		great_la.setVisible(true);
		miss_la.setVisible(true);
		myScore = 150 * game.percnt + 100 * game.greatcnt;
		score_la.setText("총 점수 : " + myScore);
		score_la.setVisible(true);
		user.setNowScore(myScore);
		track_Seleted_Name.setVisible(false);
		isGamescreen = false;
		background = new ImageIcon(Main.class.getResource("../images/black.png")).getImage();
		dbCon.userScore(user.getID(), trackList.get(nowSelected).getTitleName(), myScore);
		dbCon.userRank(user.getID(), trackList.get(nowSelected).getTitleName(), myScore);
	}

	public User getUser() {
		return user;
	}
}
