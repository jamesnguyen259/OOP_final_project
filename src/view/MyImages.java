package view;

import model.GameState;

import java.util.Random;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class MyImages {
	static final ImageIcon[] BGR_ICONS = new ImageIcon[GameState.NUMBER_OF_BGR + 1];
	static final ImageIcon[] PIECES = new ImageIcon[GameState.POKEMON_PIECES + 1];
	static final ImageIcon AVATAR = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/images/avatar.png"));

	public static void loadAll() {
		for (int i = 0; i < GameState.NUMBER_OF_BGR; i++)
			BGR_ICONS[i] = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/images/bgr" + i + ".jpg"));

		for (int i = 0; i <= GameState.POKEMON_PIECES; i++)
			PIECES[i] = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/images/h" + i + ".jpg"));
	}

	public static ImageIcon getARandomBgr() {
		Random rand = new Random();
		int  n = rand.nextInt(10);
		return BGR_ICONS[n];
	}

}
