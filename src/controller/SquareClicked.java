package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import view.BoardPanel;

public class SquareClicked implements MouseListener {

	private int iClicked, jClicked;
	private BoardPanel panel;

	public SquareClicked(int iClicked, int jClicked, BoardPanel panel) {
		this.iClicked = iClicked;
		this.jClicked = jClicked;
		this.panel = panel;
	}

	public void mousePressed(MouseEvent e) {
		if (BoardPanel.LOCKING)
			return;
		// Gan so cho chuot Trai = 1, Phai = 2,giua bang 3
		int mouseButton = e.getButton();

		if (mouseButton == 1) {
			if (panel.isWaiting) {
				if (iClicked == panel.iFirstClicked
						&& jClicked == panel.jFirstClicked)
					return;
				panel.isWaiting = false;

				ArrayList<Point> arrayList = PokemonController.checkPath(panel.shapes,
						panel.iFirstClicked, panel.jFirstClicked, iClicked,
						jClicked);
				panel.buttons[panel.iFirstClicked][panel.jFirstClicked]
						.setEnabled(true);
				if (arrayList != null) {
					panel.shapes[panel.iFirstClicked][panel.jFirstClicked] = 0;
					panel.shapes[iClicked][jClicked] = 0;
					panel.buttons[panel.iFirstClicked][panel.jFirstClicked]
							.setVisible(false);
					panel.buttons[iClicked][jClicked].setVisible(false);


					new Thread(new ConnectController(panel, arrayList)).start();
				}
			} else {
				panel.iFirstClicked = iClicked;
				panel.jFirstClicked = jClicked;
				panel.isWaiting = true;
				panel.buttons[iClicked][jClicked].setEnabled(false);
			}
		} else if (mouseButton == 3) {
			if (panel.isWaiting) {
				panel.isWaiting = false;
				panel.buttons[panel.iFirstClicked][panel.jFirstClicked]
						.setEnabled(true);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
