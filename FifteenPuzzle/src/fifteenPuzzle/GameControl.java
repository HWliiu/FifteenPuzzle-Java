package fifteenPuzzle;

import java.awt.Color;

import javax.swing.JFrame;

public class GameControl {
//	public Color[] color = { Color.green, Color.pink, Color.blue, Color.gray, Color.cyan, Color.orange, Color.red,
//			Color.lightGray, Color.yellow, Color.magenta };

	private GameWindow gameWindow;
	private int moveCount;

	public GameControl(GameWindow frame) {
		// TODO Auto-generated constructor stub
		gameWindow = frame;
	}

	public void scrambles() { // 打乱函数
		// TODO Auto-generated method stub
		int[] arr = gameWindow.scramble.getNew();
		updateArr(arr);
		gameWindow.tipsLabel.setText("  用  W S A D 移 动 方 块   ");
		gameWindow.moveFlag = true;
		moveCount = 0;
	}

	private void updateArr(int[] arr) {
		int n = gameWindow.size;
		int lay;
		int r, g, b;
		r = (70 - 255) / (n - 1);
		g = (120 - 100) / (n - 1);
		b = (255 - 100) / (n - 1);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				if (arr[i] % n == 0) {
					lay = arr[i] / n - 1;
				} else if (arr[i] / n > arr[i] % n - 1) {
					lay = arr[i] % n - 1;
				} else {
					lay = arr[i] / n;
				}
				gameWindow.cardLabes.get(i).setBackground(new Color(255 + lay * r, 100 + lay * g, 100 + lay * b));
				gameWindow.cardLabes.get(i).setText(Integer.toString(arr[i]));
				gameWindow.cardLabes.get(i).setOpaque(true);
			} else {
				gameWindow.cardLabes.get(i).setText(" ");
				gameWindow.cardLabes.get(i).setOpaque(false);
			}
		}
	}

	private int getIndex(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	public void move(int[] scramble_arr, int key, int n) {
		// 获取0的下标
		int index_0 = getIndex(scramble_arr);

		// 边缘检测 如果不在边缘 即可移动
		if (key == 87 || key == 38) {
			if (index_0 != 0 && index_0 / n + (index_0 % n == 0 ? 0 : 1) < n) {
				if (index_0 == n * n - n)
					swap(scramble_arr, index_0, 0);
				else
					swap(scramble_arr, index_0, index_0 + n);

			}
		} else if (key == 83 || key == 40) {
			if (index_0 == 0 || index_0 / n + (index_0 % n == 0 ? 0 : 1) > 1) {
				if (index_0 == 0)
					swap(scramble_arr, index_0, n * n - n);
				else
					swap(scramble_arr, index_0, index_0 - n);
			}
		} else if (key == 65 || key == 37) {
			if (index_0 != 0 && index_0 % n != 0) {
				if (index_0 == n * n - 1)
					swap(scramble_arr, index_0, 0);
				else
					swap(scramble_arr, index_0, index_0 + 1);
			}
		} else if (key == 68 || key == 39) {
			if (index_0 % n != 1) {
				if (index_0 == 0)
					swap(scramble_arr, index_0, n * n - 1);
				else
					swap(scramble_arr, index_0, index_0 - 1);
			}
		}
		updateArr(scramble_arr);
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		moveCount++;
		if (win(array)) {
			gameWindow.tipsLabel.setText(" 恭 喜 通 关 ");
			gameWindow.winAudio.play();
			gameWindow.moveFlag = false;
			gameWindow.repaint();
		} else {
			gameWindow.tipsLabel.setText(" 移 动 步 数  ：" + moveCount);
		}
		gameWindow.moveAudio.play();
	}

	private boolean win(int arr[]) {
		// TODO Auto-generated method stub
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != i) {
				return false;
			}
		}
		return true;
	}

	public static void startGame(int n) {
		JFrame frame = new GameWindow(n);
		new LocationUtil(frame);
	}
}
