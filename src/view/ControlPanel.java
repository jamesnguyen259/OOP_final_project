package view;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JProgressBar;

public class ControlPanel extends JPanel {
	private JLabel level = null;
	public JProgressBar time = null;
	private JLabel score = null;

	public ControlPanel() {
		super();
		initialize();
	}
	// khoi tao
	private void initialize() {
		score = new JLabel();
		score.setBounds(760, 1, 132, 39);
		score.setForeground(Color.pink);
		score.setFont(new Font("Tahoma", Font.BOLD, 20));
		level = new JLabel();
		level.setBounds(420, 3, 78, 16);
		level.setForeground(Color.green);
		level.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(720, 40));
		this.setBackground(Color.black);
		this.add(level, null);
		this.add(getTime(), null);
		this.add(score, null);
	}

	// Phuong thuc tra ve thoi thanh chay time
	private JProgressBar getTime() {
		if (time == null) {
			time = new JProgressBar();
			time.setBounds(200, 22, 500, 14);
		}
		return time;
	}

	// Phuong thuc cai dat qua trinh hien thi cua cac thanh phan:
	// level,time,score
	public void setVisibleAll(boolean b) {
		this.level.setVisible(b);
		this.time.setVisible(b);
		this.score.setVisible(b);
	}

	// / Phuong thuc setText cho Level
	public void repaintLevel(int level) {
		this.level.setText("Level " + level);
	}

	// / Phuong thuc setText cho Score
	public void repaintScore(int s) {
		this.score.setText("Score: " + s);
	}
}
