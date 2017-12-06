package run;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.MainGame;
import view.MyImages;

public class MainRun {

	public static void main(String[] args) {
		MyImages.loadAll();
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainGame thisClass = new MainGame();  
				thisClass.initialize();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
				thisClass.setResizable(false);
			}
		});
	}

}
