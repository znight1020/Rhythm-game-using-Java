package dynamic_beat3;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class SignDialog extends JDialog {
	private JPanel signUpPanel = new JPanel();
	private JOptionPane MessageBox = new JOptionPane();
	private JLabel idlabel = new JLabel("아이디");
	private JLabel pwlabel = new JLabel("비밀번호");
	private JLabel pwChecklabel = new JLabel("비밀번호 확인");
	private JTextField idText = new JTextField(15);
	private JPasswordField pwText = new JPasswordField(15);
	private JPasswordField pwCheckText = new JPasswordField(15);
	private JButton signUpbtn = new JButton("회원가입");
	private JButton overlapBtn = new JButton("중복확인");
	private boolean check = false;
	public SignDialog(DynamicBeat frame) {
		signUpPanel.setLayout(null);
		setTitle("회원가입");
		setContentPane(signUpPanel);

		idlabel.setBounds(50, 20, 100, 20);
		signUpPanel.add(idlabel);

		idText.setBounds(50, 40, 100, 20);
		signUpPanel.add(idText);

		overlapBtn.setBounds(180, 40, 90, 20);
		overlapBtn.setContentAreaFilled(false);
		overlapBtn.setFocusPainted(false);
		overlapBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				overlapBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				overlapBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!(idText.getText().length() >= 4)) {
					MessageBox.showMessageDialog(null, "4글자 이상으로 작성해주세요!");
				} else if (frame.dbCon.isAdmin(idText.getText())) {
					MessageBox.showMessageDialog(null, "중복된 아이디입니다. 다시 입력해주세요");
					idText.setText("");
				} else {
					MessageBox.showMessageDialog(null, "사용 가능한 아이디입니다.");
					check = true;
				}
			}
		});
		signUpPanel.add(overlapBtn);

		pwlabel.setBounds(50, 100, 100, 20);
		signUpPanel.add(pwlabel);

		pwText.setBounds(50, 120, 100, 20);
		signUpPanel.add(pwText);

		pwChecklabel.setBounds(180, 100, 100, 20);
		signUpPanel.add(pwChecklabel);

		pwCheckText.setBounds(180, 120, 100, 20);
		signUpPanel.add(pwCheckText);

		signUpbtn.setBounds(120, 180, 100, 20);
		signUpbtn.setContentAreaFilled(false);
		signUpbtn.setFocusPainted(false);
		signUpbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				signUpbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				String pass = new String(pwText.getPassword());
				String passcheck = new String(pwCheckText.getPassword());
				if (idlabel == null || pass == null || passcheck == null) {
					MessageBox.showMessageDialog(null, "빈칸을 입력해주세요.");
				} else if (!check) {
					MessageBox.showMessageDialog(null, "아이디를 중복확인해 주세요.");
				} else if (!(pass.length() >= 4)) {
					MessageBox.showMessageDialog(null, "비밀번호를 4글자 이상으로 작성해주세요.");
				} else if (!pass.equals(passcheck)) {
					MessageBox.showMessageDialog(null, "비밀번호를 다시 확인해주세요.");
				} else {
					if (frame.dbCon.Enrollment(idText.getText(), pass)) {
						MessageBox.showMessageDialog(null, "회원가입이 완료되었습니다.");
						setVisible(false);
					} else {
						MessageBox.showMessageDialog(null, "회원가입에 실패하였습니다. 다시 시도해주세요.");
					}

				}
			}
		});
		signUpPanel.add(signUpbtn);

		setSize(350, 250);
		setLocationRelativeTo(null);
	}
}