package fifteenPuzzle;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	public Vector<JLabel> cardLabes = new Vector<JLabel>();
	private JPanel cardPanel;
	public JLabel tipsLabel;
	private JPanel tipsPanel;
	private JButton shuffleButton;
	private JButton backButton;
	private JPanel buttonPanel;
	private Font labelFont;
	private Font tipsFont;
	private Font buttonFont;
	private boolean pressedFlag;
	public Scramble scramble;
	private GameControl control;
	public boolean moveFlag = true;
	public int size;

	public AudioClip moveAudio;
	public AudioClip winAudio;

	public GameWindow(int n) {
		// TODO Auto-generated constructor stub
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 0, 0, 0));
		labelFont = new Font("楷体", Font.CENTER_BASELINE, 5 + 200 / n);
		tipsFont = new Font("微軟雅黑", Font.CENTER_BASELINE, 30);
		buttonFont = new Font("楷体", Font.HANGING_BASELINE, 30);
		scramble = new Scramble(n);
		control = new GameControl(this);
		size = n;
		try {
			moveAudio = Applet.newAudioClip(new File("res/move.wav").toURL());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			winAudio = Applet.newAudioClip(new File("res/win.wav").toURL());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cardPanel = new JPanel();
		cardPanel.setBackground(new Color(0, 0, 0, 0));
		cardPanel.setLayout(new GridLayout(n, n, 10, 10));
		tipsPanel = new JPanel();
		tipsPanel.setLayout(new BoxLayout(tipsPanel, BoxLayout.X_AXIS));
		tipsPanel.setBackground(new Color(0, 0, 0, 0));
		tipsLabel = new JLabel("   用  W S A D 移 动 方 块      ");
		tipsLabel.setFont(tipsFont);
		tipsLabel.setForeground(new Color(241, 124, 103));
		tipsPanel.add(tipsLabel);

		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		shuffleButton = new JButton("    Shuffle   ");
		backButton = new JButton("     Back     ");
		shuffleButton.setBackground(new Color(255, 180, 74));
		backButton.setBackground(new Color(255, 209, 145));
		shuffleButton.setFont(buttonFont);
		backButton.setFont(buttonFont);
		shuffleButton.setFocusPainted(false);
		shuffleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mouseClicked(arg0);
				control.scrambles();
				GameWindow.this.repaint();
				GameWindow.this.requestFocus();
			}
		});
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mouseClicked(arg0);
				GameWindow.this.dispose();
				new InterfaceWindow();
			}
		});
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(shuffleButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(backButton);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(Box.createVerticalStrut(40));
		initCardLabel(n * n);
		mainPanel.add(cardPanel);
		mainPanel.add(Box.createVerticalStrut(5));
		mainPanel.add(tipsPanel);
		mainPanel.add(Box.createVerticalStrut(5));
		mainPanel.add(buttonPanel);

		this.setUndecorated(true);
		this.add(mainPanel);
		this.setVisible(true);
		this.setSize(500, 600);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		// this.requestFocus();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (!GameWindow.this.isFocusable()) {
					GameWindow.this.setFocusable(true);
				}
				if (!GameWindow.this.isFocusOwner()) {
					GameWindow.this.requestFocus();
				}
				if (!pressedFlag) {
					// cardLabes.get(1).setText("2");
					if (e.getKeyCode() == 32) {
						control.scrambles();
					}
					if (e.getKeyCode() == 27) {
						System.exit(0);
					}
					if (moveFlag) {
						control.move(scramble.scramble_arr, e.getKeyCode(), n);
					}
					GameWindow.this.repaint();
					pressedFlag = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				super.keyReleased(arg0);
				pressedFlag = false;
			}
		});
	}

	public void initCardLabel(int size) {
		// TODO Auto-generated method stub
		int[] rands = scramble.getNew();
		for (int i = 0; i < rands.length; i++) {
			JLabel cardLabel;
			if (rands[i] != 0) {
				cardLabel = new JLabel(Integer.toString(rands[i]), JLabel.CENTER);
//				cardLabel.setBackground(Color.green);
				cardLabel.setOpaque(true);
				cardLabel.setFont(labelFont);
			} else {
				cardLabel = new JLabel(" ", JLabel.CENTER);
//				cardLabel.setBackground(Color.green);
				cardLabel.setOpaque(false);
				cardLabel.setFont(labelFont);
			}
			cardLabes.add(cardLabel);
		}
		control.scrambles();
		cardPanel.add(cardLabes.get(cardLabes.size() - 1));
		for (int i = 1; i < cardLabes.size(); i++) {
			cardPanel.add(cardLabes.get(i));
		}
		cardPanel.add(cardLabes.get(0));
	}
}
