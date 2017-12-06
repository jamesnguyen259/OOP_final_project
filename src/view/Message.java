package view;

import javax.swing.*;
import java.awt.*;

public class Message extends JDialog {
	private JPanel panel;
	private JLabel label;
	private JButton buttonOK;
	private Frame owner;
	String title, message;

	// cai dat constructor
	public Message(Frame owner, String title, String message) {
		super(owner);
		this.owner = owner;
		this.title = title;
		this.message = message;
		initialize();
	}

	// Phuong thuc khoi tao
	private void initialize() {
		this.setSize(300, 156);
		this.setResizable(false);
		this.setModal(true);
		this.setLocation(500, 100);
		this.setTitle(this.title);
		this.setContentPane(getPanel());
	}

	// Tra ve 1 JPanel
	private JPanel getPanel() {
		label = new JLabel();
		label.setBounds(41, 18, 215, 53);
		label.setForeground(Color.orange);
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setText(this.message);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.black);
		panel.add(getButton());
		panel.add(label);
		return panel;
	}

	private JButton getButton() {
		buttonOK = new JButton();
		buttonOK.setBounds(new Rectangle(114, 85, 68, 25));
		buttonOK.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonOK.setText("OK");
		buttonOK.setFocusPainted(false);
		buttonOK.addActionListener(e -> {
			if (e.getSource() == buttonOK)
				this.setVisible(false);
		});
		return buttonOK;
	}

	public static void showMessage(Frame owner, String title, String message) {
		new Message(owner, title, message).setVisible(true);
	}
}
