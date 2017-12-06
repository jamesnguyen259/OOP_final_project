package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameState;
import view.*;

public class MenuItemController implements ActionListener {
	AboutMe aboutMe;
	Message message;
	MainGame mainGame;

	public MenuItemController(AboutMe aboutMe, Message message, MainGame mainGame) {
		this.aboutMe = aboutMe;
		this.message = message;
		this.mainGame = mainGame;
	}

	// Su kien khi stop game
	public void stopThisGame() {
		mainGame.newGame.setEnabled(true);
		mainGame.stopGame.setEnabled(false);
		mainGame.panel.remove(mainGame.boardPanel);
		mainGame.panel.add(mainGame.bgr, BorderLayout.CENTER);
		mainGame.bgr.setIcon(MyImages.getARandomBgr());
		mainGame.controlPanel.setVisibleAll(false);
		if (mainGame.timeController != null)
			mainGame.timeController.setStop();
		mainGame.repaint();
		// trang thai cua game: dang mo hay khong
		mainGame.newgame = false;
		mainGame.setVisible(true);
	}

	// Su kien new game
	public void createNewGame() {
		mainGame.newGame.setEnabled(false);
		mainGame.stopGame.setEnabled(true);
		mainGame.panel.remove(mainGame.bgr);
		mainGame.boardPanel.createNewLevel();
		mainGame.boardPanel.currentLevel = GameState.CURRENT_LEVEL;
		mainGame.boardPanel.currentScore = GameState.CURRENT_SCORE;
		mainGame.controlPanel.repaintLevel(mainGame.boardPanel.currentLevel);
		mainGame.controlPanel.repaintScore(GameState.CURRENT_SCORE);
		mainGame.controlPanel.setVisibleAll(true);
		if (mainGame.timeController != null)
			mainGame.timeController.setStop();
		mainGame.timeController = new TimeController(mainGame.controlPanel.time,
				mainGame, this);
		mainGame.timeController.start();
		mainGame.panel.add(mainGame.boardPanel, BorderLayout.CENTER);
		mainGame.repaint();
		// Game dang mo
		mainGame.newgame = true;
		mainGame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainGame.howToPlay) {
			new HowToPlay();
		}
		if (e.getSource() == mainGame.stopGame) {
			stopThisGame();
		}
		if (e.getSource() == mainGame.newGame) {
			createNewGame();
		}
		if (e.getSource() == mainGame.aboutMe) {
			new AboutMe();
		}
	}
}
