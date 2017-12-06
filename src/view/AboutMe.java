package view;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

public class AboutMe extends JDialog {
    public JPanel panelAboutMe;
    private JLabel avatar;
    private JLabel info;
    public JButton buttonOK;

    public AboutMe() {
        initialize();
    }

    public void initialize() {
        this.setSize(394, 246);
        this.setResizable(false);
        this.setModal(true);
        this.setTitle("About me");
        this.setContentPane(getJContentPane());
        this.setLocation(500, 100);
        this.setVisible(true);
    }

    private JPanel getJContentPane() {
        info = new JLabel();
        info.setBounds(171, 22, 184, 16);
        info.setForeground(Color.green);
        info.setFont(new Font("Tahoma", Font.BOLD, 12));
        info.setText("Team BK");

        avatar = new JLabel();
        avatar.setSize(150, 150);
        avatar.setIcon(MyImages.AVATAR);
        avatar.setLocation(8, 9);

        panelAboutMe = new JPanel();
        panelAboutMe.setLayout(null);
        panelAboutMe.setBackground(Color.black);
        panelAboutMe.add(avatar);
        panelAboutMe.add(info);
        panelAboutMe.add(getJButton());

        return panelAboutMe;
    }

    private JButton getJButton() {
        buttonOK = new JButton();
        buttonOK.setBounds(159, 175, 80, 25);
        buttonOK.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonOK.setText("OK");
        buttonOK.setFocusPainted(false);// Lam dep Button
        buttonOK.addActionListener(e -> {
            if (e.getSource() == buttonOK) {
                this.setVisible(false);
            }
        });
        return buttonOK;
    }
}
