package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Setting {
	// �⺻ ����
	int width;
	int height;

	// color
	Color title = new Color(0, 0, 0);
	Color background = new Color(255, 255, 255);

	// Font
	Font font1 = new Font("����ǹ��� �־�", Font.PLAIN, 50);
	Font font2 = new Font("����ǹ��� �־�", Font.PLAIN, 26);
	Font font3 = new Font("����ǹ��� �־�", Font.PLAIN, 16);

	// Image
	ImageIcon logo = new ImageIcon("src/img/logo.png");
	ImageIcon logo_over = new ImageIcon("src/img/logo_over.png");
	ImageIcon i = new ImageIcon("src/img/benner.png");
	Image im = i.getImage();
}