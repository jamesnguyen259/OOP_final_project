package view;
//bang dieu khien
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameState;
import controller.SquareClicked;
import controller.PokemonController;

public class BoardPanel extends JPanel {

	public static boolean LOCKING = false;
	public JButton[][] buttons = new JButton[GameState.MAX_ROWS][GameState.MAX_COLUMNS];
	private JLabel bgrImage;// Label hien thi hinh nen
	public int[][] shapes; // Khuon
	public boolean isWaiting;// duoc cho doi hay khong
	public int iFirstClicked, jFirstClicked;
	public int currentLevel;// level hien tai
	public int currentScore;// Diem choi hien tai
	public MainGame mainGame;
	public JLabel[] lines = new JLabel[3];

	public BoardPanel(MainGame mainGame) {
		super();
		this.mainGame = mainGame;  
		initialize();
	}

	// Phuong thuc khoi tao
	private void initialize() {
		this.setLayout(null);
		int i, j;
		for (i = 1; i <= GameState.POKEMON_ROWS; i++) {
			for (j = 1; j <= GameState.POKEMON_COLUMNS; j++) {
				buttons[i][j] = new JButton();
				// Su kien khi cac button duoc nhap
				buttons[i][j].addMouseListener(new SquareClicked(i, j, this));
				buttons[i][j].setBounds(i * GameState.WIDTH_IMAGE, j * GameState.HEIGHT_IMAGE,
						GameState.WIDTH_IMAGE, GameState.HEIGHT_IMAGE);
				buttons[i][j].setFocusPainted(false);
				this.add(buttons[i][j]);
			}
			this.drawLines();
		}
		for (i = 0; i < 3; i++)
			this.add(lines[i]);
		bgrImage = new JLabel(MyImages.getARandomBgr());
		bgrImage.setBounds(0, 0, GameState.WIDTH_FRAME, GameState.HEIGHT_FRAME);
		this.add(bgrImage);
		this.setPreferredSize(new Dimension(GameState.WIDTH_FRAME, GameState.HEIGHT_FRAME));
	}

	// Cai dat ngau nhien mot hinh nen bat ky
	public void setNewBgr() {
		this.bgrImage.setIcon(MyImages.getARandomBgr());
	}

	// Tao level moi tu dau

	public void createNewLevel() {
		int i, j;
		setNewBgr();

		for (i = 1; i <= GameState.POKEMON_ROWS; i++) {
			for (j = 1; j <= GameState.POKEMON_COLUMNS; j++) {
				buttons[i][j].setEnabled(true);
				buttons[i][j].setBounds(i * GameState.WIDTH_IMAGE + 54, j * GameState.HEIGHT_IMAGE
						+ 48, GameState.WIDTH_IMAGE, GameState.HEIGHT_IMAGE);
			}
		}
		this.shapes = PokemonController.getNewMatrix();
		if (this.shapes == null) {
			System.out.println("Error");
			System.exit(0);
		}
		applyMatrix();
		isWaiting = false;
	}

	// Ap dung ma tran
	public void applyMatrix() {
		int i, j, k;
		for (i = 1; i <= GameState.POKEMON_ROWS; i++)
			for (j = 1; j <= GameState.POKEMON_COLUMNS; j++) {
				k = this.shapes[i][j];
				this.buttons[i][j].setVisible(k > 0);
				if (k > 0)
					this.buttons[i][j].setIcon(MyImages.PIECES[k]);
			}
	}

	// cai dat cho duong di: mau dong ke la da cam
	public void drawLines() {
		int i;
		for (i = 0; i < 3; i++) {
			lines[i] = new JLabel();
			lines[i].setOpaque(true);
			lines[i].setBackground(Color.ORANGE);
		}
	}

	public static Point findCentre(int i, int j) {
		return new Point(i * GameState.WIDTH_IMAGE + GameState.WIDTH_IMAGE
				/ 2 + 54, j * GameState.HEIGHT_IMAGE + GameState.HEIGHT_IMAGE
				/ 2 + 48);
	}
}
