package controller;

import javax.swing.JProgressBar;

import model.GameState;
import view.Message;
import view.MainGame;

public class TimeController extends Thread {
	private JProgressBar time;
	private MainGame mainGame;
	private boolean stop = false;
	private int countTime; // tra lai khoang thoi gian con thua` sau khi hoan thanh level
	MenuItemController menuItemController = new MenuItemController(null, null,null);

	public TimeController(JProgressBar time, MainGame mainGame, MenuItemController menuItemController) {
		this.time = time;
		this.mainGame = mainGame;
		this.menuItemController = menuItemController;
	}

	public void run() {
		for (int count = GameState.SECOND; count >= 0; count--) {
			if (stop)
				return;
			this.time.setValue(count);
			try {
				sleep(1200); // tuong duong voi 120s
				countTime = count;
				System.out.println(countTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Message.showMessage(mainGame, "LOSE", "FULL TIME");
		menuItemController.stopThisGame();
	}

	public int getCountTime() {
		return this.countTime;
	}

	public void setStop() {
		stop = true;
	}
}
