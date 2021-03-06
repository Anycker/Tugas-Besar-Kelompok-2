package tubess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class SortPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	protected static final int BORDER_WIDTH = 10;
	private Dimension prefferedDimension;
	protected int size;
	protected int[] list;
	protected int sleepTime;
	private String name;
	
	public SortPanel(String name, int sleepTime, int width, int height) {
		prefferedDimension = new Dimension(width, height);
		this.name = name;
		this.sleepTime = sleepTime;
	}
	
	public void setList(int[] list) {
		reset();
		this.size = list.length;
		this.list = java.util.Arrays.copyOf(list, size);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return prefferedDimension;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);
		
		Font nameFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
		FontMetrics nameFontMetrix = getFontMetrics(nameFont);		
		g.setColor(Color.BLACK);
		g.setFont(nameFont);
		g.drawString(name, (getWidth() - nameFontMetrix.stringWidth(name)) / 2, BORDER_WIDTH + nameFontMetrix.getAscent() / 1);

	}

	@Override
	public abstract void run();

	public abstract void reset();

}
