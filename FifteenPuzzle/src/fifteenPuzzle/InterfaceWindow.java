package fifteenPuzzle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceWindow extends JFrame {
	private Font buttonFont;
	public InterfaceWindow() {
		// TODO Auto-generated constructor stub
		this.setUndecorated(true);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(0, 0, 0, 0));
		buttonFont=new Font("¿¬Ìå", Font.HANGING_BASELINE, 30);
		JButton easyBtn=new JButton("      Easy     ");
		easyBtn.setBackground(new Color(255, 233, 87));
		easyBtn.setFont(buttonFont);
		easyBtn.setFocusPainted(false);
		easyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				GameControl.startGame(3);
				InterfaceWindow.this.dispose();
			}
		});
		
		JButton normalBtn=new JButton("     Normal    ");
		normalBtn.setBackground(new Color(242, 159, 63));
		normalBtn.setFont(buttonFont);
		normalBtn.setFocusPainted(false);
		normalBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				GameControl.startGame(4);
				InterfaceWindow.this.dispose();
			}
		});
		
		JButton hardBtn=new JButton("      Hard     ");
		hardBtn.setBackground(new Color(242, 117, 63));
		hardBtn.setFont(buttonFont);
		hardBtn.setFocusPainted(false);
		hardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				GameControl.startGame(6);
				InterfaceWindow.this.dispose();
			}
		});
		
		JButton hellBtn=new JButton("      Hell     ");
		hellBtn.setBackground(new Color(232, 126, 81));
		hellBtn.setFont(buttonFont);
		hellBtn.setFocusPainted(false);
		hellBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				GameControl.startGame(10);
				InterfaceWindow.this.dispose();
			}
		});
		
		JButton exitBtn=new JButton("      Exit     ");
		exitBtn.setBackground(new Color(222, 140, 104));
		exitBtn.setFont(buttonFont);
		exitBtn.setFocusPainted(false);
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				System.exit(0);
			}
		});
		
		JPanel btnPanel=new JPanel();
		btnPanel.setBackground(new Color(0, 0, 0, 0));
		JPanel jPanel=new JPanel();
		jPanel.setBackground(new Color(0, 0, 0, 0));
		BoxLayout boxLayout=new BoxLayout(btnPanel, BoxLayout.Y_AXIS);
		btnPanel.setLayout(boxLayout);
		btnPanel.add(Box.createVerticalStrut(20));
		btnPanel.add(easyBtn);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(normalBtn);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(hardBtn);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(hellBtn);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(exitBtn);
		btnPanel.add(Box.createVerticalStrut(20));
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel.add(btnPanel);
		this.add(jPanel);
		this.setVisible(true);
//		new LocationUtil(this);
	}
}
