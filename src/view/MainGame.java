package view;
import controller.TimeController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import controller.MenuItemController;
import model.GameState;

public class MainGame extends JFrame {

	MenuItemController menuItemController = new MenuItemController(null, null,this);

	public JPanel panel = null;
	private JMenuBar menuBar = null;
	private JMenu actionMenu = null;
	private JMenu helpJMenu = null;
	public JMenuItem newGame = null;
	public JMenuItem stopGame = null;
	public JMenuItem howToPlay = null;
	public JMenuItem aboutMe = null;
	public BoardPanel boardPanel = null;
	public ControlPanel controlPanel = null;
	public JLabel bgr = null;
	public TimeController timeController = null;
	public boolean newgame = false;

	public MenuItemController getMenuItemController() {
		return menuItemController;
	}
	// Tao mot JMenuBar chua cac Menu: Action, Level, Help
	private JMenuBar getMenu() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getAction());
			menuBar.add(getHelp());
		}
		return menuBar;
	}

	// Tao JMenu co ten la Action co 2 JMenuItem: Play New Game,Stop This Game
	private JMenu getAction() {
		if (actionMenu == null) {
			actionMenu = new JMenu();
			actionMenu.setText("Action");
			actionMenu.setFont(new Font("Verdana", Font.BOLD, 12));
			actionMenu.add(getNewGame());
			actionMenu.add(getStopGame());
		}
		return actionMenu;
	}

	// Tao JMenu co ten la Help co 2 JMenuItem: How To Play,About me
	private JMenu getHelp() {
		if (helpJMenu == null) {
			helpJMenu = new JMenu();
			helpJMenu.setText("Help");
			helpJMenu.setFont(new Font("Verdana", Font.BOLD, 12));
			helpJMenu.add(getHowToPlay());
			helpJMenu.add(getAboutMe());
		}
		return helpJMenu;
	}

	// Tao mot JMenuItem co ten la: Start New Game
	public JMenuItem getNewGame() {
		if (newGame == null) {
			newGame = new JMenuItem();
			newGame.setText("New game");
			newGame.setFont(new Font("Verdana", Font.PLAIN, 12));
			newGame.addActionListener(menuItemController);
		}
		return newGame;
	}

	// Tao mot JMenuItem co ten la:Stop Game
	private JMenuItem getStopGame() {
		if (stopGame == null) {
			stopGame = new JMenuItem();
			stopGame.setText("Stop Game");
			stopGame.setEnabled(false);
			stopGame.setFont(new Font("Verdana", Font.PLAIN, 12));
			stopGame.addActionListener(menuItemController);
		}
		return stopGame;
	}

	private JMenuItem getHowToPlay() {
		if (howToPlay == null) {
			howToPlay = new JMenuItem();
			howToPlay.setText("How to play");
			howToPlay.setFont(new Font("Verdana", Font.PLAIN, 12));
			howToPlay.addActionListener(menuItemController);
		}
		return howToPlay;
	}

	// Tao mot JMenuItem co ten la: About Me
	private JMenuItem getAboutMe() {
		if (aboutMe == null) {
			aboutMe = new JMenuItem();
			aboutMe.setText("About me");
			aboutMe.setFont(new Font("Verdana", Font.PLAIN, 12));
			aboutMe.addActionListener(menuItemController);
		}
		return aboutMe;
	}

	// Tao mot JPanel chinh chua cac thanh
	private JPanel getPanel() {
		if (panel == null) {
			bgr = new JLabel(MyImages.getARandomBgr());
			bgr.setPreferredSize(new Dimension(GameState.WIDTH_FRAME, GameState.HEIGHT_FRAME));
			this.boardPanel = new BoardPanel(this);
			this.controlPanel = new ControlPanel();
			this.controlPanel.setVisibleAll(false);
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(bgr, BorderLayout.CENTER);
			panel.add(controlPanel, BorderLayout.NORTH);
		}
		return panel;
	}

	// Hien thi giua man hinh Desktop
	private void setToCenter() {
		Dimension mySize = this.getSize();
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (scrSize.width - mySize.width) / 2;
		if (x < 0)
			x = 0;

		int y = (scrSize.height - mySize.height) / 2;
		if (y < 0)
			y = 0;

		this.setLocation(x, y);
	}

	// Phuong thuc khoi chay
	public void initialize() {
		
		// Them JMenuBar vao JFrame
		this.setJMenuBar(getMenu());

		// Them JContentPane vao JFrame
		this.setContentPane(getPanel());

		// Cai dat ten tieu de cho Game
		this.setTitle("Pokemon");

		// Khong duoc phep mo rong hoac thu hep cua so cua game
		this.setResizable(false);
		// icon cho frame
		BufferedImage image = null ;
		try {
			image = ImageIO.read(this.getClass().getResource("/images/pokeicon.png"));
		} catch (IOException rr) {
			// TODO Auto-generated catch block
			rr.printStackTrace();
		}
	   this.setIconImage(image);		
		// Khi goi phuong thuc nay khung chua se hien thi vua du cho cac thanh
		// phan hien thi
		this.pack();
		// thoat
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Hien thi khung man hinh game o giua nen Desktop
		this.setToCenter();
	}

	// Chuyen sang level tiep theo neu thang
	public void nextLevel() {
		if (timeController != null)
			timeController.setStop();
		boardPanel.currentLevel++;

		if (boardPanel.currentLevel == 4) {
			Message.showMessage(this, "THANK YOU","YOU HAVE FINISHED 3 LEVEL");
			menuItemController.stopThisGame();
			return;
		}
		Message.showMessage(this, "FINISHED LEVEL", "GO TO LEVEL " + boardPanel.currentLevel);
		boardPanel.currentScore += timeController.getCountTime()*10 + 500;

		boardPanel.createNewLevel();
		controlPanel.repaintLevel(boardPanel.currentLevel);
		controlPanel.repaintScore(boardPanel.currentScore);

		this.timeController = new TimeController(this.controlPanel.time, this, null);
		this.timeController.start();
	}
}
