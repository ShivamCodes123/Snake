import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SnakePanel implements KeyListener{
	JFrame frame;
	JPanel subPanel;
	JPanel panel;
	GridLayout g;
	int direction = 2;
	int length = 3;
	boolean dead = false;
	Queue<Component> tails;
	int speed = 100;
	int head;
	int score;
	JLabel display;
	ArrayList<JButton> a = new ArrayList<JButton>();
	public SnakePanel() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		subPanel = new JPanel();
		subPanel.setFocusable(true);
		subPanel.addKeyListener(this);
		display = new JLabel("Score: " + score);
		g = new GridLayout(30, 30);
		subPanel.setLayout(g);
		for (int i = 0; i < g.getRows(); i++) {
			for (int j = 0; j < g.getColumns(); j++) {
				JButton x = new JButton();
				x.setEnabled(false);
				
				a.add(x);
				x.setBorder(new LineBorder(Color.black));
				subPanel.add(x);
				x.setFocusable(true);
			}
		}
		head = (g.getRows() / 2) * g.getColumns() + g.getColumns() / 2;
		
		panel.add(display, BorderLayout.NORTH);
		panel.add(subPanel, BorderLayout.CENTER);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeCreator(3);
	}
	public void snakeCreator(int length) {
		
		tails = new LinkedList<Component>();
		for (int i = length - 1; i >= 0; i--) {
			subPanel.getComponent(head - i).setBackground(Color.green);
			tails.add(subPanel.getComponent(head - i));
		}
		appleMaker();
		movement(head, tails.remove());
	}
	public void movement(int head, Component tail) {
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(direction == 2) {
			if((head / g.getRows()) != ((head + 1) / g.getRows()) || tails.contains(subPanel.getComponent(head + 1))) {
				dead = true;
				
			}
			if(dead == false) {
				System.out.println("x");
				if(subPanel.getComponent(head + 1).getBackground().equals(Color.red) == false) {
					tail.setBackground(null);
					tail = tails.remove();
				}
				else {
					score++;
					display.setText("Score: " + score);
					appleMaker();
				}
				
				subPanel.getComponent(head + 1).setBackground(Color.green);
				tails.add(subPanel.getComponent(head + 1));
				movement(++head, tail);
			}
		}
		else if(direction == 1) {
			if((head / g.getRows()) != ((head - 1) / g.getRows()) || tails.contains(subPanel.getComponent(head - 1))) {
				dead = true;
				
			}
			if(dead == false) {
				System.out.println("x");
				if(subPanel.getComponent(head - 1).getBackground() != Color.red) {
					tail.setBackground(null);
					tail = tails.remove();
				}
				else {
					score++;
					display.setText("Score: " + score);
					appleMaker();
				}
				subPanel.getComponent(head - 1).setBackground(Color.green);
				tails.add(subPanel.getComponent(head - 1));
				movement(--head, tail);
			}
		}
		else if(direction == 3) {
			if(head - g.getRows() < 0 || tails.contains(subPanel.getComponent(head - g.getRows()))) {
				dead = true;
			}
			if(dead == false) {
				System.out.println("x");
				if(subPanel.getComponent(head - g.getRows()).getBackground() != Color.red) {
					tail.setBackground(null);
					tail = tails.remove();
				}
				else {
					score++;
					display.setText("Score: " + score);
					appleMaker();
				}
				subPanel.getComponent(head - g.getRows()).setBackground(Color.green);
				tails.add(subPanel.getComponent(head - g.getRows()));
				head -= g.getRows();
				movement(head, tail);
			}
		}
		else if(direction == 4) {
			if(head + g.getRows() > g.getRows() * g.getColumns() || tails.contains(subPanel.getComponent(head + g.getRows()))) {
				dead = true;
			}
			if(dead == false) {
				System.out.println("x");
				if(subPanel.getComponent(head + g.getRows()).getBackground() != Color.red) {
					tail.setBackground(null);
					tail = tails.remove();
				}
				else {
					score++;
					display.setText("Score: " + score);
					appleMaker();
				}
				subPanel.getComponent(head + g.getRows()).setBackground(Color.green);
				tails.add(subPanel.getComponent(head + g.getRows()));
				head += g.getRows();
				movement(head, tail);
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("hello");
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(direction != 2) {
				direction = 1;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(direction != 1) {
				direction = 2;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(direction != 4) {
				direction = 3;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(direction != 3) {
				direction = 4;
			}
		}
		
	}
	public void appleMaker() {
		int apple = (int)(Math.random()* (g.getColumns()*g.getRows()));
		if(a.get(apple).getBackground() != Color.green) {
			a.get(apple).setBackground(Color.red);
		}
		else {
			appleMaker();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
