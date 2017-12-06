package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import view.Message;
import view.MainGame;

import view.BoardPanel;

public class ConnectController implements Runnable {

	private BoardPanel panel;
	private ArrayList<?> arrayList;

	public ConnectController(BoardPanel panel, ArrayList<?> arrayList) {
		this.panel = panel;
		this.arrayList = arrayList;
	}

	public void run() {
		BoardPanel.LOCKING = true;
		draw();
		afterDrawPath();
		BoardPanel.LOCKING = false;
	}

	private void draw() {
		Point point1 = (Point) arrayList.get(0);
		Point point2;
		Point centre1, centre2;

		int i;

		for (i = 1; i < arrayList.size(); i++) {
			point2 = (Point) arrayList.get(i);

			centre1 = BoardPanel.findCentre(point1.x, point1.y);
			centre2 = BoardPanel.findCentre(point2.x, point2.y);

			panel.lines[i - 1].setBounds(getRRR(centre1, centre2));
			point1 = point2;
		}
		// ve hinh the hien duong an
		for (i = 1; i < arrayList.size(); i++)
			panel.lines[i - 1].setVisible(true);
		// Thoi gian dung duong an
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Xoa duong an
		for (i = 1; i < arrayList.size(); i++)
			panel.lines[i - 1].setVisible(false);
	}

	private Rectangle getRRR(Point p1, Point p2) {
		int x1, y1, x2, y2;

		if (p1.x < p2.x) {
			x1 = p1.x;
			x2 = p2.x;
		} else {
			x2 = p1.x;
			x1 = p2.x;
		}

		if (p1.y < p2.y) {
			y1 = p1.y;
			y2 = p2.y;
		} else {
			y2 = p1.y;
			y1 = p2.y;
		}

		return new Rectangle(x1 - 3, y1 - 3, x2 - x1 + 6, y2 - y1 + 6);
	}

	private void afterDrawPath() {
		panel.currentScore += 100; // an 1 cap duoc 100 diem
		panel.mainGame.controlPanel.repaintScore(panel.currentScore);

		if (PokemonController.countArrays(panel.shapes) == 0) {
			panel.mainGame.nextLevel();
			return;	
		}
		PokemonController.fixMatrix(panel.shapes, panel.currentLevel);
		panel.applyMatrix();

		if (PokemonController.isEnd(panel.shapes)) {
			Message.showMessage(panel.mainGame, "LOSE", "NO MOVE");
			panel.mainGame.getMenuItemController().stopThisGame();
		}
	}
}
