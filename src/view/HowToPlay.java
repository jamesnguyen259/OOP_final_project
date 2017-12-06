package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Wind on 5/24/2017.
 */
public class HowToPlay {
	public HowToPlay() {
		init();
	}

	public void init() {
		JFrame frame = new JFrame("How To Play");
		frame.setSize(400, 300);
		JLabel lb = new JLabel("                       HOW TO PLAY");
		JLabel lb2 = new JLabel(
				" PIKACHU GAME - EASY GAME FOR EVERYONE ");
		lb.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.setLayout(new BorderLayout());
		frame.add(lb);
		TextArea tx = new TextArea(
						"\n TREN CUA SO MENU GAME, CHON NEW GAME DE TIEN HANH CHOI.\nCO 3 MAN` CHOI TAT CA. BAN CO 2 PHUT CHO MOI VAN CHOI.\nLUAT CHOI: HAY CLICK CHUOT DE NOI 2 CON POKEMON GIONG NHAU SAO CHO DUONG THANG NOI GIUA CHUNG KHONG CO QUA 3 DOAN GAP KHUC.\n MOI 1 CAP AN THANH CONG BAN DUOC CONG 100 DIEM.");
		tx.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tx.setForeground(Color.BLUE);
		// icon cho frame////////////////////////
		BufferedImage image = null;
		try {
			image = ImageIO.read(frame.getClass().getResource(
					"/images/icon.png"));
		} catch (IOException rr) {
			rr.printStackTrace();
		}
		frame.setIconImage(image);
		frame.setResizable(false);
		tx.setBackground(Color.BLACK);
		tx.setEditable(false);
		frame.setLocation(500, 100);

		frame.add(tx, BorderLayout.CENTER);
		frame.add(lb, BorderLayout.NORTH);
		frame.add(lb2, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
