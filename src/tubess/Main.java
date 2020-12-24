package tubess;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Visualization and Comparison of Sorting Algorithms
public class Main extends JApplet {

	private static final long serialVersionUID = 1L;
	private SortPanel[] sortPanels = new SortPanel[2];
	private static int size = 100;
	private int sleepTime = 2;
	private String animationName = "";

	public Main() {
		setLayout(new GridLayout(1, 0, 0, 0));
		SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
		sortPanelHolder.setLayout(new  GridLayout(0, 2, 0, 0));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width / 3;
		int height = screenSize.height / 3;

		sortPanels[0] = new BubbleSortPanel(" Bubble Sort ", sleepTime, width, height);
                sortPanels[1] = new InsertionSortPanel(" Insertion Sort ", sleepTime, width, height);
	
		for (int i = 0; i < sortPanels.length; i++) {
			sortPanels[i].setVisible(false);
			sortPanelHolder.add(sortPanels[i]);				
		}
		add(sortPanelHolder);
	}
	
	class SortPanelsHolder extends JPanel {
		private static final long serialVersionUID = 1L;
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}
	
	public void beginAnimation(int[] list) {
		try {
			repaint();
			Thread.sleep(100);
			repaint();
			for (int i = 0; i < sortPanels.length; i++) {
				sortPanels[i].setList(list);
				sortPanels[i].setVisible(true);
			}
			Thread.sleep(1000);
			ExecutorService executor = Executors.newFixedThreadPool(sortPanels.length);
			for (int i = 0; i < sortPanels.length; i++) {
				executor.execute(sortPanels[i]);
			}
			executor.shutdown();
			while(!executor.isTerminated()) {
				Thread.sleep(100);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting Algorithm Visualtization");
		Main main = new Main();
		frame.add(main);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		int[] list = new int[size];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = i + 1;
		}
		for (int i = 0; i < list.length; i++) {
			int index = (int) (Math.random() * list.length);
			int temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
		main.beginAnimation(list);
		
		for (int i = 0; i < list.length; i++) {
			list[i] = (1 + i / (size / 4) ) * (size / 4);
		}
		for (int i = 0; i < list.length; i++) {
			int index = (int) (Math.random() * list.length);
			int temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}

	}
}