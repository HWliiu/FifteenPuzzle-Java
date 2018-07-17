package fifteenPuzzle;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class LocationUtil {
	private JFrame frame;
	private int xPos;
	private int yPos;

	public LocationUtil(JFrame ck) {
		// TODO Auto-generated constructor stub
		this.frame = ck;
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				xPos = e.getX();
				yPos = e.getY();
				if (!ck.isFocusable()) {
					ck.setFocusable(true);
				}
				if (!ck.isFocusOwner()) {
					ck.requestFocus();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		frame.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				frame.setCursor(Cursor.MOVE_CURSOR);
				frame.setLocation(e.getX() - xPos + frame.getLocation().x, e.getY() - yPos + frame.getLocation().y);
			}
		});
	}

}
