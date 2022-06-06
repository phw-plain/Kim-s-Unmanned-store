import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JPanel;

import code.DotGraph;
import code.DotGraphYear;

public class test {

	public static void main(String[] args) {

		Frame mainframe = new Frame();
		mainframe.setSize(1000, 1000);
		JPanel t = new JPanel(new GridLayout(2, 1));
		
		// graph
		int[][] data1 = new int[7][4]; // 일, 월, 연, sales
		int[][] data2 = new int[7][4];
		for (int i = 0; i < 7; i++) {
			data1[i][0] = 20 + i;
			data1[i][1] = 3;
			data1[i][2] = 2022;
			data1[i][3] = 100 + (20 * i);

			data2[i][0] = 20 + i;
			data2[i][1] = 3;
			data2[i][2] = 2022;
			data2[i][3] = 240 - (20 * i);
		}
		
		DotGraph tg = new DotGraph();
		JPanel cp1 = new JPanel();
		cp1 = tg.createDemoPanel(1, data1, data2);
		
		// graph
		int[][] data3 = new int[12][4]; // 일, 월, 연, sales, 요일
		int[][] data4= new int[12][4];
		for (int i = 0; i < data3.length; i++) {
			data3[i][0] = 1;
			data3[i][1] = 1+i;
			data3[i][2] = 2022;
			data3[i][3] = 100 + (1 * i);

			data4[i][0] = 1;
			data4[i][1] = 1+i;
			data4[i][2] = 2022;
			data4[i][3] = 240 - (1 * i);
		}
		DotGraphYear yg = new DotGraphYear();
		JPanel cp2 = new JPanel();
		cp2 = yg.createDemoPanel(1, data3, data4);
		
		t.add(cp1);
		t.add(cp2);
		
		mainframe.add(t);
		mainframe.setVisible(true);
	}

}
